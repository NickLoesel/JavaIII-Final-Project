<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Job List</title>
        <link href="styles/normalize.css" rel="stylesheet" type="text/css"/>
        <link href="styles/main.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <header>
            <nav>
                <ul>
                    <li class="activeNavBar"><a href="<c:url value="/jobs" />">View Jobs</a></li>
                    <li><a href="<c:url value="/applications" />">Applications</a></li>
                </ul>
            </nav>
        </header>
        <div class ="container">
            <h2>Job List</h2>
            <div class="pagination">
                <c:forEach var="i" begin="1" end="${maxPages}">
                    <a <c:if test="${currentPage == i}">class="active"</c:if> href="<c:url value="/jobs"><c:param name="page" value="${i}" /></c:url>">${i}</a>
                </c:forEach>
            </div>
            <div class="job">
                <c:forEach items="${jobs}" var="jobs" begin="${begin}" end="${end}">
                    <div class="jobs">
                        <p>&nbsp;Title: <a href="<c:url value="/jobs"><c:param name="id" value="${jobs.id}" /></c:url>">${fn:escapeXml(jobs.title)} </a></p>
                        <p>&nbsp;Location: <c:out value="${jobs.city}" />&nbsp;, <c:out value="${jobs.state}" /><br/>&nbsp;Department: <c:out value="${jobs.department}" /></p>
                        <p>&nbsp;Description:</p> 
                        <p><c:out value="${jobs.jobDescription}"/></p>
                        </div>
                </c:forEach>
            </div>
        </div>
    </body>
</html>
