<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Question</title>
</head>
<body>
<content tag="nav">
    <li class="dropdown">
        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Login <span class="caret"></span></a>
        <ul class="dropdown-menu">
            <li class="dropdown-item"><a href="#">Controllers: ${grailsApplication.controllerClasses.size()}</a></li>

        </ul>
    </li>
</content>



<div id="content" role="main">
    <section class="row colset-2-its">
        <h1>Bienvenido a Question</h1>
        <div id="controllers" role="navigation">
            <h2>Elige que hacer:</h2>
            <ul>

                    <li>
                        <g:link controller="encuesta" action="index">Mis encuestas creadas</g:link>
                    </li>
                    <li>
                       <g:link controller="encuesta" action="create">Crear nueva encuesta</g:link>
                    </li>
            </ul>
        </div>
    </section>
</div>

</body>
</html>
