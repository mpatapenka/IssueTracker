<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="header">
    <ul class="navigation">
        <security:authorize access="isAnonymous()">
            <li class="menu-item">
                <a href="<c:url value="/"/>" class="nav-link"><spring:message code="header.preview-dashboard"/></a>
            </li>
        </security:authorize>
        <security:authorize access="hasRole('ROLE_ADMIN')">
            <li class="menu-item">
                <a href="<c:url value="/"/>" class="nav-link"><spring:message code="header.admin-panel"/></a>
            </li>
        </security:authorize>
        <security:authorize access="hasRole('ROLE_USER')">
            <li class="menu-item">
                <a href="<c:url value="/"/>" class="nav-link"><spring:message code="header.dashboard"/></a>
            </li>
            <li class="dropdown menu-item">
                <a href="#" class="dropdown-toggle nav-link" data-toggle="dropdown" role="button" aria-haspopup="true"
                   aria-expanded="false"><spring:message code="label.projects"/><span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <c:if test="${empty members}">
                        <li><a href="#"><spring:message code="label.no-one-project"/></a></li>
                    </c:if>
                    <c:forEach var="member" items="${members}">
                        <li><a href="<c:url value="/projects?id=${member.project.id}"/>">${member.project.name}</a></li>
                    </c:forEach>
                </ul>
            </li>
        </security:authorize>
        <security:authorize access="hasRole('ROLE_LEAD')">
            <li class="button nav-button menu-item"><a href="#issueModal" data-toggle="modal" class="nav-link"><spring:message code="label.create-issue"/></a></li>
        </security:authorize>
        <li class="account-button dropdown menu-item">
            <a href="#" class="dropdown-toggle nav-link" data-toggle="dropdown" role="button" aria-haspopup="true"
               aria-expanded="true"><spring:message code="label.lang"/><span class="caret"></span></a>
            <ul class="dropdown-menu dropdown-menu-right">
                <li><a href="<c:url value="/?lang=en"/>"><spring:message code="label.eng"/></a></li>
                <li><a href="<c:url value="/?lang=ru"/>"><spring:message code="label.rus"/></a></li>
            </ul>
        </li>
        <security:authorize access="isAuthenticated()">
            <li class="account-button dropdown menu-item">
                <a href="#" class="dropdown-toggle nav-link" data-toggle="dropdown" role="button" aria-haspopup="true"
                   aria-expanded="true">${user}<span class="caret"></span></a>
                <ul class="dropdown-menu dropdown-menu-right">
                    <li><a href="<c:url value="/j_spring_security_logout"/>"><spring:message code="label.logout"/></a></li>
                </ul>
            </li>
        </security:authorize>
        <security:authorize access="isAnonymous()">
            <li class="account-button menu-item"><a href="<c:url value="/login"/>" class="nav-link"><spring:message code="label.signin"/></a></li>
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
                    <h4 class="modal-title" id="myModalLabel"><spring:message code="label.create-issue"/></h4>
                </div>
                <div class="modal-body">
                    <sf:form method="post" id="issueForm" modelAttribute="newAssign">
                        <sf:select class="project-form-item insertBeforeIssueForm" path="member.project.id"
                                   onchange="loadEmployees(this)">
                            <sf:option value="-1"><spring:message code="forms.project"/></sf:option>
                            <c:forEach var="proj" items="${allProjects}">
                                <sf:option value="${proj.id}">
                                    ${proj.name}
                                </sf:option>
                            </c:forEach>
                        </sf:select>
                        <sf:select id="projMembers" class="project-form-item" path="member.id">
                            <sf:option cssClass="insertAfter" value="-1"><spring:message code="forms.assignee"/></sf:option>
                        </sf:select>
                        <sf:input id="psd" class="project-form-item datepicker" path="task.planStartDate" placeholder="Plan start date"/>
                        <sf:input id="ped" class="project-form-item datepicker" path="task.planEndDate" placeholder="Plan end date"/>
                        <sf:textarea class="project-form-item project-form-textarea" placeholder="Description"
                                     path="task.description"/>
                    </sf:form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="modal.cancel-btn"/></button>
                    <button type="button" class="btn btn-success"
                            onclick="createIssue()"><spring:message code="modal.create-btn"/>
                    </button>
                </div>
            </div>
        </div>
    </div>
</security:authorize>
