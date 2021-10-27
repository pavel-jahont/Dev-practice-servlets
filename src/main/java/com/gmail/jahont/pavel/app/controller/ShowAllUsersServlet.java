package com.gmail.jahont.pavel.app.controller;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gmail.jahont.pavel.app.service.UserService;
import com.gmail.jahont.pavel.app.service.impl.UserServiceImpl;
import com.gmail.jahont.pavel.app.service.model.UserDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ShowAllUsersServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private static final String HOME = "/WEB-INF/pages";
    private static final String USERS = HOME + "/users.jsp";
    private final UserService userService = UserServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<UserDTO> users = userService.findAll();
        req.setAttribute("users", users);

        ServletContext servletContext = getServletContext();
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(USERS);
        requestDispatcher.forward(req, resp);
    }
}
