<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'usuario.label', default: 'Usuario')}"/>
    <title><g:message code="default.edit.label" args="[entityName]"/></title>
</head>

<body>
<a href="#edit-usuario" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                              default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
    </ul>
</div>

<div id="edit-usuario" class="content scaffold-edit" role="main">
    <h1><g:message code="default.edit.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${this.usuario}">
        <ul class="errors" role="alert">
            <g:eachError bean="${this.usuario}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message
                        error="${error}"/></li>
            </g:eachError>
        </ul>
    </g:hasErrors>
    <g:form resource="${this.usuario}" method="PUT">
        <g:hiddenField name="version" value="${this.usuario?.version}"/>
        <fieldset class="form">
            <div class="fieldcontain">
                <span class="property-label">Nombre de usuario</span>

                <div class="property-value">
                    <g:field name="username" type="text" value="${this.usuario.username}"/>
                </div>
            </div>

            <div class="fieldcontain">
                <span class="property-label">Contraseña</span>

                <div class="property-value">
                    <g:field name="password" type="password" value="${this.usuario.password}" />
                </div>
            </div>

            <div class="fieldcontain">
                <span class="property-label">Email</span>

                <div class="property-value">
                    <g:field name="mail" type="email" value="${this.usuario.email}"/>
                </div>
            </div>

            <div class="fieldcontain">
                <span class="property-label">Cuenta Premium</span>

                <div class="property-value">
                    <g:field name="premium" type="checkbox" value="${this.usuario.cuentaPremium}"/>
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