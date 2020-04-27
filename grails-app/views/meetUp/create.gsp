<%@ page import="org.springframework.validation.FieldError" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'meetUp.label', default: 'MeetUp')}"/>
    <title>
        <g:message code="default.create.label" args="[entityName]"/>
    </title>
</head>

<body>
<a href="#create-meetUp" class="skip" tabindex="-1">
    <g:message code="default.link.skip.label" default="Skip to content&hellip;"/>
</a>

<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
    </ul>
</div>

<div id="create-meetUp" class="content scaffold-create" role="main">
    <h1><g:message code="default.create.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${this.meetUp}">
        <ul class="errors" role="alert">
            <g:eachError bean="${this.meetUp}" var="error">
                <li <g:if test="${error in FieldError}">data-field-id="${error.field}"</g:if>><g:message
                        error="${error}"/></li>
            </g:eachError>
        </ul>
    </g:hasErrors>
    <g:form resource="${this.meetUp}" method="POST">
        <fieldset class="formulario" >
        <g:field name="usuario.id" hidden="true" value="${sec.loggedInUserInfo(field: 'id')}" type="text"/>
        <f:field bean="meetUp" property="titulo"/>
        <f:field bean="meetUp" property="descripcion"/>
        <f:field bean="meetUp" property="fecha">
            <g:datePicker name="${property}" value="${value}"/>
        </f:field>
        <fieldset class="buttons">
            <g:submitButton name="create" class="save"
                            value="${message(code: 'default.button.create.label', default: 'Create')}"/>
        </fieldset>
    </g:form>
</div>
</body>
</html>
