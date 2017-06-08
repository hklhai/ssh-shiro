package cn.edu.ncut.controller.filter;

import lyy.redis.JedisAPI;

import javax.servlet.ServletContext;
import javax.servlet.http.*;
import java.util.*;

/**
 * Created by lh on 2017/4/22.
 */
public class HttpSessionImpl implements HttpSession {

    private HttpServletRequest request;

    private HttpServletResponse response;

    ThreadLocal<String> local = new ThreadLocal<>();

    ThreadLocal<Map<String, Object>> mapThreadLocal = new ThreadLocal<>();

    public HttpSessionImpl(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    @Override
    public long getCreationTime() {
        return 0;
    }

    @Override
    public String getId() {
        return null;
    }

    @Override
    public long getLastAccessedTime() {
        return 0;
    }

    @Override
    public ServletContext getServletContext() {
        return null;
    }

    @Override
    public void setMaxInactiveInterval(int i) {

    }

    @Override
    public int getMaxInactiveInterval() {
        return 0;
    }

    @Override
    public HttpSessionContext getSessionContext() {
        return null;
    }

    /**
     * 从Redis中获取
     * 实际项目中Session 中存放的位map，采用redis的hmget方法获取Session
     *
     * @param s
     * @return
     */
    @Override
    public Object getAttribute(String s) {
        //先获取SessionId
        String sessionId = getSessionIdFromCookie();
        //String sessionString= JedisAPI.get(sessionId);
        List<String> hmget = JedisAPI.hmget(sessionId, s);
        return hmget.get(0);
    }

    private String getSessionIdFromCookie() {
        String value = null;

        Cookie[] requestCookies = request.getCookies();
        if (requestCookies != null && requestCookies.length > 0) {
            for (Cookie co : requestCookies) {
                if ("sessionId".equals(co.getName())) {
                    value = co.getValue();
                }
            }
        }
        return value;
    }

    @Override
    public Object getValue(String s) {
        return null;
    }

    @Override
    public Enumeration<String> getAttributeNames() {
        return null;
    }

    @Override
    public String[] getValueNames() {
        return new String[0];
    }

    /**
     * 写入Redis
     * 第一次请求时sessionId为空
     *
     * @param s
     * @param o
     */
    @Override
    public void setAttribute(String s, Object o) {
        String sessionId = getSessionIdFromCookie();
        if (sessionId == null) {
            if (local.get() != null) {
                sessionId = local.get();
            } else {
                sessionId = Math.random() + "----" + "userInfoFilter";
                local.set(sessionId);
            }
        } else//需要判断sessionId在Redis中的情况，将Redis中的信息取出，与本次请求内容合并，在放入Redis中
        {

        }

        if (mapThreadLocal.get() != null) {
            mapThreadLocal.get().put(s, o);
            //JedisAPI.hmset(sessionId,mapThreadLocal.get());

        } else {
            Map<String, Object> map = new HashMap<>();
            map.put(s, o);
            mapThreadLocal.set(map);
        }
        JedisAPI.hdel(sessionId);
        JedisAPI.hmset(sessionId, mapThreadLocal.get());
        setCookie(sessionId);
    }


    /**
     * 写入Cookie
     *
     * @param sessionId
     */
    private void setCookie(String sessionId) {
        //需要借助Cookie将key写入Cookie中
        Cookie cookie = new Cookie("sessionId", sessionId);
        cookie.setPath("/");
        response.addCookie(cookie);
    }


    @Override
    public void putValue(String s, Object o) {

    }

    @Override
    public void removeAttribute(String s) {

    }

    @Override
    public void removeValue(String s) {

    }

    @Override
    public void invalidate() {

    }

    @Override
    public boolean isNew() {
        return false;
    }
}
