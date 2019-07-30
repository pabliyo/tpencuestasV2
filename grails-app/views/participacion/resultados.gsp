<html>
<head>
    <title>Question - Mis Encuestas</title>
    <meta name="layout" content="main"/>
</head>

<body>
<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li></ul>
</div>

<h1>Respuestas Realizadas</h1>
<g:each in="${respuestas}" var="respuesta">
    <div id="list-respuesta" class="content scaffold-list" role="main">
        <f:table collection="${respuestaList}" properties="encuesta, votante"/>
        <div class="pagination">
            <li>
                <g:link action="participar" id="${encuesta.id}">
                    <b>${respuesta.encuesta}</b>
                    ${respuesta.votante}
                </g:link>
            </li
        </div>
    </div>
</g:each>
</body>
</html>