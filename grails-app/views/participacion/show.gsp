<html>
<head>
    <title>Question - Encuestas</title>
    <meta name="layout" content="main"/>
</head>

<body>
<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li></ul>
</div>

<h1>Encuestas Disponibles</h1>
<g:each in="${encuestas}" var="encuesta">
    <div id="list-encuesta" class="content scaffold-list" role="main">
        <f:table collection="${encuestaList}" properties="titulo, descipcion"/>
        <div class="pagination">
            <li>
                <g:link action="participar" id="${encuesta.id}">${encuesta.titulo}
                    ${encuesta.descripcion}</g:link>
            </li
        </div>
    </div>
</g:each>
</body>
</html>