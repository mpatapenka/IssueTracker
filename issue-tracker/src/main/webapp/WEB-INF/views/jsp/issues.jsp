<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<tiles:insertDefinition name="hftemplate">
    <tiles:putAttribute name="content">
        <div class="content-header">
            <a href="<c:url value="/issues?id=${assign.id}&export"/>" class="btn btn-default export-btn">
                <span class="glyphicon glyphicon-export"></span>
                <spring:message code="label.export-xml"/>
            </a>
            <a href="<c:url value="/projects?id=${assign.task.project.id}"/>">${assign.task.project.name}</a> /
                ${assign.task.description}<br>
                ${assign.description}
        </div>
        <div class="content-layout">
            <div class="content-panel">
                <div class="panel-header">
                    <spring:message code="panel.task-info"/>
                </div>
                <div class="panel-content">
                    <p>
                        <security:authorize access="hasRole('ROLE_LEAD')">
                            <a href="#reassignModal" data-toggle="modal" class="btn btn-default"><spring:message code="forms.assignee"/></a>
                        </security:authorize>
                        <a href="JavaScript:transferIssueStatus('${assign.id}', 'start-progress')"
                           class="btn btn-default"><spring:message code="label.start-progress"/></a>
                        <a href="JavaScript:transferIssueStatus('${assign.id}', 'delivery')" class="btn btn-default"><spring:message code="label.delivery"/></a>
                        <security:authorize access="hasRole('ROLE_LEAD')">
                            <a href="JavaScript:transferIssueStatus('${assign.id}', 'done')" class="btn btn-success"><spring:message code="label.resolve"/></a>
                            <a href="JavaScript:transferIssueStatus('${assign.id}', 'reject')" class="btn btn-danger"><spring:message code="label.reject"/></a>
                        </security:authorize>
                    </p>

                    <p><spring:message code="label.status"/>: <strong>${assign.task.status.name}</strong></p>

                    <p><spring:message code="forms.assignee"/>:
                        <c:if test="${empty assign.member}">
                            &ndash;
                        </c:if>
                        <c:if test="${not empty assign.member}">
                            <strong>${assign.member.employee.firstName} ${assign.member.employee.lastName}</strong>
                        </c:if>
                    </p>
                    <hr>
                    <p><spring:message code="forms.plan-start-date"/>: <strong>${assign.task.planStartDate}</strong></p>

                    <p><spring:message code="forms.plan-end-date"/>: <strong>${assign.task.planEndDate}</strong></p>
                    <hr>
                    <p><spring:message code="forms.action-start-date"/>:
                        <c:if test="${empty assign.task.actionStartDate}">
                            &ndash;
                        </c:if>
                        <strong>${assign.task.actionStartDate}</strong>
                    </p>

                    <p><spring:message code="forms.action-end-date"/>:
                        <c:if test="${empty assign.task.actionEndDate}">
                            &ndash;
                        </c:if>
                        <strong>${assign.task.actionEndDate}</strong>
                    </p>
                    <a href="#reportModal" data-toggle="modal" class="btn btn-default"><spring:message code="label.report"/></a>
                </div>
            </div>
            <div class="content-panel">
                <div class="panel-header">
                    <spring:message code="label.activity"/>
                </div>
                <div class="panel-content">
                    <c:if test="${empty assign.activities}">
                        <p><spring:message code="panel.no-activity"/>.</p>
                    </c:if>
                    <c:if test="${not empty assign.activities}">
                        <div class="scrollable-panel-content">
                            <c:forEach items="${assign.activities}" var="activity">
                                <div class="activity-item">
                                    <strong>${activity.member.employee.firstName} ${activity.member.employee.lastName}</strong>
                                        ${activity.comment}<br>
                                    <span class="label label-default">${activity.date}</span> -
                                    <span class="label label-success">${activity.duration} <spring:message code="label.minutes"/></span>
                                </div>
                            </c:forEach>
                        </div>
                    </c:if>
                </div>
            </div>
            <div class="content-panel">
                <div class="panel-header">
                    <spring:message code="label.attachments"/>
                </div>
                <div class="panel-content">
                    <c:if test="${empty assign.task.attachments}">
                        <p><spring:message code="panel.upload-new"/> <a href="#attachModal" data-toggle="modal"><spring:message code="label.file"/></a>.</p>
                    </c:if>
                    <c:if test="${not empty assign.task.attachments}">
                        <div class="scrollable-panel-content">
                            <c:forEach items="${assign.task.attachments}" var="attach">
                                <div class="activity-item">
                                    <strong><a href="<c:url value="/issues?id=${attach.id}&download-file"/>">${attach.name}</a></strong>
                                        ${attach.description}<br>
                                    <span class="label label-default">
                                        ${attach.size} <spring:message code="label.bytes"/>
                                    </span>
                                </div>
                            </c:forEach>
                            <security:authorize access="hasRole('ROLE_LEAD') OR ${userId eq assign.member.employee.id}">
                                <a href="#attachModal" data-toggle="modal" class="button">
                                    <span class="btn-panel">
                                        <span class="glyphicon glyphicon-cloud-upload"></span>
                                        <spring:message code="modal.attach-new"/></span>
                                </a>
                            </security:authorize>
                        </div>
                    </c:if>
                </div>
            </div>
        </div>

        <security:authorize access="hasRole('ROLE_LEAD') OR ${userId eq assign.member.employee.id}">
            <div class="modal fade" id="attachModal" tabindex="-1" role="dialog"
                 aria-labelledby="attachModalLabel">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title" id="myModalLabel"><spring:message code="modal.attach-new"/></h4>
                        </div>
                        <div class="modal-body">
                            <sf:form method="post" id="attachForm" modelAttribute="newAttach"
                                     enctype="multipart/form-data">
                                <input class="file-upload insertBeforeAttachForm" name="file" type="file"/>
                                <sf:textarea class="project-form-item project-form-textarea" placeholder="Description"
                                             path="description"/>
                            </sf:form>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="modal.cancel-btn"/>
                            </button>
                            <button type="button" class="btn btn-success"
                                    onclick="attachFile(${assign.id})"><spring:message code="label.attach"/>
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </security:authorize>

        <div class="modal fade" id="reportModal" tabindex="-1" role="dialog"
             aria-labelledby="reportModalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" id="myModalLabel"><spring:message code="label.report"/></h4>
                    </div>
                    <div class="modal-body">
                        <sf:form method="post" id="reportForm" modelAttribute="newReport">
                            <sf:input class="project-form-item insertBeforeReportForm" placeholder="Duration"
                                      path="duration"/>
                            <sf:textarea class="project-form-item project-form-textarea" placeholder="Comment"
                                         path="comment"/>
                        </sf:form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="modal.cancel-btn"/>
                        </button>
                        <button type="button" class="btn btn-success"
                                onclick="reportIssue(${assign.id})"><spring:message code="label.report"/>
                        </button>
                    </div>
                </div>
            </div>
        </div>

        <security:authorize access="hasRole('ROLE_LEAD')">
            <div class="modal fade" id="reassignModal" tabindex="-1" role="dialog"
                 aria-labelledby="reassignModalLabel">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title" id="myModalLabel"><spring:message code="label.assign"/></h4>
                        </div>
                        <div class="modal-body">
                            <sf:form method="post" id="reassignForm" modelAttribute="reAssign">
                                <sf:select class="project-form-item insertBeforeReassignForm" path="member.id">
                                    <sf:option value="-1"><spring:message code="forms.assignee"/></sf:option>
                                    <c:forEach var="member" items="${assign.task.project.members}">
                                        <sf:option value="${member.id}">
                                            ${member.employee.firstName} ${member.employee.lastName}
                                        </sf:option>
                                    </c:forEach>
                                </sf:select>
                                <sf:textarea class="project-form-item project-form-textarea" placeholder="Description"
                                             path="description"/>
                            </sf:form>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="modal.cancel-btn"/>
                            </button>
                            <button type="button" class="btn btn-success"
                                    onclick="reassignIssue(${assign.id})"><spring:message code="label.assign"/>
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </security:authorize>
    </tiles:putAttribute>
</tiles:insertDefinition>
