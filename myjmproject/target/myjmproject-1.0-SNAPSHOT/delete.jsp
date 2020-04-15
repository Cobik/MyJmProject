<%@ page import="service.UserService" %>
<%@ page import="java.sql.SQLException" %><%--
  Created by IntelliJ IDEA.
  User: javidanhajizada
  Date: 4/13/20
  Time: 21:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    UserService userService = new UserService();
    String id = request.getParameter("d");

    try {
        if (userService.deleteUser(Long.parseLong(id))){
            response.sendRedirect("/myjmproject_war/main");
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.getWriter().println("Have a problem");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

%>
