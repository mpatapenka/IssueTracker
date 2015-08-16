<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
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
                        <a class="button nav-button menu-item nav-link" href="<c:url value="/register"/>" role="button">Create
                            new user</a>
                    </p>

                    <p>
                        <a class="button nav-button menu-item nav-link" href="#projectModal" data-toggle="modal"
                           role="button">Create new project</a>
                    </p>
                </div>
            </div>
            <div class="content-panel">
                <div class="panel-header">
                    All projects
                </div>
                <div class="panel-content">
                    <div class="scrollable-panel-content">
                        <table class="table table-condensed table-hover">
                            <thead>
                            <td>Name</td>
                            <td>Description</td>
                            </thead>
                            <c:forEach var="project" items="${allProjects}">
                                <tr>
                                    <td><a href="<c:url value="/projects?id=${project.id}"/>">${project.name}</a></td>
                                    <td><a href="#">${project.description}</a></td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </div>
            </div>
        </div>

        <div class="modal fade" id="projectModal" tabindex="-1" role="dialog"
             aria-labelledby="projectModalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" id="myModalLabel">Create new project</h4>
                    </div>
                    <div class="modal-body">
                        <sf:form method="post" id="newProjectForm" modelAttribute="newProject">
                            <sf:input class="project-form-item insertBefore" placeholder="Project name" path="name"/>
                            <sf:textarea class="project-form-item project-form-textarea" placeholder="Description"
                                         path="description"/>
                        </sf:form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close
                        </button>
                        <button type="button" class="btn btn-success"
                                onclick="projectAdd()">Add
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </tiles:putAttribute>
</tiles:insertDefinition>