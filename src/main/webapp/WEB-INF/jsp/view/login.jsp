<%-- 
    Document   : login
    Created on : May 2, 2021, 11:33:35 PM
    Author     : stewi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
        <link href="styles/normalize.css" rel="stylesheet" type="text/css"/>
        <link href="styles/main.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <header>
            <nav>
                <ul>
                    <li><a href="<c:url value="/jobs" />">View Jobs</a></li>
                    <li class="activeNavBar"><a href="<c:url value="/applications" />">Applications</a></li>
                </ul>
            </nav>
        </header>
        <h1>You Must login to view the current applications.</h1>
        <form method="POST" action="<c:url value="/login" />">
            <label for="username">Username</label>
            <input type="text" name="username" id="username" /><br><br>
            <label for="password">Password</label>
            <input type="password" name="password" id="password" /><br><br>
            <input type="submit" value="Log In" />
        </form>
    </body>
</html>
