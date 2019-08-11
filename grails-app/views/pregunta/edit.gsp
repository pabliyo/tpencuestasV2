<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'pregunta.label', default: 'Pregunta')}"/>
    <title><g:message code="default.edit.label" args="[entityName]"/></title>
</head>

<body>
<a href="#edit-pregunta" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                               default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="list" controller="participacion" action="propias">Lista encuestas</g:link></li>
        <li><g:link class="create" action="create"><g:message code="default.new.label"
                                                              args="[entityName]"/></g:link></li>
    </ul>
</div>

<div id="edit-pregunta" class="content scaffold-edit" role="main">
    <h1><g:message code="default.edit.label" args="[entityName]"/></h1>
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
    <g:form resource="${this.pregunta}" method="PUT">
        <g:hiddenField name="version" value="${this.pregunta?.version}"/>
        <fieldset class="form">
            <f:field bean="pregunta" property="enunciado"/>
            <f:field bean="pregunta" property="orden">
                <g:field type="number" readOnly="true" name="orden" value="${pregunta.orden}"/>
            </f:field>
            <div class="fieldcontain">
                <span class="property-label">Opciones</span>

                <div class="property-value">
                    <f:field label=" " bean="pregunta" property="opciones"/>
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
