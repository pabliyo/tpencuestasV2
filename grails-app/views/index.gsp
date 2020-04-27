<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Santander</title>
</head>

<body>

<div id="content" role="main">

    <sec:ifNotLoggedIn>
        <p>
            Loggearse para ver las acciones.
        </p>
        <section>
            <h1>1. Challenge Meetups</h1>
        </section>

        <p>
            <b>En Santander Tecnología queremos armar las mejores meetups y para eso planeamos hacer una App que nos ayude a lograr que no falte lo fundamental... ¡Birras!</b>
        </p>

        <p><span style="color: rgb(231,76,60); --darkreader-inline-color:#ea6153;" data-darkreader-inline-color="">
            <b>¿Cuál es el problema?</b>
        </span></p>

        <p>
            <b>Tenemos un proveedor que nos vende cajas de 6 unidades de birras. El problema es que: si hace entre 20 y 24 grados, se toma una birra por persona; si hace menos de 20 grados, se toma 0.75; y si hace mucho calor (más de 24 grados), se toman 2 birras más por persona. Y siempre preferimos que sobre y no que falte.</b>
        </p>

        <p>
            <span style="color: rgb(231, 76, 60);">
                <b>Este es nuestro</b><strong>backlog</strong><b>, ¿desarrollamos?</b>
            </span>
        </p>

        <p>&nbsp;</p>

        <p><b>Cada historia de usuario una tiene su parte front y back, las que están en negrita son obligatorias y cuantas más hagas, ¡mejor!</b>
        </p>

        <ul>
            <li>
                <p><strong>Como admin quiero saber cuántas cajas de birras tengo que comprar para poder aprovisionar la meetup.</strong>
                </p>
            </li>
            <li>
                <p><strong>Como admin y usuario quiero conocer la temperatura del día de la meetup para saber si va a hacer calor o no.</strong>
                </p>
            </li>
            <li>
                <p><b>Como usuario y como admin quiero poder recibir notificaciones para estar al tanto de las meetups.</b>
                </p>
            </li>
            <li>
                <p><b>Como admin quiero armar una meetup para poder invitar otras personas.</b></p>
            </li>
            <li>
                <p><b>Como usuario quiero inscribirme en una meetup para poder asistir.</b></p>
            </li>
            <li>
                <p><b>Como usuario quiero hacer check-in en una meetup para poder avisar que estuve ahí.</b></p>
            </li>
        </ul>

        <p><span style="color: rgb(231, 76, 60);"><b>Tené en cuenta estos aspectos técnicos</b></span></p>

        <ul>
            <li>
                <p><b>Uso de swagger para las APIs</b></p>
            </li>
            <li>
                <p><b>Cache, retry, circuit breaker, maturity level, I18N, reactive</b></p>
            </li>
            <li>
                <p><b>Seguridad</b></p>
            </li>
            <li>
                <p><b>Front responsive/pwa</b></p>
            </li>
            <li>
                <p><b>Tests ui</b></p>
            </li>
            <li>
                <p><b>Testing automático</b></p>
            </li>
        </ul>

        <p>&nbsp;</p>

        <p><span style="color: rgb(231,76,60);"><b>Consideraciones finales</b></span></p>

        <p>&nbsp;</p>

        <p><strong>Según qué tipo de dev te consideres, podes hacer front, back o ambas versiones de la historia de usuario</strong>, es a gusto y piaccere<strong>.</strong>
        </p>

        <p><strong>Para backend te proponemos usar java, kotlin, net, js o go.&nbsp;</strong><b>Y para la persistencia, sentite a gusto de elegir la bd que te guste.</b><strong>Para front web, podes usar angular, react o vue. Para front mobile podes usar iOS o Android.&nbsp;</strong>
        </p>

        <p>&nbsp;</p>

        <p><b>Si considerás tener supuestos, como “estaAPImevaadevolverxcosa”, por favor aclaralo en el README o mejor, podés mockearlo.</b>
        </p>

        <p><b>Por favor, agregá un README que tenga info del stack, cómo hacer un deploy o info que aporte valor.</b>
        </p>

        <p><b>¡Lo último!<br>
            <br>
            <em>Bonus points​:</em></b></p>

        <ul>
            <li>
                <p><b>Proponer esquema de CI/CD</b></p>
            </li>
            <li>
                <p><b>Proponer un esquema de automatización de pruebas</b></p>
            </li>
        </ul>

        <p><b>Te dejamos un par de APIs de clima para que uses la que más te convenga:<br>
            <br>
            https://rapidapi.com/community/api/open-weather-map</b></p>
    </sec:ifNotLoggedIn>

    <section class="row colset-2-its">
        <!--h1 align="center">Bienvenido a Question</h1-->

        <div id="controllers" role="navigation">
            <sec:ifAnyGranted roles='ROLE_ADMIN'>
                <!--ADMIN -->
                <h1 align="left"><b>Acciones Admin:</b></h1>
                <ul>
                    <div class="pagination">
                        <li>
                            <g:link controller="meetUp" action="index">Aprovisionar MeetUps</g:link>
                        </li>
                    </div>

                    <div class="pagination">
                        <li>
                            <g:link controller="meetUp" action="create">Crear MeetUp</g:link>
                        </li>
                    </div>
                    <!--li>TODO mostrar meetups creadas y permitir modificar
                        <g:link controller="meetUp" action="index">Lista de MeetUps</g:link>
                    </li-->
                </ul>
            </sec:ifAnyGranted>
            <sec:ifAnyGranted roles='ROLE_USER'>
                <!--USER -->
                <h1 align="left"><b>Acciones Usuario:</b></h1>
                <ul>
                    <div class="pagination">
                        <li>
                            <!--TODO cambiar controller -->
                            <g:link controller="meetUp" action="index">Inscribirse en una meetUp</g:link>
                        </li>
                    </div>

                    <div class="pagination">
                        <li>
                            <!--TODO cambiar controller -->
                            <g:link controller="meetUp" action="index">Hacer Check-In</g:link>
                        </li>
                    </div>
                </ul>
            </sec:ifAnyGranted>
            <sec:ifLoggedIn>
                <h2><b>Acciones generales:</b></h2>
                <ul>
                    <div class="pagination">
                        <li>
                            <!--TODO cambiar controller -->
                            <g:link controller="meetUp" action="index">Temperatura de las meetUps</g:link>
                        </li>
                    </div>
                </ul>
            </sec:ifLoggedIn>
        </div>
    </section>
</div>

</body>
</html>
