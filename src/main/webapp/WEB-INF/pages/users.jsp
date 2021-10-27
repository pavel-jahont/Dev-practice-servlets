<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <title>Users Page</title>
    </head>
    <body>

        <table border="1">
            <thead>
                <tr>
                    <td>ID</td>
                    <td>Username</td>
                    <td>Password</td>
                    <td>isActive</td>
                    <td>Age</td>
                    <td>Remove</td>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${users}" var="user">
                    <tr>
                    <td>${user.id}</td>
                        <td>${user.username}</td>
                        <td>${user.password}</td>
                        <td><c:if test="${user.active == true}">
                                         <p>I am a superman</p>
                                     </c:if>
                                     <c:if test="${user.active == false}">
                                         <p>Staying at shadow</p>
                                     </c:if></td>
                        <td>${user.age}</td>
                        <td>
                        <form method="POST" action="removeUser">
                                     <input type="hidden" name="userId" value="${user.id}"/>
                                     <input type="submit" name="remove" value="remove"/>
                         </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <p>You can add new user <a href="${pageContext.request.contextPath}/createNewUser">here</a></p>
    </body>
</html>