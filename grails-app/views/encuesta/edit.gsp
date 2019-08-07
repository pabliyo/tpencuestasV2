<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'encuesta.label', default: 'Encuesta')}"/>
    <title>
        <g:message code="default.edit.label" args="[entityName]"/>
    </title>
</head>

<body>
<a href="#edit-encuesta" class="skip" tabindex="-1">
    <g:message code="default.link.skip.label" default="Skip to content&hellip;"/>
</a>

<div class="nav" role="navigation">
    <ul>
        <li>
            <a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a>
        </li>
        <li>
            <g:link class="list" controller="participacion" action="propias">
                <g:message code="default.list.label" args="[entityName]"/>
            </g:link>
        </li>
        <li>
            <g:link class="create" action="create">
                <g:message code="default.new.label" args="[entityName]"/>
            </g:link>
        </li>
    </ul>
</div>

<div id="edit-encuesta" class="content scaffold-edit" role="main">
    <h1>
        <g:message code="default.edit.label" args="[entityName]"/>
    </h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${this.encuesta}">
        <ul class="errors" role="alert">
            <g:eachError bean="${this.encuesta}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>>
                    <g:message error="${error}"/>
                </li>
            </g:eachError>
        </ul>
    </g:hasErrors>
    <g:form resource="${this.encuesta}" method="PUT">
        <g:hiddenField name="version" value="${this.encuesta?.version}"/>
        <fieldset class="form">
            <f:field bean="encuesta" property="titulo"/>
            <f:field bean="encuesta" property="descripcion"/>
            <f:field bean="encuesta" property="vigencia.fechaInicio">
                <g:datePicker name="${property}" value="${value}"/>
            </f:field>
            <f:field bean="encuesta" property="vigencia.fechaFin">
                <g:datePicker name="${property}" value="${value}"/>
            </f:field>
            <!--p>Preguntas:</p-->
            <f:field bean="encuesta" property="preguntas"/>
        </fieldset>
        <fieldset class="buttons">
            <input class="save" type="submit"
                   value="${message(code: 'default.button.update.label', default: 'Update')}"/>
        </fieldset>
    </g:form>
</div>
</body>
</html>
