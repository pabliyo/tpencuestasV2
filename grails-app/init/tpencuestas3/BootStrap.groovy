package tpencuestas3

class BootStrap {

    def init = { servletContext ->

        Rol adminRol = new Rol(authority: 'ROLE_ADMIN').save()
        //Rol userRol = new Rol(authority: 'ROLE_USER').save()

        Usuario admin = new Usuario(username: 'admin', password: 'admin', cuentaPremium: true, email: 'email@email.com').save()
        //Usuario usuario = new Usuario(username: 'usuario', password: 'usuario', cuentaPremium: false, email: 'email2@email.com').save()
        //Usuario premium = new Usuario(username: 'premium', password: 'premium', cuentaPremium: true, email: 'email3@email.com').save()
        //UsuarioRol.create(premium, userRol, true)
        if(!admin.authorities.contains(adminRol)){
            UsuarioRol.create(admin, adminRol, true)
        }


        /*
        3.times { encuestaIndex ->
            Encuesta encuesta = new Encuesta(titulo: "encuesta ${encuestaIndex}", descripcion: "la encuesta se trata de..")
            5.times { preguntaIndex ->
                Pregunta pregunta = new Pregunta(enunciado: "enunciado ${encuestaIndex} - ${preguntaIndex}", orden:1)
                encuesta.addToPreguntas(enunciado: pregunta.getEnunciado(), orden: pregunta.getOrden())
                1.times { opcionIndex ->
                    //pregunta.addToOpciones(orden:1, descripcion: "opcion")
                }
               // pregunta.save()
            }
            encuesta.save()
        }
        */

    }

    def destroy = {
    }
}
