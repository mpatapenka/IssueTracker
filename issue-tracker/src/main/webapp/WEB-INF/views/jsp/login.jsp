<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<tiles:insertDefinition name="ftemplate">
    <tiles:putAttribute name="content">
        <div class="align-wrapper">
            <div class="login-panel">
                <div class="login-panel-header">
                    Issue-Tracker Auth
                </div>
                <form name="form-login" action="#">
                    <input type="text" class="text-field" name="username" placeholder="Username">
                    <input type="password" class="text-field" name="password" placeholder="Password">
                    <input type="submit" class="login-button" value="Log in">
                </form>
                <div class="login-panel-footer">
                    Need an account? <a href="#">Sign up</a>.
                </div>
            </div>
        </div>
    </tiles:putAttribute>
</tiles:insertDefinition>
