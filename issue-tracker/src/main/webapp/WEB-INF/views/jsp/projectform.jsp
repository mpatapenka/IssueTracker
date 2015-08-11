<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<tiles:insertDefinition name="hftemplate">
    <tiles:putAttribute name="content">
        <div class="content-header">
            Create new project
        </div>
        <div class="content-layout">
            <div class="content-panel">
                <div class="panel-header">
                    Project info
                </div>
                <div class="panel-content">
                    <sf:form method="post" modelAttribute="project">
                        <sf:input class="project-form-item" placeholder="Project name" path="name"/>
                        <sf:errors cssClass="alert-fix alert-danger-fix" path="name"/>

                        <sf:textarea class="project-form-item project-form-textarea" placeholder="Description" path="description"/>
                        <sf:errors cssClass="alert-fix alert-danger-fix" path="description"/>

                        <input type="submit" class="default-form-btn" value="Add">
                    </sf:form>
                </div>
            </div>
        </div>
    </tiles:putAttribute>
</tiles:insertDefinition>
