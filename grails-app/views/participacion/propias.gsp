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

<div class="content scaffold-list" role="main">
    <f:table collection="${encuestas}" properties="titulo, descripcion, preguntas, vigencia"/>
</div>

<div class="pagination">
    <g:paginate total="${encuestas ?: 0}"/>
</div>
</body>
</html>