<%@ page import="org.springframework.validation.FieldError" %>
<!DOCTYPE html>
<html>
<head>
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.8.3.js"></script>
    <script type="text/javascript" src="http://jzaefferer.github.com/jquery-validation/jquery.validate.js"></script>
    <script type="text/javascript">
        function validarFormulario() {
            jQuery.validator.messages.required = 'Esta campo es obligatorio.';
            jQuery.validator.messages.number = 'Esta campo debe ser num&eacute;rico.';
            jQuery.validator.messages.email = 'La direcci&oacute;n de correo es incorrecta.';
            $("#enviar").click(function () {
                var validado = $("#formulario").valid();
                if (validado) {
                    alert('El formulario es correcto.');
                }
            });
        }

        $(document).ready(function () {
            validarFormulario();
        });
        $(document).ready(function () {
            $('#content').css({
                'left': ($('#page').width() / 2) - ($('#content').width() / 2),
                'top': ($('#page').height() / 2) - ($('#content').height() / 2)
            });
        });
    </script>
    <style type="text/css">
    .error {
        color: #f00;
    }
    </style>

    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'encuesta.label', default: 'Encuesta')}"/>
    <title><g:message code="default.create.label" args="[entityName]"/></title>
</head>

<body>
<a href="#create-encuesta" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                                 default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]"/></g:link></li>
    </ul>
</div>

<div id="create-encuesta" class="content scaffold-create" role="main">
    <h1><g:message code="default.create.label" args="[entityName]"/></h1>

    <form id="formulario" action="" method="post">
        <label for="inicio">(*) Vigencia: Fecha de inicio</label>
        <br/>

        <input id="inicio"  type="date" name="fecha_inicio"/>
        <br/>

        <label for="fin">(*) Vigencia: Fecha de fin</label>
        <br/>
        <input id="fin" type="date" name="fecha_fin" class="required"/>
        <br/>

        <label for="titulo">(*) Titulo de la encuesta</label>
        <br/>

        <input id="titulo" type="text" name="titulo" class="required"/>

        <br/>

        <label for="descripcion">Descripcion de la encuesta</label>
        <br/>
        <textarea rows="5" cols="50" name="descripcion" id="descripcion" class="required"></textarea>
        <br/>
        (*) Campos requeridos
        <br/>
        <input type="submit" id="enviar" value="Enviar"/>

        <!--div id="contenedor_errores"></div-->
    </form>
</div>

<g:if test="${flash.message}">
    <div class="message" role="status">${flash.message}</div>
</g:if>

<g:hasErrors bean="${this.encuesta}">
    <ul class="errors" role="alert">
        <g:eachError bean="${this.encuesta}" var="error">
            <li <g:if test="${error in FieldError}">data-field-id="${error.field}"</g:if>><g:message
                    error="${error}"/></li>
        </g:eachError>
    </ul>
</g:hasErrors>

<g:form resource="${this.encuesta}" method="POST">
    <fieldset class="formulario">
        <f:field bean="encuesta" property="vigencia.fechaInicio" default="${new Date()}" />
        <f:field bean="encuesta" property="vigencia.fechaFin"/>
        <f:field bean="encuesta" property="titulo"/>
        <f:field bean="encuesta" property="descripcion"/>
        <!--f:field bean="encuesta" property="imagen"/-->
        <!--f:all bean="encuesta"/-->
    </fieldset>
    <fieldset class="buttons">
        <g:submitButton name="create" class="save"
                        value="${message(code: 'default.button.create.label', default: 'Create')}"/>
    </fieldset>
</g:form>

</body>
</html>
