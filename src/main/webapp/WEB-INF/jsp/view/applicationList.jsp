<%-- 
    Document   : applicationList
    Created on : May 5, 2021, 10:20:59 PM
    Author     : stewi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Application List</title>
        <link href="styles/normalize.css" rel="stylesheet" type="text/css"/>
        <link href="styles/main.css" rel="stylesheet" type="text/css"/>  
    </head>
    <body>
        <header>
            <nav>
                <ul>
                    <li><a href="<c:url value="/jobs" />">View Jobs</a></li>
                    <li class="activeNavBar"><a href="<c:url value="/applications" />">Applications</a></li>
                    <c:if test="${sessionScope.username != null}">
                          <li><a href="<c:url value="/login?logout" />">Logout</a></li>
                    </c:if>
                </ul>
            </nav>
        </header>
        <div class="applications">
            <c:if test = "${applications.isEmpty() == true}">
                <p class="noapps">There are currently no applications.</p>
            </c:if>
            <c:forEach items="${applications}" var="applications">
                <div class="jobs">
                    <p>&nbsp;Job Title: <a href="<c:url value="/applications"><c:param name="id" value="${applications.value.id}" /></c:url>">${applications.value.jobTitle}</a></p>
                    <p>&nbsp;Name: <c:out value="${applications.value.firstName}" />&nbsp;<c:out value="${applications.value.lastName}" /><br/>&nbsp;Email: <c:out value="${applications.value.email}" /></p>
                </div>
            </c:forEach>
            </div>
    </body>
</html>
