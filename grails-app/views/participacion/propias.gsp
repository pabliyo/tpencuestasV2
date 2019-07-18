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
<g:each in="${encuestas}" >
    <div class="content scaffold-list" role="main">
        ${it}
    </div>
</g:each>
</body>
</html>