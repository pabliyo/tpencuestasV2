<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'pregunta.label', default: 'Pregunta')}"/>
    <title><g:message code="default.create.label" args="[entityName]"/></title>
</head>

<body>
<a href="#create-pregunta" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                                 default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="list" controller="participacion" action="propias">Mis encuestas</g:link></li>
    </ul>
</div>

<div id="create-pregunta" class="content scaffold-create" role="main">
    <h1><g:message code="default.create.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${this.pregunta}">
        <ul class="errors" role="alert">
            <g:eachError bean="${this.pregunta}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message
                        error="${error}"/></li>
            </g:eachError>
        </ul>
    </g:hasErrors>
    <g:form resource="${this.pregunta}" method="POST">
    <g:hiddenField name="version" value="${this.encuesta?.version}" />
        <fieldset class="form">
            <g:hiddenField name="encuesta.id" value="${params.get("encuesta.id")}" />
            <f:field bean="pregunta" property="encuesta"><f:display bean="pregunta" property="encuesta"/></f:field>
            <f:field bean="pregunta" property="enunciado"/>
            <g:set var="i" value="${1}"/>
            <g:each in="${pregunta.encuesta.preguntas}">
                <g:set var="i" value="${i+1}"/>
             </g:each>
             <f:field bean="pregunta" property="orden"><g:field type="number" readOnly="true" name="orden" value="${i}"/></f:field>
        </fieldset>
        <fieldset class="buttons">
            <g:submitButton name="create" class="save"
                            value="${message(code: 'default.button.create.label', default: 'Create')}"/>
        </fieldset>
    </g:form>
</div>
</body>
</html>
