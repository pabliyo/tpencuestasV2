<html>
<head>
    <title>Question - Responder</title>
    <meta name="layout" content="main">
</head>

<body>
<g:form action="guardarRespuestas">
    <g:hiddenField name="encuestaId" value="${encuesta.id}"/>
    <g:each in="${encuesta.preguntas.sort { it.orden }}" var="pregunta">
        <li>
            <g:sortableColumn property="enunciado" title="${pregunta.enunciado}"/>
            <g:sortableColumn property="orden" title="${pregunta.orden}"/>
        </li>
        <li>
            <g:checkBox name="  "/>
            ${encuesta.preguntas.opciones.descripcion}
        </li>
        <br/>
    </g:each>
    <g:submitButton name="Responder"/>
</g:form>
</body>
</html>