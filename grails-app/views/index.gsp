<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Question</title>
    <asset:stylesheet src="application.css"/>
</head>

<body>
<sec:ifNotLoggedIn>
    <nav class="navbar navbar-expand-lg navbar-dark navbar-static-top" role="navigation">
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarContent"
                aria-controls="navbarContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="navbar-collapse collapse" aria-expanded="false" style="height: 0.8px;" id="navbarContent">
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                       aria-expanded="false">Inicio</a>
                    <ul class="dropdown-menu">
                        <li>
                            <g:link controller="usuario" action="create">Registrate</g:link>
                        </li>
                        <li>
                            <g:link controller="login" action="index">LogIn</g:link>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>

    </nav>
</sec:ifNotLoggedIn>

<div id="content" role="main">
    <section class="row colset-2-its">
        <h1>Bienvenido a Question</h1>

        <div id="controllers" role="navigation">
            <sec:ifLoggedIn>
                <h2>Elige que hacer:</h2>
                <ul>
                    <li>
                        <g:link controller="encuesta" action="index">Todas las encuestas creadas</g:link>
                    </li>
                    <li>
                        <g:link controller="encuesta" action="create"
                                usuario="${currentUsuario}">Crear nueva encuesta</g:link>
                    </li>
                    <li>
                        <g:link controller="participacion" action="propias"
                                id="${usuarioInstance?.id}">Mis Encuestas</g:link>
                    </li>
                    <li>
                        <g:link controller="participacion" action="show">Participar</g:link>
                    </li>
                </ul>
            </sec:ifLoggedIn>
        </div>
    </section>
</div>

</body>
</html>
