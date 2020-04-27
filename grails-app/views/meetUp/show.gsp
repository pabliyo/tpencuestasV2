<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'meetUp.label', default: 'MeetUp')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<a href="#show-meetUp" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                             default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li>
            <a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a>
        </li>
        <sec:ifAnyGranted roles='ROLE_ADMIN'>
            <li>
                <g:link class="create" action="create"><g:message code="default.new.label"
                                                                  args="[entityName]"/></g:link>
            </li>
        </sec:ifAnyGranted>
    </ul>
</div>

<div id="show-meetUp" class="content scaffold-show" role="main">
    <h1>
        meetUp : <f:display bean="meetUp" property="titulo"/>
    </h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>

    <div class="fieldcontain">
        <span class="property-label">Descripcion</span>

        <div class="property-value">
            <f:display bean="meetUp" property="descripcion"/>
        </div>
    </div>

    <div class="fieldcontain">
        <span class="property-label">Fecha</span>

        <div class="property-value">
            <f:display bean="meetUp" property="fecha"/>
            <g:formatDate date="${value}" type="both"/>
        </div>
    </div>
    <sec:ifAnyGranted roles='ROLE_ADMIN'>
        <g:form resource="${this.meetUp}" method="DELETE">
            <fieldset class="buttons">
                <g:link class="edit" action="edit" resource="${this.meetUp}">
                    <g:message code="default.button.edit.label" default="Edit"/>
                </g:link>
                <input class="delete" type="submit"
                       value="${message(code: 'default.button.delete.label', default: 'Delete')}"
                       onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/>
            </fieldset>
        </g:form>
    </sec:ifAnyGranted>
</div>
</body>
</html>
