package tpencuestas3

class BootStrap {

    def init = { servletContext ->
        initUsersRolesAndEncuestas()
    }

    private static crearEncuestas(Usuario creador) {
        Date ahora = new Date()
        3.times { encuestaIndex ->
            Encuesta encuesta = new Encuesta(usuario: creador, titulo: "encuesta ${encuestaIndex + 1}", descripcion: "la encuesta se trata de..")
            5.times { preguntaIndex ->
                Pregunta pregunta = new Pregunta(enunciado: "pregunta ${preguntaIndex + 1}", orden: preguntaIndex + 1)
                pregunta.save()
                encuesta.addToPreguntas(pregunta)
                3.times { opcionIndex ->
                    Opcion opcion = new Opcion(descripcion: "opcion ${opcionIndex + 1}", orden: opcionIndex + 1)
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

        Usuario admin = new Usuario(username: 'admin', password: 'admin', cuentaPremium: true, email: 'email@email.com').save()
        Usuario usuario = new Usuario(username: 'usuario', password: 'usuario', cuentaPremium: true, email: 'email2@email.com').save()
        Usuario noPremium = new Usuario(username: 'noPremium', password: 'noPremium', cuentaPremium: false, email: 'email3@email.com').save()

        UsuarioRol.create(usuario, Rol.getUserRol().save(), true)
        UsuarioRol.create(noPremium, Rol.getUserRol().save(), true)
        UsuarioRol.create(admin, Rol.getAdminRol().save(), true)

        crearEncuestas(admin)
        crearEncuestas(usuario)
        crearEncuestas(noPremium)
        encuesta1(admin)

    }

    private static encuesta1(Usuario creador){
        Encuesta encuesta = new Encuesta(usuario: creador, titulo: "Votacion Presidencial 2019", descripcion: "encuesta sobre elecciones en Argentina y distintos candidatos")

        Pregunta pregunta = new Pregunta(enunciado: "¿Que Presidente eligiría?", orden: 1)
        pregunta.save()
        encuesta.addToPreguntas(pregunta)

        Opcion opcion = new Opcion(descripcion: "Alberto Fernandez", orden: 1)
        opcion.save()
        Opcion opcion2 = new Opcion(descripcion: "Mauricio Macri", orden: 2)
        opcion2.save()
        Opcion opcion3 = new Opcion(descripcion: "Ninguno", orden: 3)
        opcion3.save()

        pregunta.addToOpciones(opcion)
        pregunta.addToOpciones(opcion2)
        pregunta.addToOpciones(opcion3)

        pregunta = new Pregunta(enunciado: "¿Que Gobernador eligiría?", orden: 2)
        pregunta.save()
        encuesta.addToPreguntas(pregunta)

        opcion = new Opcion(descripcion: "Lamens", orden: 1)
        opcion.save()
        opcion2 = new Opcion(descripcion: "Tombolini", orden: 2)
        opcion2.save()
        opcion3 = new Opcion(descripcion: "Larreta", orden: 3)
        opcion3.save()
        Opcion opcion4 = new Opcion(descripcion: "Ninguno", orden: 4)
        opcion4.save()

        pregunta.addToOpciones(opcion)
        pregunta.addToOpciones(opcion2)
        pregunta.addToOpciones(opcion3)
        pregunta.addToOpciones(opcion4)

        pregunta = new Pregunta(enunciado: "¿Cual de los siguientes politicos le inspira mas confianza?", orden: 3)
        pregunta.save()
        encuesta.addToPreguntas(pregunta)

        opcion = new Opcion(descripcion: "Cristina Kirchner", orden: 1)
        opcion.save()
        opcion2 = new Opcion(descripcion: "Sergio Massa", orden: 2)
        opcion2.save()
        opcion3 = new Opcion(descripcion: "Jose Espert", orden: 3)
        opcion3.save()
        opcion4 = new Opcion(descripcion: "Ninguno", orden: 4)
        opcion4.save()

        pregunta.addToOpciones(opcion)
        pregunta.addToOpciones(opcion2)
        pregunta.addToOpciones(opcion3)
        pregunta.addToOpciones(opcion4)

        pregunta = new Pregunta(enunciado: "¿Como considera su situacion economica Personal HOY?", orden: 4)
        pregunta.save()
        encuesta.addToPreguntas(pregunta)

        opcion = new Opcion(descripcion: "Buena", orden: 1)
        opcion.save()
        opcion2 = new Opcion(descripcion: "Mala", orden: 2)
        opcion2.save()

        pregunta.addToOpciones(opcion)
        pregunta.addToOpciones(opcion2)

        pregunta = new Pregunta(enunciado: "¿Como considera la situacion economica actual del país?", orden: 5)
        pregunta.save()
        encuesta.addToPreguntas(pregunta)

        opcion = new Opcion(descripcion: "Buena", orden: 1)
        opcion.save()
        opcion2 = new Opcion(descripcion: "Mala", orden: 2)
        opcion2.save()

        pregunta.addToOpciones(opcion)
        pregunta.addToOpciones(opcion2)

        Date ahora = new Date()
        Date fechaFin = new Date()
        def dias = 90
        fechaFin.setDate(ahora.getDate()+dias)
        Vigencia vigencia = new Vigencia(fechaInicio: ahora, fechaFin: fechaFin)
        encuesta.vigencia = vigencia
        encuesta.save()
    }

    def destroy = {
    }
}
