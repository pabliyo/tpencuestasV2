<html>
<head>
    <title>Question - Mis Encuestas</title>
    <meta name="layout" content="main"/>
</head>

<body>
<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li></ul>
</div>
<g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
</g:if>

<div class="content scaffold-list" role="main">
    <f:table collection="${encuestasUsuarioActual}" properties="titulo, descripcion, preguntas, vigencia"/>
</div>

<div class="pagination">
    <g:paginate total="${encuestasUsuarioActual ?: 0}"/>
</div>
</body>
</html>