<html>
<head>
    <title>Question - Responder</title>
    <meta name="layout" content="main">
</head>

<body>
<g:if test="${flash.message}">
    <div class="message" role="status">${flash.message}</div>
</g:if>
<g:hiddenField name="encuestaId" value="${encuesta.id}"/>
<g:form action="guardarRespuestas" id="${encuesta.id}">
    <g:set var="i" value="${0}"/>
    <g:each in="${encuesta.preguntas.sort { it.orden }}" var="pregunta">
        <g:if test="${encuesta.getVigencia().estaVigente()}">
        <g:if test="${pregunta.cantidadOpciones()>1}">
        <g:set var="i" value="${i+1}"/>
            <br/>
            <li>
                <h4>${i} ${pregunta.enunciado}</h4>
                <g:radioGroup name="${pregunta.orden}"
                              labels="${pregunta.opciones}"
                              values="${pregunta.opciones}">
                    <p style="float: left ; margin-left: 1% ">
                        <input type="radio" name="${pregunta.getId()}" value="${it.label.getId()}"/>
                        ${it.label.descripcion}
                    </p>
                </g:radioGroup>
            </li>
            <br/>
         </g:if>
        </g:if>
    </g:each>
    <br/>
    <g:submitButton name="Responder"/>
</g:form>
</body>
</html>