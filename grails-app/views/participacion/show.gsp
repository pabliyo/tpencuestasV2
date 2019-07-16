<html>
<head>
  <meta name="layout" content="main" />
  <title>Question - Encuestas</title>
</head>
<body>
    <g:each in="${encuestas}" var="encuesta">
        <div style="width: 400px; float: left">
            ${encuesta.titulo}
        </div>

    </g:each>
</body>
</html>