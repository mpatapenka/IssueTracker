<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
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
        </div>
    </tiles:putAttribute>
</tiles:insertDefinition>