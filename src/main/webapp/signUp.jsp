<%-- 
    Document   : signUp
    Created on : Jun 18, 2015, 8:22:19 PM
    Author     : samuel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sign Up Page</title>
    </head>
    <body>
        <h1>Sign Up</h1>
        <br />
        <form action="SignUp" method="POST">
            <label>First Name:</label>
            <input name="firstName" type="text" />
            <label>Last Name:</label>
            <input name="lastName" type="text" />
            <br />
            <br />
            <label>Username:</label>
            <input name="username" type="text" />
            <br />
            <br />
            <label>Password:</label>
            <input name="password" type="password" />
            <br />
            <br />
            <button type="submit">Submit</button>
        </form>        
    </body>
</html>
