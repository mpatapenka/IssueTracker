<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<!DOCTYPE html>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><spring:message code="app.name"/></title>

    <link rel="stylesheet" href="">
    <link rel="stylesheet" href="">
</head>

<body>
    <tiles:insertAttribute name="header"/>
    <div class="container">
        <tiles:insertAttribute name="content"/>
    </div>
    <tiles:insertAttribute name="footer"/>

    <script src=""></script>
    <script src=""></script>
</body>
</html>
