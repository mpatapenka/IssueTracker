<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<tiles:insertDefinition name="hftemplate">
    <tiles:putAttribute name="content">
        <div class="content-header">
            ${project.name}
        </div>
        <div class="content-layout">
            <div class="content-panel">
                <div class="panel-header">
                    <spring:message code="label.tasks"/>
                </div>
                <div class="panel-content">
                    <c:if test="${empty issues}">
                        <p><spring:message code="panel.no-issues-msg"/>.</p>
                    </c:if>
                    <c:if test="${not empty issues}">
                        <table class="table table-condensed table-hover">
                            <c:if test="${not empty project.tasks}">
                                <thead>
                                    <td><spring:message code="label.description"/></td>
                                    <td><spring:message code="label.status"/></td>
                                </thead>
                            </c:if>
                            <c:forEach var="issue" items="${issues}">
                                <tr>
                                    <td>
                                        <a href="<c:url value="/issues?id=${issue.id}"/>">${issue.task.description}</a>
                                    </td>
                                    <td><a href="#">${issue.task.status.name}</a></td>
                                </tr>
                            </c:forEach>
                        </table>
                    </c:if>
                </div>
            </div>
            <div class="content-panel">
                <div class="panel-header">
                    <spring:message code="label.summary"/>
                </div>
                <div class="panel-content">
                    <p>${project.description}</p>
                </div>
            </div>
            <div class="content-panel">
                <div class="panel-header">
                    <spring:message code="panel.project-members"/>
                </div>
                <div class="panel-content">
                    <c:if test="${empty project.members}">
                        <p><spring:message code="panel.no-project-members"/>.</p>
                    </c:if>
                    <c:if test="${not empty project.members}">
                        <table class="table table-condensed table-hover">
                            <c:if test="${not empty project.members}">
                                <thead>
                                    <td><spring:message code="label.name"/></td>
                                    <td><spring:message code="label.status"/></td>
                                </thead>
                            </c:if>
                            <c:forEach var="member" items="${project.members}">
                                <tr>
                                    <td><a href="#">${member.employee.firstName} ${member.employee.lastName}</a></td>
                                    <td><a href="#">${member.role.name}</a></td>
                                </tr>
                            </c:forEach>
                        </table>
                    </c:if>
                    <security:authorize access="hasRole('ROLE_ADMIN')">
                        <a href="#addModal" class="button" data-toggle="modal">
                            <span class="btn-panel"><spring:message code="label.add-member"/></span>
                        </a>
                        <div class="modal fade" id="addModal" tabindex="-1" role="dialog"
                             aria-labelledby="addModalLabel">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span></button>
                                        <h4 class="modal-title" id="myModalLabel"><spring:message code="label.add-member"/></h4>
                                    </div>
                                    <div class="modal-body">
                                        <sf:form method="post" id="memberForm" modelAttribute="addMember">
                                            <sf:select class="project-form-item insertBefore" path="employee.id">
                                                <sf:option value="-1"><spring:message code="label.employee"/></sf:option>
                                                <c:forEach var="employee" items="${employees}">
                                                    <sf:option value="${employee.id}">
                                                        ${employee.firstName} ${employee.lastName}
                                                    </sf:option>
                                                </c:forEach>
                                            </sf:select>
                                            <sf:select class="project-form-item" path="role.id">
                                                <sf:option value="-1"><spring:message code="label.role"/></sf:option>
                                                <c:forEach var="role" items="${roles}">
                                                    <sf:option value="${role.id}">${role.name}</sf:option>
                                                </c:forEach>
                                            </sf:select>
                                        </sf:form>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="modal.cancel-btn"/>
                                        </button>
                                        <button type="button" class="btn btn-success"
                                                onclick="memberAdd(${project.id})"><spring:message code="modal.add-btn"/>
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </security:authorize>
                </div>
            </div>
        </div>
    </tiles:putAttribute>
</tiles:insertDefinition>
