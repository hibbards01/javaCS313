<%-- 
    Document   : login
    Created on : Jun 17, 2015, 3:18:39 PM
    Author     : samuel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page session="true" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Login</h1>
        <%
            // Grab the session
            String error = "none";
            Boolean login = (Boolean) session.getAttribute("loginFailed");
            
            if (login != null) {
                if (login == true) {
                    error = "inline";                    
                }
            }
        %>
        <p style="display:<%= error %>">
            <font color="red">Username or password is invalid.</font>
        </p>
        <br />
        <br />
        <form action="Login" method="POST">
            <label>Username:</label>
            <input name="username" type="text" />
            <label>Password:</label>
            <input name="password" type="password" />
            <button type="submit">Submit</button>
        </form>
    </body>
</html>
