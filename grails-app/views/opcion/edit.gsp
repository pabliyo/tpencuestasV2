<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'opcion.label', default: 'Opcion')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#edit-opcion" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" controller="participacion" action="propias">Lista encuestas</g:link></li>
            </ul>
        </div>
        <div id="edit-opcion" class="content scaffold-edit" role="main">
            <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${this.opcion}">
            <ul class="errors" role="alert">
                <g:eachError bean="${this.opcion}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                </g:eachError>
            </ul>
            </g:hasErrors>
            <g:form resource="${this.opcion}" method="PUT">
                <g:hiddenField name="version" value="${this.opcion?.version}" />
                <fieldset class="form">
                    <f:field bean="opcion" property="pregunta"><f:display bean="opcion" property="pregunta"/></f:field>
                    <f:field bean="opcion" property="descripcion"/>
                    <f:field bean="opcion" property="orden"><g:field type="number" readOnly="true" name="orden" value="${opcion.orden}"/></f:field>
                </fieldset>
                <fieldset class="buttons">
                    <input class="save" type="submit" value="${message(code: 'default.button.update.label', default: 'Update')}" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
