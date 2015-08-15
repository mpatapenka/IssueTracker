<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<tiles:insertDefinition name="hftemplate">
    <tiles:putAttribute name="content">
        <div class="content-header">
            <a href="#" class="btn btn-default export-btn">
                <span class="glyphicon glyphicon-export"></span>
                Export to XML
            </a>
            <a href="/projects?id=${assign.task.project.id}">${assign.task.project.name}</a> /
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
                    <a href="#" class="btn btn-default">Report</a>
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
    </tiles:putAttribute>
</tiles:insertDefinition>
