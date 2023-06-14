package com.dev4fun.filter;

import com.dev4fun.model.Account;
import com.dev4fun.utils.SessionUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {"/admin/*"})
public class AuthorizationAdmin implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String url = req.getRequestURI();

        if (!url.startsWith("/admin/login") && url.startsWith("/admin")) {
            Account account = (Account) SessionUtil.getInstance().getValue(req, "ACCOUNT_ADMIN");
            if (account != null && (account.getRole().equals("ADMIN") || account.getRole().equals("STAFF"))) {
                chain.doFilter(request, response);
            } else {
                res.sendRedirect("/admin/login");
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
