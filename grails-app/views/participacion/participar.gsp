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
                <!--value="" dentro del radio group es donde se quiere que se devuelva el valor seleccionado,
                hay que crear la respuesta con fecha , usuario ,encuesta actual-->
                <p style="float: left ">${it.radio} ${it.label}</p>
            </g:radioGroup>
        </li>
        <br/>
    </g:each>
    <g:submitButton name="Responder"/>
</g:form>
</body>
</html>