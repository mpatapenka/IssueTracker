<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<!DOCTYPE html>
<html class="height-fix">
<head>
    <meta charset="UTF-8">
    <title><spring:message code="app.name"/></title>
    <link href="<spring:url value='/resources/core/css/styles.css'/>" rel="stylesheet" type="text/css">
</head>

<body class="height-fix">
    <div class="main">
        <tiles:insertAttribute name="content"/>
    </div>
    <tiles:insertAttribute name="footer"/>
</body>
</html>
