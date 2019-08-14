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
        <g:if test="${encuesta.cantidadPreguntas()>0 && encuesta.tieneOpciones()}">
         <div class="pagination">
            <li>
                <g:link action="participar" id="${encuesta.id}">
                    <h1>Encuesta : <b>${encuesta.titulo}</b></h1>
                    <h1>Descripcion : <b> ${encuesta.descripcion}</b></h1>
                    <h1>Finaliza : <b>${encuesta.vigencia.fechaFin}</b> </h1>
                    <h1>Votaciones : <b>${encuesta.cantidadVotaciones(votaciones)} </b></h1>
                </g:link>
            </li>
         </div>
        </g:if>
    </div>
</g:each>
</body>
</html>