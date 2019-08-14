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

<h1><b>Mis Respuestas (${respuestasUsuarioActual.size()})</b></h1>

<g:if test="${respuestasUsuarioActual.isEmpty()}">
    <h3>•No se encontraron datos</h3>
</g:if>
<g:else>
    <g:each in="${respuestasUsuarioActual}" var="respuesta">
                <div id="list-respuesta" class="content scaffold-list" role="main">
                    <f:table collection="${respuestaList}" properties="encuesta, votante, fechaVoto, encuesta.preguntas, respuestas"/>
                     <div class="pagination">
                     <li>
                          <b>${respuesta.encuesta} : </b>
                     </li>
                     <li>
                        <g:set var="i" value="${0}" />
                        <g:each in="${respuesta.respuestas}" var="rta">
                            ${respuesta.encuesta.preguntas[i]} <b>${rta}</b>
                            <br>
                            <g:set var="i" value="${i+1}" />
                        </g:each>
                     </li>
                        <li>
                            <b>Fecha de Voto :</b> ${respuesta.fechaVoto}
                            <br>
                            <b>Descripcion :</b> ${respuesta.encuesta.descripcion}
                            <br>
                        </li>

                     </div>
                </div>
            </g:each>
</g:else>

<g:if test="${esUsuarioActualPremium}">
    <h1><b>Respuestas de Todos los Usuarios (${respuestasTodosUsuarios.size()})</b></h1>
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
                      <b>Encuesta : </b>
                      <br>
                      <g:link action="detallesEncuesta" id="${respuesta.encuesta.id}">
                            <button type="button" ><b>${respuesta.encuesta} </b></button>
                      </g:link>
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