<%-- 
    Document   : job
    Created on : May 1, 2021, 11:49:13 PM
    Author     : stewi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <header>
            <nav>
                <ul>
                    <li><a href="<c:url value="/jobs" />">View Jobs</a></li>
                    <li><a href="<c:url value="/applications" />">Applications</a></li>
                </ul>
            </nav>
        </header>
        <main>
            <div class ="container">
                <div class ="job">
                    <h2><c:out value="${jobs.title}"/></h2>
                    <table>
                        <tr>
                            <td>Location:</td>
                            <td><c:out value="${jobs.city}, ${jobs.state}"/></td>
                        </tr>
                        <tr>
                            <td>Job Type:</td>
                            <td>
                                <c:choose>
                                    <c:when test="${jobs.fullTime}">
                                        Full-Time
                                    </c:when>
                                    <c:otherwise>
                                        Part-Time
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                        <tr>
                            <td>Department:</td>
                            <td><c:out value="${jobs.department}"/></td>
                        </tr>
                        <tr>
                            <td>Experience:</td>
                            <td><c:out value ="${jobs.experience}"/></td>
                        </tr>
                        <tr>
                            <td>Salary:</td>
                            <td>
                                <fmt:formatNumber maxFractionDigits="0" type="currency" value="${jobs.salary}" />
                                <c:choose>
                                    <c:when test="${jobs.wageCategory == 'Salaried'}">
                                        Per Year
                                    </c:when>
                                    <c:otherwise>
                                        Per Hour
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                        <tr>
                            <td>Description:</td>
                            <td><c:out value="${jobs.jobDescription}"/></td>
                        </tr>
                    </table>
                </div>
                <div class="Application">
                    <h2>Apply for <c:out value="${jobs.title}"/></h2>
                    <form action="<c:url value="/applications" />" method="POST" enctype="multipart/form-data"> 
                        <input type="hidden" name="action" value="create">
                        <input type="hidden" name="jobId" value="${jobs.id}">
                        <input type="hidden" name="jobTitle" value="${jobs.title}">
                        <table>
                            <tr>
                                <td><label for="firstName">First Name: <span class="Requirederror"></span></label></td>
                                <td>
                                    <input type="text" name="firstName" id="firstName" value="${application.firstName}">
                                    <p class="error">${application.firstNameError}</p>
                                </td>
                            </tr>
                            <tr>
                                <td><label for="lastName">Last Name: <span class="Requirederror"></span></label></td>
                                <td>
                                    <input type="text" name="lastName" id="lastName" value="${application.lastName}">
                                    <p class="error">${application.lastNameError}</p>
                                </td>
                            </tr>
                            <tr>
                                <td><label for="email">Email: <span class="Requirederror"></span></label></td>
                                <td>
                                    <input type="email" name="email" id="email" value="${application.email}">
                                    <p class="error">${application.emailError}</p>
                                </td>
                            </tr>
                            <tr>
                                <td><label for="phone">Phone Number: <span class="Requirederror"></span></label></td>
                                <td>
                                    <input type="text" name="phone" id="phone" value="${application.phone}">
                                    <p class="error">${application.phoneError}</p>
                                </td>
                            </tr>
                            <tr>
                                <td><label for="resume">Resume: <span class="Requirederror"></span></label></td>
                                <td>
                                    <input type="file" name="resume" id="resume">
                                    <p class="error">${application.resumeError}</p>
                                </td>
                            </tr>
                            <tr>
                                <td><label for="salary">Desired Salary: <span class="Requirederror"></span></label></td>
                                <td>
                                    <input type="text" name="salary" id="salary" value="${application.desiredSalary}">
                                    <p class="error">${application.salaryError}</p>
                                </td>
                            </tr>
                            <tr>
                                <td><label for="startDate">Start Date: <span class="Requirederror"></span></label></td>
                                <td>
                                    <input type="date" name="startDate" id="startDate">
                                    <p class="error">${application.startDateError}</p>
                                </td>
                            </tr>

                        </table>

                    </form>
                </div>
            </div>
        </main>
    </body>
</html>
