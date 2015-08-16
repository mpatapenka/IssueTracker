<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<tiles:insertDefinition name="hftemplate">
    <tiles:putAttribute name="content">
        <security:authorize access="isAnonymous()">
            <div class="content-header">
                Preview Dashboard
            </div>
            <div class="content-layout">
                <div class="content-panel">
                    <div class="panel-header">
                        Introduction
                    </div>
                    <div class="panel-content">
                        <p><strong>Welcome to Issue Tracker</strong></p>

                        <p>Please sign in.</p>
                    </div>
                </div>
                <div class="content-panel">
                    <div class="panel-header">
                        Additional info
                    </div>
                    <div class="panel-content">
                        <p>If you don't have an account, please contact your administrator.</p>
                    </div>
                </div>
            </div>
        </security:authorize>
        <security:authorize access="hasRole('ROLE_USER')">
            <div class="content-header">
                System Dashboard
            </div>
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
                        <a id="show-more-btn" href="<c:url value="/dashboard/activity"/>" class="button show-more-btn">
                            <span class="btn-panel">Show more...</span>
                        </a>
                    </div>
                </div>
                <div class="content-panel">
                    <div class="panel-header">
                        Assigned to Me
                    </div>
                    <div class="panel-content">
                        <c:if test="${empty assignToMe}">
                            <p>You currently have no <a href="<c:url value="/issues"/>">issues</a> assigned to you.
                                Enjoy your day!</p>
                        </c:if>
                        <c:if test="${not empty assignToMe}">
                            <table class="table table-condensed table-hover">
                                <thead>
                                    <td>Key</td>
                                    <td>Summary</td>
                                </thead>
                                <c:forEach var="assign" items="${assignToMe}">
                                    <tr>
                                        <td>
                                            <a href="<c:url value="/projects?id=${assign.task.project.id}"/>">${assign.task.project.name}</a>
                                        </td>
                                        <td>
                                            <a href="<c:url value="/issues?id=${assign.task.id}"/>">${assign.task.description}</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </c:if>
                    </div>
                </div>
            </div>
        </security:authorize>
    </tiles:putAttribute>
</tiles:insertDefinition>