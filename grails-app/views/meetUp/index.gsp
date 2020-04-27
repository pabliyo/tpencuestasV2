<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'meetUp.label', default: 'MeetUp')}"/>
    <title>
        <g:message code="default.list.label" args="[entityName]"/>
    </title>
</head>

<body>
<a href="#list-meetUp" class="skip" tabindex="-1">
    <g:message code="default.link.skip.label" default="Skip to content&hellip;"/>
</a>

<div class="nav" role="navigation">
    <ul>
        <li>
            <a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a>
        </li>
        <li>
            <sec:ifAnyGranted roles='ROLE_ADMIN'>
                <g:link class="create" action="create">
                    <g:message code="default.new.label" args="[entityName]"/>
                </g:link>
            </sec:ifAnyGranted>
        </li>
    </ul>
</div>
<!--TODO if sec admin mostrar cuantas cajas comprar -->
<!--TODO if sec user link para hacer check in o para inscribirse -->
<div id="list-meetUp" class="content scaffold-list" role="main">
    <h1>
        <g:message code="default.list.label" args="[entityName]"/>
    </h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:each in="${meetUpList}">
        <p>Titulo: ${it.titulo}
        Descripcion: ${it.descripcion}
        Fecha: ${it.fecha}
        ${it.cantidadDeCajasDeBirra}</p>
    </g:each>
    <f:table collection="${meetUpList}" properties="titulo,descripcion"/>
    <div class="pagination">
        <g:paginate total="${meetUpCount ?: 0}"/>
    </div>
</div>
</body>
</html>
