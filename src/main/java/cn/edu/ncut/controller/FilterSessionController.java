package cn.edu.ncut.controller;

import lyy.redis.JedisAPI;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lh on 2017/4/20.
 */
@Controller
public class FilterSessionController {

    @RequestMapping(value = "/filterSessionSave", method = RequestMethod.POST)
    public ModelAndView filterSessionSave(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> map = new HashMap<>();
        HttpSession httpSession = request.getSession();
        httpSession.setAttribute("name", request.getParameter("name"));
        httpSession.setAttribute("passwd", request.getParameter("passwd"));

        System.out.println("name:" + request.getParameter("name"));
        System.out.println("passwd:" + request.getParameter("passwd"));

        //将Session信息存入Redis

        String key = Math.random() + "==" + "userInfo";
        JedisAPI.set(key, request.getParameter("name") + " " + request.getParameter("passwd"));

//        //需要借助Cookie将key写入Cookie中
//        Cookie cookie = new Cookie("sessionId", key);
//        cookie.setPath("/");
//        response.addCookie(cookie);
//
        String site = "";
        try {
            site = InetAddress.getLocalHost().getHostAddress() + ":" + InetAddress.getLocalHost().getHostName();
            System.out.println(site);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        map.put("name", site);
        return new ModelAndView("s2/index", map);
    }


    @RequestMapping(value = "/filterSessionGet", method = RequestMethod.POST)
    public ModelAndView filterSessionGet(HttpServletRequest request) {
        Map<String, String> map = new HashMap<>();


        HttpSession httpSession = request.getSession();
        httpSession.setAttribute("name", httpSession.getAttribute("name"));
        httpSession.setAttribute("passwd", httpSession.getAttribute("passwd"));

        System.out.println("name:" + httpSession.getAttribute("name"));
        System.out.println("passwd:" + httpSession.getAttribute("passwd"));

        System.out.println("Session get success");


//        String value = null;
//        //获取Cookie中的sessionId
//        Cookie[] requestCookies = request.getCookies();
//        if (requestCookies != null && requestCookies.length > 0) {
//            for (Cookie co : requestCookies) {
//                if ("sessionId".equals(co.getName())) {
//                    value = co.getValue();
//                }
//            }
//        }

//        //从Redis缓存中取出
//        String s = JedisAPI.get(value);
//        System.out.println(s);

        String site = "";
        try {
            site = InetAddress.getLocalHost().getHostAddress() + ":" + InetAddress.getLocalHost().getHostName();

            System.out.println(site);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        map.put("name", site);
        map.put("user", httpSession.getAttribute("name")+" "+httpSession.getAttribute("passwd"));
        return new ModelAndView("s2/index2", map);
    }

}
