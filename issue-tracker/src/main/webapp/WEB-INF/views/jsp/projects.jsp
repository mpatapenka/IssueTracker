<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<tiles:insertDefinition name="hftemplate">
    <tiles:putAttribute name="content">
        <div class="content-header">
                ${project.name}
        </div>
        <div class="content-layout">
            <div class="content-panel">
                <div class="panel-header">
                    Tasks
                </div>
                <div class="panel-content">
                    <table class="table table-condensed table-hover">
                        <thead>
                            <td>Description</td>
                            <td>Status</td>
                        </thead>
                        <c:forEach var="task" items="${project.tasks}">
                            <tr>
                                <td><a href="#">${task.description}</a></td>
                                <td><a href="#">${task.status.name}</a></td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
            <div class="content-panel">
                <div class="panel-header">
                    Summary
                </div>
                <div class="panel-content">
                    <p>${project.description}</p>
                </div>
            </div>
            <div class="content-panel">
                <div class="panel-header">
                    Project members
                </div>
                <div class="panel-content">
                    <table class="table table-condensed table-hover">
                        <thead>
                        <td>Name</td>
                        <td>Role</td>
                        </thead>
                        <c:forEach var="member" items="${project.members}">
                            <tr>
                                <td><a href="#">${member.employee.firstName} ${member.employee.lastName}</a></td>
                                <td><a href="#">${member.role.name}</a></td>
                            </tr>
                        </c:forEach>
                    </table>
                    <security:authorize access="hasRole('ROLE_ADMIN')">
                        <a href="#addModal" class="button" data-toggle="modal">
                            <span class="btn-panel">Add member</span>
                        </a>

                        <div class="modal fade" id="addModal" tabindex="-1" role="dialog"
                             aria-labelledby="addModalLabel">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span></button>
                                        <h4 class="modal-title" id="myModalLabel">Add new member</h4>
                                    </div>
                                    <div class="modal-body">
                                        <sf:form method="post" id="memberForm" modelAttribute="addMember">
                                            <sf:select class="project-form-item insertBefore" path="employee.id">
                                                <sf:option value="-1">Employee</sf:option>
                                                <c:forEach var="employee" items="${employees}">
                                                    <sf:option value="${employee.id}">
                                                        ${employee.firstName} ${employee.lastName}
                                                    </sf:option>
                                                </c:forEach>
                                            </sf:select>
                                            <sf:select class="project-form-item" path="role.id">
                                                <sf:option value="-1">Role</sf:option>
                                                <c:forEach var="role" items="${roles}">
                                                    <sf:option value="${role.id}">${role.name}</sf:option>
                                                </c:forEach>
                                            </sf:select>
                                        </sf:form>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default" data-dismiss="modal">Close
                                        </button>
                                        <button type="button" class="btn btn-success"
                                                onclick="memberAdd(${project.id})">Add
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
