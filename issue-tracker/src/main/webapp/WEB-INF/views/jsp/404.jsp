<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<tiles:insertDefinition name="ftemplate">
    <tiles:putAttribute name="content">
        <div class="align-wrapper">
            <div class="login-panel">
                <div class="login-panel-header">
                    HTTP Status 404 - <spring:message code="error.title404"/>
                </div>
                <p><spring:message code="error.message404"/></p>
                <div class="login-panel-footer">
                    <spring:message code="label.back-page"/>? <a href="<c:url value="/"/>"><spring:message code="label.go-home"/></a>.
                </div>
            </div>
        </div>
    </tiles:putAttribute>
</tiles:insertDefinition>
