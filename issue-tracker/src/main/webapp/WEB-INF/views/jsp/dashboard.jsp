<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<tiles:insertDefinition name="hftemplate">
    <tiles:putAttribute name="content">
        <security:authorize access="isAnonymous()">
            <div class="content-header">
                <spring:message code="header.preview-dashboard"/>
            </div>
            <div class="content-layout">
                <div class="content-panel">
                    <div class="panel-header">
                        <spring:message code="panel.introduction"/>
                    </div>
                    <div class="panel-content">
                        <p><strong><spring:message code="label.welcome"/> <spring:message code="app.name"/></strong></p>
                        <p><spring:message code="panel.anonim-info"/>.</p>
                    </div>
                </div>
                <div class="content-panel">
                    <div class="panel-header">
                        <spring:message code="panel.additional-info"/>
                    </div>
                    <div class="panel-content">
                        <p><spring:message code="panel.additional-info-msg"/>.</p>
                    </div>
                </div>
            </div>
        </security:authorize>
        <security:authorize access="hasRole('ROLE_USER')">
            <div class="content-header">
                <spring:message code="label.system-dashboard"/>
            </div>
            <div class="content-layout">
                <div class="content-panel">
                    <div class="panel-header">
                        <spring:message code="panel.activity-stream"/>
                    </div>
                    <div class="panel-content activity-panel">
                        <c:if test="${empty lastActivities}">
                            <p><spring:message code="panel.no-activity-msg"/>.</p>
                        </c:if>
                        <c:if test="${not empty lastActivities}">
                            <c:forEach var="activity" items="${lastActivities}">
                                <div class="activity-item">
                                    <strong>${activity.member.employee.firstName} ${activity.member.employee.lastName}</strong>
                                        ${activity.comment}<br>
                                        <span class="label label-default">${activity.date}</span> -
                                        <span class="label label-success">${activity.duration} min</span>
                                </div>
                            </c:forEach>
                            <c:if test="${lastActivities.size() eq 5}">
                                <a id="show-more-btn" href="<c:url value="/dashboard/activity"/>" class="button show-more-btn">
                                    <span class="btn-panel"><spring:message code="forms.show-more"/>...</span>
                                </a>
                            </c:if>
                        </c:if>
                    </div>
                </div>
                <div class="content-panel">
                    <div class="panel-header">
                        <spring:message code="panel.assigned-me"/>
                    </div>
                    <div class="panel-content">
                        <div class="scrollable-panel-content">
                            <c:if test="${empty assignToMe}">
                                <p><spring:message code="panel.assigned-me-msg"/>!</p>
                            </c:if>
                            <c:if test="${not empty assignToMe}">
                                <table class="table table-condensed table-hover">
                                    <thead>
                                        <td><spring:message code="label.key"/></td>
                                        <td><spring:message code="label.summary"/></td>
                                    </thead>
                                    <c:forEach var="assign" items="${assignToMe}">
                                        <tr>
                                            <td>
                                                <a href="<c:url value="/projects?id=${assign.task.project.id}"/>">${assign.task.project.name}</a>
                                            </td>
                                            <td>
                                                <a href="<c:url value="/issues?id=${assign.id}"/>">${assign.task.description}</a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </table>
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>
        </security:authorize>
    </tiles:putAttribute>
</tiles:insertDefinition>