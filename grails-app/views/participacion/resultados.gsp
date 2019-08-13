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

<g:if test="${respuestasUsuarioActual.isEmpty()}">
    <h3>•No se encontraron datos</h3>
</g:if>
<g:else>
    <div class="content scaffold-list" role="main">
        <f:table collection="${respuestasUsuarioActual}" properties="encuesta, fechaVoto, encuesta.preguntas, respuestas"/>
    </div>
</g:else>

<g:if test="${esUsuarioActualPremium}">
    <h1>Encuestas Respondidas Por Todos</h1>
    <g:if test="${respuestasTodosUsuarios.isEmpty()}">
        <h3>•No se encontraron datos</h3>
    </g:if>
    <g:else>
        <g:each in="${respuestasTodosUsuarios}" var="respuesta">
            <div id="list-respuesta" class="content scaffold-list" role="main">
                <f:table collection="${respuestaList}" properties="encuesta, votante, fechaVoto, encuesta.preguntas, respuestas"/>
                 <div class="pagination">
                    <li>
                        <b>Usuario :</b> ${respuesta.votante}
                        <br>
                        <b>Fecha de Voto :</b> ${respuesta.fechaVoto}
                        <br>
                        <b>Descripcion :</b> ${respuesta.encuesta.descripcion}
                        <br>
                    </li>
                    <li>
                            <b>${respuesta.encuesta}</b>
                    </li>
                    <li>
                    <g:set var="i" value="${0}" />
                    <g:each in="${respuesta.respuestas}" var="rta">
                        ${respuesta.encuesta.preguntas[i]} <b>${rta}</b>
                        <br>
                        <g:set var="i" value="${i+1}" />
                    </g:each>
                    </li>
                 </div>
            </div>
        </g:each>
    </g:else>
</g:if>

</body>
</html>