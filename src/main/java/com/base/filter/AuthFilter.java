package com.base.filter;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/6.
 */
public class AuthFilter implements Filter {
    Logger log = Logger.getLogger(AuthFilter.class);
    private static final String NO_CHECK = "noCheck";
    private static final String REDIRECT_PATH = "redirectPath";
    private List<String> noCheckList = new ArrayList<String>();
    private String redirectPath = "../user/login.html";

    public void init(FilterConfig init) throws ServletException {
        log.info("初始化filter....");
        String noChecks = init.getInitParameter("whiteList");
        if (StringUtils.isNotBlank(noChecks)) {
            if (StringUtils.indexOf(noChecks, ",") != -1) {
                for (String no : noChecks.split(",")) {
                    noCheckList.add(StringUtils.trimToEmpty(no));
                }
            } else {
                noCheckList.add(noChecks);
            }
        }
        String path = init.getInitParameter(REDIRECT_PATH);
        if (StringUtils.isNotBlank(path)) {
            redirectPath = path;
        }
    }

    private boolean check(String path) {
        if (noCheckList == null || noCheckList.size() <= 0)
            return false;
        for (String s : noCheckList) {
            if (path.indexOf(s) > -1) {
                return true;
            }
        }
        return false;
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String contextpath = req.getContextPath();
        if ("/".equals(contextpath)) {
            contextpath = "";
        }
        if (check(req.getRequestURI())) {
            filterChain.doFilter(req, resp);
        } else {
            HttpSession session = ((HttpServletRequest) request).getSession();
            String token = (String) session.getAttribute("token");
            if (token == null)
                resp.sendRedirect(resp.encodeURL(contextpath + redirectPath));
            else
                filterChain.doFilter(req, resp);
        }
    }

    public void destroy() {

    }
}