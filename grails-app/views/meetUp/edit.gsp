<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'meetUp.label', default: 'MeetUp')}"/>
    <title>
        <g:message code="default.edit.label" args="[entityName]"/>
    </title>
</head>

<body>
<a href="#edit-meetUp" class="skip" tabindex="-1">
    <g:message code="default.link.skip.label" default="Skip to content&hellip;"/>
</a>

<div class="nav" role="navigation">
    <ul>
        <li>
            <a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a>
        </li>
        <li>
            <g:link class="create" action="create">
                <g:message code="default.new.label" args="[entityName]"/>
            </g:link>
        </li>
    </ul>
</div>

<div id="edit-meetUp" class="content scaffold-edit" role="main">
    <h1>
        <g:message code="default.edit.label" args="[entityName]"/>
    </h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${this.meetUp}">
        <ul class="errors" role="alert">
            <g:eachError bean="${this.meetUp}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>>
                    <g:message error="${error}"/>
                </li>
            </g:eachError>
        </ul>
    </g:hasErrors>
    <g:form resource="${this.meetUp}" method="PUT">
        <g:hiddenField name="version" value="${this.meetUp?.version}"/>
        <fieldset class="form">
            <f:field bean="meetUp" property="titulo"/>
            <f:field bean="meetUp" property="descripcion"/>
            <f:field bean="meetUp" property="fecha">
                <g:datePicker name="${property}" value="${value}"/>
            </f:field>
            <div class="fieldcontain">
                <span class="property-label">Preguntas</span>

                <div class="property-value">
                    <f:field label=" " bean="meetUp" property="preguntas"/>
                </div>
            </div>
        </fieldset>
        <fieldset class="buttons">
            <input class="save" type="submit"
                   value="${message(code: 'default.button.update.label', default: 'Update')}"/>
        </fieldset>
    </g:form>
</div>
</body>
</html>
