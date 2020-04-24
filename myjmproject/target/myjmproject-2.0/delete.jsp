<%@ page import="service.UserService" %>
<%@ page import="model.User" %>
<%--
  Created by IntelliJ IDEA.
  User: javidanhajizada
  Date: 4/13/20
  Time: 21:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String id = request.getParameter("id");
    User user = UserService.getInstance().getUserById(Long.parseLong(id));

    if (UserService.getInstance().deleteUser(Long.parseLong(id))){
        response.sendRedirect("/admin/main");
        response.setStatus(HttpServletResponse.SC_OK);
    } else {
        response.getWriter().println("Have a problem");
    }

%>
