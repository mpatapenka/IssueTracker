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
                    <div class="panel-content">
                        <c:forEach var="activity" items="${activities}">
                            <p>
                                <strong>${activity.member.employee.login}</strong>
                                ${activity.date}
                                ${activity.comment}
                            </p>
                        </c:forEach>
                        <a href="#" class="button">
                            <span class="btn-panel">Show more...</span>
                        </a>
                    </div>
                </div>
                <div class="content-panel">
                    <div class="panel-header">
                        Assigned to Me
                    </div>
                    <div class="panel-content">

                    </div>
                </div>
            </div>
        </security:authorize>
    </tiles:putAttribute>
</tiles:insertDefinition>