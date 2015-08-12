<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<tiles:insertDefinition name="hftemplate">
    <tiles:putAttribute name="content">
        <div class="content-header">
            Admin panel
        </div>
        <div class="content-layout">
            <div class="content-panel">
                <div class="panel-header">
                    Functions
                </div>
                <div class="panel-content">
                    <p>
                        <a class="button nav-button menu-item nav-link" href="/admin/register" role="button">Create
                            new user</a>
                    </p>

                    <p>
                        <a class="button nav-button menu-item nav-link" href="/projects?new" role="button">Create
                            new project</a>
                    </p>
                </div>
            </div>
            <div class="content-panel">
                <div class="panel-header">
                    All projects
                </div>
                <div class="panel-content">
                    <table class="table table-condensed table-hover">
                        <thead>
                            <td>Name</td>
                            <td>Description</td>
                        </thead>
                        <c:forEach var="project" items="${allProjects}">
                            <tr>
                                <td><a href="/projects?id=${project.id}">${project.name}</a></td>
                                <td><a href="#">${project.description}</a></td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
        </div>
    </tiles:putAttribute>
</tiles:insertDefinition>