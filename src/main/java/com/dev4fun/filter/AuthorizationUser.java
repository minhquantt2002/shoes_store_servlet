package com.dev4fun.filter;

import com.dev4fun.model.Account;
import com.dev4fun.utils.SessionUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {"/user/*"})
public class AuthorizationUser implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String url = req.getRequestURI();
        if (!url.startsWith("/login") && url.startsWith("/user")) {
            Account account = (Account) SessionUtil.getInstance().getValue(req, "ACCOUNT_USER");
            if (account != null && account.getRole().equals("USER")) {
                chain.doFilter(request, response);
            } else {
                res.sendRedirect("/login");
            }
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
