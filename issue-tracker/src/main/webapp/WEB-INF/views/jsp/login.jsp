<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<div>
    <form method="post" action="/">
        <label for="userLogin"><spring:message code="login.userLogin"/></label>
        <input type="text" id="userLogin" name="userLogin" placeholder="<spring:message code="login.userLogin"/>">

        <label for="userPassword"><spring:message code="login.userPassword"/></label>
        <input type="password" id="userPassword" name="userPassword"
               placeholder="<spring:message code="login.userPassword"/>">

        <input type="submit" value="<spring:message code="login.loginButton"/>"/>
    </form>
</div>
