<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Question</title>
</head>

<body>


<div id="content" role="main">
    <section class="row colset-2-its">
        <h1>Bienvenido a Question</h1>
        <div id="controllers" role="navigation">
            <sec:ifLoggedIn>
                <h2>Elige que hacer:</h2>
                <ul>
                   <sec:ifAnyGranted roles='ROLE_ADMIN'>
                    <li>
                       <g:link controller="encuesta" action="index">Ver todas las encuestas</g:link>
                    </li>
                    <li>
                       <g:link controller="usuario" action="index">Ver todos los usuarios</g:link>
                    </li>
                   </sec:ifAnyGranted>
                    <li>
                        <g:link controller="usuario" action="edit">Editar Perfil</g:link>
                    </li>
                    <li>
                        <g:link controller="participacion" action="show">Participar en encuestas disponibles</g:link>
                    </li>
                    <li>
                        <g:link controller="encuesta" action="create">Crear nueva encuesta</g:link>
                    </li>
                    <li>
                        <g:link controller="participacion" action="propias">Mis Encuestas</g:link>
                    </li>
                    <li>
                        <g:link controller="participacion" action="resultados">Ver resultados</g:link>
                    </li>
                </ul>
            </sec:ifLoggedIn>
        </div>
    </section>
</div>

</body>
</html>
