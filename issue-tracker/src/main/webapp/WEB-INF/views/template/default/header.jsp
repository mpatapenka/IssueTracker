<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<div class="header">
    <ul class="navigation">
        <security:authorize access="isAnonymous()">
            <li class="menu-item">
                <a href="<c:url value="/"/>" class="nav-link">Preview dashboard</a>
            </li>
        </security:authorize>
        <security:authorize access="hasRole('ROLE_ADMIN')">
            <li class="menu-item">
                <a href="<c:url value="/"/>" class="nav-link">Admin panel</a>
            </li>
        </security:authorize>
        <security:authorize access="hasRole('ROLE_USER')">
            <li class="menu-item">
                <a href="<c:url value="/"/>" class="nav-link">Dashboard</a>
            </li>
            <li class="dropdown menu-item">
                <a href="#" class="dropdown-toggle nav-link" data-toggle="dropdown" role="button" aria-haspopup="true"
                   aria-expanded="false">Projects<span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <c:if test="${empty members}">
                        <li><a href="#">No projects for me</a></li>
                    </c:if>
                    <c:forEach var="member" items="${members}">
                        <li><a href="<c:url value="/projects?id=${member.project.id}"/>">${member.project.name}</a></li>
                    </c:forEach>
                </ul>
            </li>
        </security:authorize>
        <security:authorize access="hasRole('ROLE_LEAD')">
            <li class="button nav-button menu-item"><a href="#issueModal" data-toggle="modal" class="nav-link">Create
                Issue</a></li>
        </security:authorize>
        <security:authorize access="isAuthenticated()">
            <li class="account-button dropdown menu-item">
                <a href="#" class="dropdown-toggle nav-link" data-toggle="dropdown" role="button" aria-haspopup="true"
                   aria-expanded="true">${user}<span class="caret"></span></a>
                <ul class="dropdown-menu dropdown-menu-right">
                    <li><a href="<c:url value="/j_spring_security_logout"/>">Log out</a></li>
                </ul>
            </li>
        </security:authorize>
        <security:authorize access="isAnonymous()">
            <li class="account-button menu-item"><a href="<c:url value="/login"/>" class="nav-link">Sign In</a></li>
        </security:authorize>
    </ul>
</div>

<security:authorize access="hasRole('ROLE_LEAD')">
    <div class="modal fade" id="issueModal" tabindex="-1" role="dialog"
         aria-labelledby="issueModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">Create issue</h4>
                </div>
                <div class="modal-body">
                    <sf:form method="post" id="issueForm" modelAttribute="newAssign">
                        <sf:select class="project-form-item insertBeforeIssueForm" path="member.project.id"
                                   onchange="loadEmployees(this)">
                            <sf:option value="-1">Project</sf:option>
                            <c:forEach var="proj" items="${allProjects}">
                                <sf:option value="${proj.id}">
                                    ${proj.name}
                                </sf:option>
                            </c:forEach>
                        </sf:select>
                        <sf:select id="projMembers" class="project-form-item" path="member.id">
                            <sf:option cssClass="insertAfter" value="-1">Assignee</sf:option>
                        </sf:select>
                        <sf:input id="psd" class="project-form-item datepicker" path="task.planStartDate" placeholder="Plan start date"/>
                        <sf:input id="ped" class="project-form-item datepicker" path="task.planEndDate" placeholder="Plan end date"/>
                        <sf:textarea class="project-form-item project-form-textarea" placeholder="Description"
                                     path="task.description"/>
                    </sf:form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Cancel
                    </button>
                    <button type="button" class="btn btn-success"
                            onclick="createIssue()">Create
                    </button>
                </div>
            </div>
        </div>
    </div>
</security:authorize>
