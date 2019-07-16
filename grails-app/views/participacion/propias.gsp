<html>
<head>
   <meta name="layout" content="main" />
   <title>Question - Mis Encuestas</title>
</head>
<body>
    <g:each in="${encuestas}" var="usuario">
        <div style="width: 400px; float: left">
            ${usuario.username}
        </div>

    </g:each>
</body>
</html>