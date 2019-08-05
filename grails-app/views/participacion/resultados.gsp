<html>
<head>
    <title>Question - Mis Respuestas</title>
    <meta name="layout" content="main"/>
</head>

<body>
<div class="nav" role="navigation">
    <ul>
        <li>
            <a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a>
        </li>
    </ul>
</div>

<h1>Encuestas Respondidas Propias</h1>

<div class="content scaffold-list" role="main">
    <f:table collection="${respuestasUsuarioActual}" properties="encuesta, fechaVoto, respuestas"/>
</div>
<g:if test="esUsuarioActualPremium">
    <h1>Encuestas Respondidas Por Todos</h1>

    <div class="content scaffold-list" role="main">
        <f:table collection="${respuestasTodosUsuarios}" properties="encuesta, votante,fechaVoto, respuestas"/>
    </div>
</g:if>

</body>
</html>