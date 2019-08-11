<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'pregunta.label', default: 'Pregunta')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<a href="#show-pregunta" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                               default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="list" controller="participacion" action="propias">Lista encuestas</g:link></li>
    </ul>
</div>

<div id="show-pregunta" class="content scaffold-show" role="main">
    <h1>
        <f:display bean="pregunta" property="enunciado"/>
    </h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <div class="fieldcontain">
        <span class="property-label">Encuesta</span>

        <div class="property-value">
            <f:display bean="pregunta" property="encuesta"/>
        </div>
    </div>

    <div class="fieldcontain">
        <span class="property-label">Orden</span>

        <div class="property-value">
            <f:display bean="pregunta" property="orden"/>
        </div>
    </div>

    <div class="fieldcontain">
        <span class="property-label">Opciones</span>

        <div class="property-value">
            <f:field label=" " bean="pregunta" property="opciones"/>
        </div>
    </div>
    <g:form resource="${this.pregunta}" method="DELETE">
        <fieldset class="buttons">
            <g:link class="edit" action="edit" resource="${this.pregunta}"><g:message code="default.button.edit.label"
                                                                                      default="Edit"/></g:link>
            <input class="delete" type="submit"
                   value="${message(code: 'default.button.delete.label', default: 'Delete')}"
                   onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/>
        </fieldset>
    </g:form>
</div>
</body>
</html>
