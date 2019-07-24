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
            <h4>${pregunta.orden} ${pregunta.enunciado} </h4>
            <g:radioGroup name="${pregunta.orden} + ' ' + ${pregunta.enunciado}"
                          labels="${pregunta.opciones.each { it.descripcion }}"
                          values="${pregunta.opciones.each { it.orden }}">

                <p style="float: left ">${it.radio} ${it.label}</p>
            </g:radioGroup>
        </li>
        <br/>
    </g:each>

    <g:submitButton name="Responder"/>
</g:form>
</body>
</html>