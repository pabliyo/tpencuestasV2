<!doctype html>
<html lang="en" class="no-js">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>
        <g:layoutTitle default="Grails"/>
    </title>
    <link rel="shortcut icon" href="${createLinkTo(dir: 'images', file: 'logo.ico')}" type="image/x-icon"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>

    <asset:stylesheet src="application.css"/>

    <g:layoutHead/>
</head>

<body>

<nav class="navbar navbar-expand-lg navbar-dark navbar-static-top" role="navigation">

    <a class="navbar-brand" href="/#"><asset:image src="Paomedia-Small-N-Flat-Beer.ico" width="120" height="150"
                                                   alt="Beer Logo"/></a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarContent"
            aria-controls="navbarContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <sec:ifNotLoggedIn>
        <nav class="navbar navbar-expand-lg navbar-dark navbar-static-top" role="navigation">
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarContent"
                    aria-controls="navbarContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="navbar-collapse collapse" aria-expanded="false" style="height: 0.8px;" id="notLoggedIn">
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
    <div class="navbar-collapse collapse" aria-expanded="false" style="height: 0.8px;" id="loggedIn">
        <ul class="nav navbar-nav navbar-right">
            <sec:ifLoggedIn>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                       aria-expanded="false">Hola <sec:username/><span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><g:link class="edit">Home</g:link></li>
                        <li><g:link controller='logout'>Logout</g:link></li>
                    </ul>
                </li>
            </sec:ifLoggedIn>
        </ul>
    </div>

</nav>


<g:layoutBody/>


<div class="footer row" role="contentinfo">
    <div class="col">

    </div>

</div>

<div id="spinner" class="spinner" style="display:none;">
    <g:message code="spinner.alt" default="Loading&hellip;"/>
</div>

<asset:javascript src="application.js"/>

</body>
</html>
