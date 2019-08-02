package tpencuestas3

class BootStrap {

    def init = { servletContext ->
        initUsersRolesAndEncuestas()
    }
    
    private static crearEncuestas(Usuario creador) {
        Date ahora = new Date()
        3.times { encuestaIndex ->
            Encuesta encuesta = new Encuesta(usuario: creador, titulo: "encuesta ${encuestaIndex+1}", descripcion: "la encuesta se trata de..")
            5.times { preguntaIndex->
                Pregunta pregunta = new Pregunta(enunciado: "pregunta ${preguntaIndex+1}", orden: preguntaIndex+1)
                pregunta.save()
                encuesta.addToPreguntas(pregunta)
                3.times { opcionIndex ->
                    Opcion opcion = new Opcion(descripcion: "opcion ${opcionIndex+1}", orden: opcionIndex+1)
                    opcion.save()
                    pregunta.addToOpciones(opcion)
                }
            }
            Vigencia vigencia = new Vigencia(fechaInicio: ahora, fechaFin: new Date(ahora.getTime() + (24 * 60 * 60 * 1000)))
            encuesta.vigencia = vigencia
            encuesta.save()
        }
    }

    private static initUsersRolesAndEncuestas() {
        Rol adminRol = new Rol(authority: 'ROLE_ADMIN').save()
        Rol userRol = new Rol(authority: 'ROLE_USER').save()

        Usuario admin = new Usuario(username: 'admin', password: 'admin', cuentaPremium: true, email: 'email@email.com').save()
        Usuario usuario = new Usuario(username: 'usuario', password: 'usuario', cuentaPremium: true, email: 'email2@email.com').save()
        Usuario noPremium = new Usuario(username: 'noPremium', password: 'noPremium', cuentaPremium: false, email: 'email3@email.com').save()

        UsuarioRol.create(usuario, userRol, true)
        UsuarioRol.create(noPremium, userRol, true)
        UsuarioRol.create(admin, adminRol, true)

        crearEncuestas(admin)
        crearEncuestas(usuario)
        crearEncuestas(noPremium)

    }

    def destroy = {
    }
}
