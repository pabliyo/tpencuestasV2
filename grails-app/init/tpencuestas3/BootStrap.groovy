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
        creadorEncuestas(admin)

    }

    private static creadorEncuestas(Usuario usuario){
        def encuesta= creaEncuesta(usuario,"Votacion Presidencial 2019", "encuesta sobre elecciones en Argentina y distintos candidatos")
        def pregunta= creaPregunta(encuesta,"¿Que Presidente eligiría?", 1)
        creaOpcion(pregunta, "Alberto Fernandez", 1)
        creaOpcion(pregunta, "Mauricio Macri", 2)
        creaOpcion(pregunta, "Ninguno", 3)
        pregunta= creaPregunta(encuesta,"¿Que candidato a Gobernador eligiría?", 2)
        creaOpcion(pregunta, "Lamens", 1)
        creaOpcion(pregunta, "Tombolini", 2)
        creaOpcion(pregunta, "Larreta", 3)
        creaOpcion(pregunta, "Ninguno", 4)
        pregunta= creaPregunta(encuesta,"¿Cual de los siguientes politicos le inspira mas confianza?", 3)
        creaOpcion(pregunta, "Cristina Kirchner", 1)
        creaOpcion(pregunta, "Sergio Massa", 2)
        creaOpcion(pregunta, "Jose Espert", 3)
        creaOpcion(pregunta, "Ninguno", 4)
        pregunta= creaPregunta(encuesta,"¿Como considera su situacion economica Personal HOY?", 4)
        creaOpcion(pregunta, "Buena", 1)
        creaOpcion(pregunta, "Mala", 2)
        pregunta= creaPregunta(encuesta,"¿Como considera la situacion economica actual del país?", 5)
        creaOpcion(pregunta, "Buena", 1)
        creaOpcion(pregunta, "Mala", 2)
        encuesta.save()
    }

    private static Encuesta creaEncuesta(Usuario usuario, String titulo, String descripcion){
        Encuesta encuesta = new Encuesta(usuario: usuario, titulo: titulo, descripcion: descripcion)
        Date ahora = new Date()
        Date fechaFin = new Date()
        def dias = 90
        fechaFin.setDate(ahora.getDate()+dias)
        Vigencia vigencia = new Vigencia(fechaInicio: ahora, fechaFin: fechaFin)
        encuesta.vigencia = vigencia
        encuesta
    }

    private static Pregunta creaPregunta(Encuesta encuesta, String enunciado, int orden){
        Pregunta pregunta = new Pregunta(enunciado: enunciado, orden: orden)
        pregunta.save()
        encuesta.addToPreguntas(pregunta)
        pregunta
    }

    private static creaOpcion(Pregunta pregunta, String enunciado, int orden){
        Opcion opcion = new Opcion(descripcion: enunciado, orden: orden)
        opcion.save()
        pregunta.addToOpciones(opcion)
    }

    def destroy = {
    }
}
