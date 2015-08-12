<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<tiles:insertDefinition name="ftemplate">
    <tiles:putAttribute name="content">
        <div class="align-wrapper">
            <div class="login-panel">
                <div class="login-panel-header">
                    Login
                </div>
                <form action="<c:url value='/j_spring_security_check'/>" method="post">
                    <c:if test="${errorMessage != null}">
                        <div class="alert alert-danger alert-dismissible alert-fix alert-danger-fix" role="alert">
                            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                                ${errorMessage}
                        </div>
                    </c:if>
                    <c:if test="${logoutMessage != null}">
                        <div class="alert alert-success alert-dismissible alert-fix alert-success-fix" role="alert">
                            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                                ${logoutMessage}
                        </div>
                    </c:if>
                    <input type="text" class="text-field" name="username" placeholder="Username">
                    <input type="password" class="text-field" name="password" placeholder="Password">
                    <input type="submit" class="login-button" value="Sign in">
                </form>
                <div class="login-panel-footer">
                    Back to start page? <a href="/">Dashboard</a>.
                </div>
            </div>
        </div>
    </tiles:putAttribute>
</tiles:insertDefinition>
