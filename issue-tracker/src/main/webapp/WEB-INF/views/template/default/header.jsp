<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="header">
    <ul class="navigation">
        <li class="menu-item">
            <a href="/" class="nav-link">Dashboard</a>
        </li>
        <security:authorize access="hasAnyRole('ROLE_USER', 'ROLE_ADMIN')">
            <li class="dropdown menu-item">
                <a href="#" class="dropdown-toggle nav-link" data-toggle="dropdown" role="button" aria-haspopup="true"
                   aria-expanded="false">Projects<span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <c:forEach var="member" items="${projects}">
                        <li><a href="#">${member.project.name}</a></li>
                    </c:forEach>
                </ul>
            </li>
            <li class="dropdown menu-item">
                <a href="#" class="dropdown-toggle nav-link" data-toggle="dropdown" role="button" aria-haspopup="true"
                   aria-expanded="true">Issues<span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li><a href="#">Issue 1</a></li>
                </ul>
            </li>
            <li class="button nav-button menu-item"><a href="#" class="nav-link">Create Issue</a></li>
            <li class="account-button dropdown menu-item">
                <a href="#" class="dropdown-toggle nav-link" data-toggle="dropdown" role="button" aria-haspopup="true"
                   aria-expanded="true">${userinfo}<span class="caret"></span></a>
                <ul class="dropdown-menu dropdown-menu-right">
                    <li><a href="#">Profile</a></li>
                    <security:authorize access="hasRole('ROLE_ADMIN')">
                        <li><a href="#">Admin page</a></li>
                    </security:authorize>
                    <li role="separator" class="divider"></li>
                    <li><a href="/j_spring_security_logout">Log Out</a></li>
                </ul>
            </li>
        </security:authorize>
        <security:authorize access="isAnonymous()">
            <li class="account-button menu-item"><a href="/login" class="nav-link">Sign In</a></li>
        </security:authorize>
    </ul>
</div>
