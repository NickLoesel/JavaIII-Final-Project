<%-- 
    Document   : application
    Created on : May 5, 2021, 11:01:49 PM
    Author     : stewi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Application</title>
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
        <div class ="viewApplication">
            <h2><c:out value="${application.jobTitle}"/></h2>
            <table>
                <tr>
                    <td>Name:</td>
                    <td><c:out value="${application.firstName}, ${application.lastName}"/></td>
                </tr>
                <tr>
                    <td>Email:</td>
                    <td><c:out value="${application.email}"/></td>
                </tr>
                <tr>
                    <td>Phone Number:</td>
                    <c:set value="${application.phone}" var="phone"/>
                    <td><c:out value="(${fn:substring(phone, 0, 3)}) ${fn:substring(phone, 3, 6)} - ${fn:substring(phone, 6, fn:length(phone))}"/></td>
                </tr>
                <tr>
                    <td>Resume:</td>
                    <td> <a href="<c:url value="/resume"><c:param name="id" value="${application.resumeUpload}" /></c:url>">${application.resumeUpload}</a></td>
                    </tr>
                    <tr>
                        <td>Desired Salary:</td>
                        <td><fmt:formatNumber maxFractionDigits="0" type="currency" value="${application.desiredSalary}" /></td>
                </tr>
                <tr>
                    <td>Earliest Start Date:</td>
                    <td><c:out value="${application.earliestStartDate}"/></td>
                </tr>
            </table>
        </div>
        <form class="inactiveResume" action="<c:url value="/jobs" />" method="POST" enctype="multipart/form-data">
            <table>
                <tr>
                    <td><label>Mark Resume as inactive?</label></td>
                    <td>
                        <input type="radio" id="yes" name="active" value="yes">
                        <label for="yes">Yes</label><br>
                        <input type="radio" id="no" name="active" value="no">
                        <label for="no">No</label>
                    </td>
                </tr>
            </table>
            <input type="submit" value="submit">
        </form>

    </body>
</html>
