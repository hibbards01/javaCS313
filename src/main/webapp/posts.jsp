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
        <hr>
        <br />
        <h2>Posts</h2>
        <hr>
        <c:forEach items="${posts}" var="post">
            <h4>Create by ${post.name} at ${post.date}</h4>
            <p>${post.text}</p>
            <br />
        </c:forEach>
        <br />
        <br />
        <hr>
        <br />
        <h2>Add a new post</h2>
        <br />
        <form action="NewPost" method="POST">
            <label>Post:</label><br />
            <textarea name="post" rows="10" cols="70" placeholder="Enter text here"></textarea>
            <br />
            <button type="submit">Post</button>
        </form>
    </body>
</html>
