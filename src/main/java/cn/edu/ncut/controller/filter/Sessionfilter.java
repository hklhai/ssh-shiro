package cn.edu.ncut.controller.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by lh on 2017/4/22.
 */
public class Sessionfilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        ServletRequest request = new SessionServletRequest((HttpServletRequest)servletRequest,(HttpServletResponse)servletResponse);
        filterChain.doFilter(request,servletResponse);

    }

    @Override
    public void destroy() {

    }
}
