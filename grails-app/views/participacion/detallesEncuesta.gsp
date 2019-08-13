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

<h1><b>Respuestas por Encuesta (${respuestasPorEncuesta.size()})</b></h1>
      <br>
      <li>
         <b> Titulo de la Encuesta :</b> ${encuesta.titulo}
         <br>
         <b> Descripcion :</b> ${encuesta.descripcion}
      </li>
    <g:each in="${encuesta.preguntas}" var="pregunta">
          <div id="list-pregunta" class="content scaffold-list" role="main">
                <f:table collection="${preguntaList}" />
                   <div class="pagination">
                      <li>
                         ${pregunta}
                         <br>
                         <g:each in="${pregunta.opciones}" var="opcion" >
                            <b>${opcion} (${respuestasPorEncuesta.respuestas.count(opcion)})</b>
                            <br>
                         </g:each>
                      </li>
                  </div>
          </div>
    </g:each>