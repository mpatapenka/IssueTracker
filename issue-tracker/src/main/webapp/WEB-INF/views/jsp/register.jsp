<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<tiles:insertDefinition name="ftemplate">
    <tiles:putAttribute name="content">
        <div class="align-wrapper">
            <div class="login-panel">
                <div class="login-panel-header">
                    Register new user
                </div>
                <sf:form method="post" modelAttribute="employee">
                    <c:if test="${errorMessage != null}">
                        <div class="alert alert-danger alert-dismissible alert-fix alert-danger-fix" role="alert">
                            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                                ${errorMessage}
                        </div>
                    </c:if>
                    <sf:input class="text-field" placeholder="First name" path="firstName"/>
                    <sf:errors cssClass="alert-fix alert-danger-fix" path="firstName"/>

                    <sf:input class="text-field" placeholder="Last name" path="lastName"/>
                    <sf:errors cssClass="alert-fix alert-danger-fix" path="lastName"/>

                    <sf:input class="text-field" placeholder="Login" path="login"/>
                    <sf:errors cssClass="alert-fix alert-danger-fix" path="login"/>

                    <sf:password class="text-field" placeholder="Password" path="password"/>
                    <sf:errors cssClass="alert-fix alert-danger-fix" path="password"/>

                    <sf:select class="text-field" placeholder="Position" path="position.id">
                        <c:forEach var="pos" items="${positions}">
                            <sf:option value="${pos.id}">${pos.name}</sf:option>
                        </c:forEach>
                    </sf:select>
                    <sf:errors cssClass="alert-fix alert-danger-fix" path="position"/>
                    <input type="submit" class="login-button" value="Sign up">
                </sf:form>
                <div class="login-panel-footer">
                    Back to start page? <a href="/">Dashboard</a>.
                </div>
            </div>
        </div>
    </tiles:putAttribute>
</tiles:insertDefinition>
