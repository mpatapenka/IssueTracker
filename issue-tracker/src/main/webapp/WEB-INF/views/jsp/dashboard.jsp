<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<tiles:insertDefinition name="hftemplate">
    <tiles:putAttribute name="content">
        <div class="content-header">
            System Dashboard
        </div>
        <security:authorize access="isAnonymous()">
            <div class="content-layout">
                <div class="content-panel">
                    <div class="panel-header">
                        Introdution
                    </div>
                    <div class="panel-content">
                        <p>
                            <strong>Welcome to Issue Tracker</strong>
                        </p>
                        <p>
                            Please sign in.
                        </p>
                    </div>
                </div>
            </div>
        </security:authorize>
        <security:authorize access="hasAnyRole('ROLE_USER', 'ROLE_ADMIN')">
            <div class="content-layout">
                <div class="content-panel">
                    <div class="panel-header">
                        Activity Stream
                    </div>
                    <div class="panel-content activity-panel">
                        <c:forEach var="activity" items="${lastActivities}">
                            <div class="activity-item">
                                <strong>${activity.member.employee.firstName} ${activity.member.employee.lastName}</strong>
                                    ${activity.comment}<br>
                                    ${activity.date}
                            </div>
                        </c:forEach>
                        <a id="show-more-btn" href="/user/dashboard/activity" class="button show-more-btn">
                            <span class="btn-panel">Show more...</span>
                        </a>
                    </div>
                </div>
                <div class="content-panel">
                    <div class="panel-header">
                        Assigned to Me
                    </div>
                    <div class="panel-content">
                        <table class="table table-condensed table-hover">
                            <thead>
                                <td>Key</td>
                                <td>Summary</td>
                            </thead>
                            <c:forEach var="assign" items="${assignToMe}">
                                <tr>
                                    <td><a href="/projects?id=${assign.task.project.id}">${assign.task.project.name}</a></td>
                                    <td><a href="#">${assign.task.description}</a></td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </div>
            </div>
        </security:authorize>
    </tiles:putAttribute>
</tiles:insertDefinition>