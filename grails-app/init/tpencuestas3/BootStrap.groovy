package tpencuestas3

class BootStrap {

    def init = { servletContext ->
        initUsersRolesAndEncuestas()
    }

    private static crearEncuestas(Usuario creador) {
        Date ahora = new Date()
        int z = 0
        3.times { encuestaIndex ->
            z = z + 1
            Encuesta encuesta = new Encuesta(usuario: creador, titulo: "encuesta ${z}", descripcion: "la encuesta se trata de..")
            int x = 0
            5.times { preguntaIndex ->
                x = x + 1
                Pregunta pregunta = new Pregunta(enunciado: "pregunta ${x}", orden: x)
                pregunta.save()
                encuesta.addToPreguntas(pregunta)
                int y = 0
                3.times { opcionIndex ->
                    y = y + 1
                    Opcion opcion = new Opcion(descripcion: "opcion ${y}", orden: y)
                    opcion.save()
                    pregunta.addToOpciones(opcion)
                }
            }
            encuesta.save()
            Vigencia vigencia = new Vigencia(fechaInicio: ahora, fechaFin: new Date(ahora.getTime() + (24 * 60 * 60 * 1000)))
            encuesta.vigencia = vigencia
        }
    }

    private static initUsersRolesAndEncuestas() {
        Rol adminRol = new Rol(authority: 'ROLE_ADMIN').save()
        Rol userRol = new Rol(authority: 'ROLE_USER').save()

        Usuario admin = new Usuario(username: 'admin', password: 'admin', cuentaPremium: true, email: 'email@email.com').save()
        Usuario usuario = new Usuario(username: 'usuario', password: 'usuario', cuentaPremium: true, email: 'email2@email.com').save()
        Usuario nopremium = new Usuario(username: 'nopremium', password: 'nopremium', cuentaPremium: false, email: 'email3@email.com').save()

        UsuarioRol.create(usuario, userRol, true)
        UsuarioRol.create(nopremium, userRol, true)
        UsuarioRol.create(admin, adminRol, true)

        crearEncuestas(admin)
        crearEncuestas(usuario)
        crearEncuestas(nopremium)
    }

    def destroy = {
    }
}
