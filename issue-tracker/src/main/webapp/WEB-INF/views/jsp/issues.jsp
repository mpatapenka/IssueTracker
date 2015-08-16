<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<tiles:insertDefinition name="hftemplate">
    <tiles:putAttribute name="content">
        <div class="content-header">
            <a href="<c:url value="/issues?id=${assign.id}&export"/>" class="btn btn-default export-btn">
                <span class="glyphicon glyphicon-export"></span>
                Export to XML
            </a>
            <a href="<c:url value="/projects?id=${assign.task.project.id}"/>">${assign.task.project.name}</a> /
            ${assign.task.description}<br>
            ${assign.description}
        </div>
        <div class="content-layout">
            <div class="content-panel">
                <div class="panel-header">
                    Task info
                </div>
                <div class="panel-content">
                    <p><a href="#" class="btn btn-default">Start progress</a></p>
                    <p>Status: <strong>${assign.task.status.name}</strong></p>
                    <p>Assignee: <strong>${assign.member.employee.firstName} ${assign.member.employee.lastName}</strong></p>
                    <hr>
                    <p>Plan start date: <strong>${assign.task.planStartDate}</strong></p>
                    <p>Plan end date: <strong>${assign.task.planEndDate}</strong></p>
                    <hr>
                    <p>Action start date: <strong>${assign.task.actionStartDate}</strong></p>
                    <p>Action end date: <strong>${assign.task.actionEndDate}</strong></p>
                    <a href="#reportModal" data-toggle="modal" class="btn btn-default">Report</a>
                </div>
            </div>
            <div class="content-panel">
                <div class="panel-header">
                    Activity
                </div>
                <div class="panel-content">
                    <c:forEach items="${assign.activities}" var="activity">
                        <div class="activity-item">
                            <strong>${activity.member.employee.firstName} ${activity.member.employee.lastName}</strong>
                                ${activity.comment}<br>
                                ${activity.date} - ${activity.duration} min
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>

        <div class="modal fade" id="reportModal" tabindex="-1" role="dialog"
             aria-labelledby="reportModalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" id="myModalLabel">Report</h4>
                    </div>
                    <div class="modal-body">
                        <sf:form method="post" id="reportForm" modelAttribute="newReport">
                            <sf:input class="project-form-item insertBeforeReportForm" placeholder="Duration" path="duration"/>
                            <sf:textarea class="project-form-item project-form-textarea" placeholder="Comment"
                                         path="comment"/>
                        </sf:form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close
                        </button>
                        <button type="button" class="btn btn-success"
                                onclick="reportIssue(${assign.id})">Report
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </tiles:putAttribute>
</tiles:insertDefinition>
