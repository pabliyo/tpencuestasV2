<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'usuario.label', default: 'Usuario')}" />
        <title>Lista de encuestas</title>
    </head>
    <b<a href="#show-usuario" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
              <div class="nav" role="navigation">
                  <ul>
                      <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                      <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
                      <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
                  </ul>
              </div>
        <div id="show-usuario" class="content scaffold-show" role="main">
                    <h1><g:message code="default.show.label" args="[entityName]" /></h1>
                    <g:if test="${flash.message}">
                    <div class="message" role="status">${flash.message}</div>
                    </g:if>
                    <f:display bean="usuario" />
        </div>
    </body>
</html>