<html>
<head>
    <title>Question - Responder</title>
    <meta name="layout" content="main">
</head>

<body>
<g:hiddenField name="encuestaId" value="${encuesta.id}"/>
<g:form action="guardarRespuestas" id="${encuesta.id}">
    <g:each in="${encuesta.preguntas.sort { it.orden }}" var="pregunta">
        <li>
            <h4>${pregunta.orden} ${pregunta.enunciado}</h4>
            <g:radioGroup name="${pregunta.orden}"
                          labels="${pregunta.opciones}"
                          values="${pregunta.opciones}">
                <p>  ${it.label.descripcion} <input type="radio" name="${pregunta.getId()}" value="${it.label.getId()}" /> </p>
                 ${pregunta.respondio}
            </g:radioGroup>

        </li>
    </g:each>
     <br/>
   <g:submitButton name="Responder"/>
</g:form>
</body>
</html>