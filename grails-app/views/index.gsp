<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Question</title>
</head>

<body>


<div id="content" role="main" style="background-image:url('${resource(dir: "images", file: "fondo3.jpg")}');height: 500px;width:1300px;">
   <div id="controllers" role="navigation">
    <sec:ifAnyGranted roles='ROLE_ADMIN'>
        <h1 align="left"><b> Administracion: </b>
                        <li>
                           <g:link controller="encuesta" action="index">Ver todas las encuestas</g:link>
                        </li>
                        <li>
                           <g:link controller="usuario" action="index">Ver todos los usuarios</g:link>
                        </li>
                        </h1>
    </sec:ifAnyGranted>
   </div>
    <section class="row colset-2-its">
        <h1 align="center">Bienvenido a Question</h1>
        <div id="controllers" role="navigation">
            <sec:ifLoggedIn>
                <h2><b>Elige que hacer: </b></h2>
                <ul>

                   <div class="pagination">
                    <li>
                        <g:link controller="usuario" action="edit">Editar Perfil</g:link>
                    </li>
                    </div>
                    <div class="pagination">
                    <li>
                        <g:link controller="participacion" action="show">Participar en encuestas disponibles</g:link>
                    </li>
                    </div>
                    <div class="pagination">
                    <li>
                        <g:link controller="encuesta" action="create">Crear nueva encuesta</g:link>
                    </li>
                    </div>
                    <div class="pagination">
                    <li>
                        <g:link controller="participacion" action="propias">Mis Encuestas</g:link>
                    </li>
                    </div>
                    <div class="pagination">
                    <li>
                        <g:link controller="participacion" action="resultados">Ver resultados</g:link>
                    </li>
                    </div>
                </ul>
            </sec:ifLoggedIn>
        </div>
    </section>
</div>

</body>
</html>
