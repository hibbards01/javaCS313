<%-- 
    Document   : loggedIn
    Created on : Jun 17, 2015, 3:30:12 PM
    Author     : samuel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List" %>
<%@page import="java.util.Map" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello ${name}</h1>
        <br />
        <h2>Posts</h2>
        <c:forEach items="${posts}" var="post">
            <h3>Create by ${post.name}</h3>
            <h4>${post.date}</h4>
            <p>${post.text}</p>
        </c:forEach>
    </body>
</html>
