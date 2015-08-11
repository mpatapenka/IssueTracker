<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<tiles:insertDefinition name="hftemplate">
    <tiles:putAttribute name="content">
        <div class="content-header">
            ${project.name}
        </div>
        <div class="content-layout">
            <div class="content-panel">
                <div class="panel-header">
                    Summary
                </div>
                <div class="panel-content">
                    <p>${project.description}</p>
                </div>
            </div>
            <div class="content-panel">
                <div class="panel-header">
                    Project members
                </div>
                <div class="panel-content">
                    <table class="table table-condensed table-hover">
                        <thead>
                        <td>Name</td>
                        <td>Role</td>
                        </thead>
                        <c:forEach var="member" items="${project.members}">
                            <tr>
                                <td><a href="#">${member.employee.firstName} ${member.employee.lastName}</a></td>
                                <td><a href="#">${member.role.name}</a></td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
        </div>
    </tiles:putAttribute>
</tiles:insertDefinition>
