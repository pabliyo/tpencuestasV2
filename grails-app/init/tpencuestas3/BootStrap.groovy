package tpencuestas3

class BootStrap {

    def init = { servletContext ->
        initUsersAndRoles()
        crearEncuestasDefault()
    }

    private static crearEncuestasDefault() {
        3.times { encuestaIndex ->
            Encuesta encuesta = new Encuesta(titulo: "encuesta ${encuestaIndex}", descripcion: "la encuesta se trata de..")
            encuesta.usuario = Usuario.first()//para que tenga un usuario asociado
            Long x = 0
            5.times { preguntaIndex ->
                x = x + 1
                Pregunta pregunta = new Pregunta(enunciado: "pregunta ${preguntaIndex}", orden: x)
                pregunta.save()
                encuesta.addToPreguntas(pregunta)
                Long y = 0
                3.times { opcionIndex ->
                    y = y + 1
                    Opcion opcion = new Opcion(descripcion: "opcion ${y}", orden: y)
                    opcion.save()
                    pregunta.addToOpciones(opcion)
                }
            }
            encuesta.save()
        }
    }

    private static initUsersAndRoles() {
        Rol adminRol = new Rol(authority: 'ROLE_ADMIN').save()
        Rol userRol = new Rol(authority: 'ROLE_USER').save()

        Usuario admin = new Usuario(username: 'admin', password: 'admin', cuentaPremium: true, email: 'email@email.com').save()
        Usuario usuario = new Usuario(username: 'usuario', password: 'usuario', cuentaPremium: true, email: 'email2@email.com').save()
        //Usuario premium = new Usuario(username: 'premium', password: 'premium', cuentaPremium: true, email: 'email3@email.com').save()

        UsuarioRol.create(usuario, userRol, true)

        if (!admin.authorities.contains(adminRol)) {
            UsuarioRol.create(admin, adminRol, true)
        }
    }

    def destroy = {
    }
}
