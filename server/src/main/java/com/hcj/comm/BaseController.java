package com.hcj.comm;

import javax.servlet.http.HttpSession;

/**
 * BaseController
 *
 * @author hcj
 * @date 2023-06-16
 */
public class BaseController {
    protected static final String SESSION_USER = "user";

    public BaseController() {
    }

    protected Object getSessionUser(HttpSession session) {
        return session.getAttribute("user");
    }

    protected void setSessionUser(HttpSession session, Object user) {
        session.setAttribute("user", user);
    }
}
