package cn.edu.ncut.controller.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by lh on 2017/4/22.
 */
public class SessionServletRequest extends HttpServletRequestWrapper{

    private HttpServletRequest request;

    private HttpServletResponse response;

    public SessionServletRequest(HttpServletRequest request) {
        super(request);
    }

    public SessionServletRequest(HttpServletRequest request, HttpServletResponse response) {
        super(request);
        this.request = request;
        this.response = response;
    }

    @Override
    public HttpSession getSession() {
        return new HttpSessionImpl(request,response);
    }
}
