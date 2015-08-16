<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<tiles:insertDefinition name="ftemplate">
    <tiles:putAttribute name="content">
        <div class="align-wrapper">
            <div class="login-panel">
                <div class="login-panel-header">
                    HTTP Status 404 - Page Not Found
                </div>
                <p>Sorry, the requested resource is not found.</p>
                <div class="login-panel-footer">
                    Back to start page? <a href="<c:url value="/"/>">Go home</a>.
                </div>
            </div>
        </div>
    </tiles:putAttribute>
</tiles:insertDefinition>
