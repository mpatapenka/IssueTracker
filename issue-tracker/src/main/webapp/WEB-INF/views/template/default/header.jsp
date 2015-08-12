<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="header">
    <ul class="navigation">
        <li class="menu-item">
            <a href="/" class="nav-link">Dashboard</a>
        </li>
        <security:authorize access="isAuthenticated()">
            <li class="dropdown menu-item">
                <a href="#" class="dropdown-toggle nav-link" data-toggle="dropdown" role="button" aria-haspopup="true"
                   aria-expanded="false">Projects<span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <c:if test="${empty members}">
                        <li><a href="#">No projects for me</a></li>
                    </c:if>
                    <c:forEach var="member" items="${members}">
                        <li><a href="/projects?id=${member.project.id}">${member.project.name}</a></li>
                    </c:forEach>
                </ul>
            </li>
        </security:authorize>
        <security:authorize access="hasRole('ROLE_USER')">
            <li class="menu-item">
                <a href="#" class="nav-link">Issues</a>
            </li>
            <li class="button nav-button menu-item"><a href="#" class="nav-link">Create Issue</a></li>
        </security:authorize>
        <security:authorize access="isAuthenticated()">
            <li class="account-button dropdown menu-item">
                <a href="#" class="dropdown-toggle nav-link" data-toggle="dropdown" role="button" aria-haspopup="true"
                   aria-expanded="true">${userFullName}<span class="caret"></span></a>
                <ul class="dropdown-menu dropdown-menu-right">
                    <security:authorize access="hasRole('ROLE_ADMIN')">
                        <li><a href="/admin/panel">Admin panel</a></li>
                        <li role="separator" class="divider"></li>
                    </security:authorize>
                    <li><a href="/j_spring_security_logout">Log Out</a></li>
                </ul>
            </li>
        </security:authorize>
        <security:authorize access="isAnonymous()">
            <li class="account-button menu-item"><a href="/login" class="nav-link">Sign In</a></li>
        </security:authorize>
    </ul>
</div>
