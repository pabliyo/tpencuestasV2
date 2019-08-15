package tpencuestas3

class BootStrap {

    def init = { servletContext ->
        initUsersRolesAndEncuestas()
    }

    private static crearEncuestas(Usuario creador) {
        Date ahora = new Date()
        2.times { encuestaIndex ->
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

        Usuario admin = new Usuario(username: 'admin', password: 'admin', cuentaPremium: true, email: 'email0@email.com').save()
        Usuario usuario = new Usuario(username: 'usuario', password: 'usuario', cuentaPremium: true, email: 'email1@email.com').save()
        Usuario usuario2 = new Usuario(username: 'usuario2', password: 'usuario2', cuentaPremium: true, email: 'email2@email.com').save()
        Usuario usuario3 = new Usuario(username: 'usuario3', password: 'usuario3', cuentaPremium: true, email: 'email3@email.com').save()
        Usuario noPremium = new Usuario(username: 'noPremium', password: 'noPremium', cuentaPremium: false, email: 'email4@email.com').save()

        UsuarioRol.create(usuario, Rol.getUserRol().save(), true)
        UsuarioRol.create(noPremium, Rol.getUserRol().save(), true)
        UsuarioRol.create(admin, Rol.getAdminRol().save(), true)

        creadorEncuestas(admin)
        creadorEncuestas2(usuario)
        creadorEncuestas3(usuario)
        creadorEncuestas4(usuario)
        creadorEncuestas5(usuario)
        crearEncuestas(noPremium)

        respondeEncuestas(usuario,1)
        respondeEncuestas(usuario2,2)
        respondeEncuestas(usuario3,2)
        respondeEncuestas(noPremium,2)

    }

    private static respondeEncuestas(Usuario usuario, int opcion){
        def lista = Encuesta.findAll()
        lista.each() { encuesta ->
            def respuesta = new Respuesta(votante: usuario, encuesta: encuesta, fechaVoto: new Date())
            respuesta.initCollection()
            encuesta.preguntas.each() { pregunta ->
                respuesta.agregarRespuesta(pregunta.opciones[opcion])
            }
            respuesta.save()
        }
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

    private static creadorEncuestas2(Usuario usuario){
        def encuesta= creaEncuesta(usuario,"Productos y marcas de Alimentos", "encuesta sobre los productos que utilizamos a diario")
        def pregunta= creaPregunta(encuesta,"¿Que mayonesa consume normalmente?", 1)
        creaOpcion(pregunta, "Natura", 1)
        creaOpcion(pregunta, "Hellmans", 2)
        creaOpcion(pregunta, "Ninguno", 3)
        pregunta= creaPregunta(encuesta,"¿Que bebida consume con la comida?", 2)
        creaOpcion(pregunta, "Gaseosa", 1)
        creaOpcion(pregunta, "Jugo", 2)
        creaOpcion(pregunta, "Agua", 3)
        creaOpcion(pregunta, "Cerveza", 4)
        pregunta= creaPregunta(encuesta,"¿Con que Jabon lava su ropa?", 3)
        creaOpcion(pregunta, "Jabon a mano", 1)
        creaOpcion(pregunta, "Jabon en polvo", 2)
        creaOpcion(pregunta, "Jabon liquido", 3)
        pregunta= creaPregunta(encuesta,"¿Que marca de desodorante prefiere?", 4)
        creaOpcion(pregunta, "Axe", 1)
        creaOpcion(pregunta, "Nivea", 2)
        creaOpcion(pregunta, "Rexona", 3)
        creaOpcion(pregunta, "Otro", 4)
        pregunta= creaPregunta(encuesta,"¿Que detergente utiliza?", 5)
        creaOpcion(pregunta, "Magistral", 1)
        creaOpcion(pregunta, "Ala", 2)
        creaOpcion(pregunta, "Otro", 3)
        encuesta.save()
    }

    private static creadorEncuestas3(Usuario usuario){
        def encuesta= creaEncuesta(usuario,"Lenguajes de Programacion", "encuesta sobre los distintos lenguajes de programacion mas utilizados comunmente")
        def pregunta= creaPregunta(encuesta,"¿Que lenguaje de programacion prefiere?", 1)
        creaOpcion(pregunta, "Java", 1)
        creaOpcion(pregunta, "Python", 2)
        creaOpcion(pregunta, "C", 3)
        creaOpcion(pregunta, "Ninguno de los anteriores", 4)
        pregunta= creaPregunta(encuesta,"¿Que Lenguaje de scripting prefiere?", 2)
        creaOpcion(pregunta, "JavaScript", 1)
        creaOpcion(pregunta, "Lua", 2)
        creaOpcion(pregunta, "Python", 3)
        creaOpcion(pregunta, "No programo en ninguno", 4)
        creaOpcion(pregunta, "Otro", 4)
        pregunta= creaPregunta(encuesta,"A la hora de programar ¿En Que paradigma se siente mas comodo ?", 3)
        creaOpcion(pregunta, " POO Orientado a Objetos", 1)
        creaOpcion(pregunta, "Programacion Funcional", 2)
        creaOpcion(pregunta, "Declarativo", 3)
        creaOpcion(pregunta, "Ninguno de los anteriores", 4)
        pregunta= creaPregunta(encuesta,"Tiene que realizar una aplicacion Web ¿Con cual de estas herramientas se inclanaria para usar?", 4)
        creaOpcion(pregunta, "Visual Vasic .NET", 1)
        creaOpcion(pregunta, "Grails", 2)
        creaOpcion(pregunta, "Php", 3)
        creaOpcion(pregunta, "Otro", 4)
        encuesta.save()
    }

    private static creadorEncuestas4(Usuario usuario){
        def encuesta= creaEncuesta(usuario,"Sistemas operativos", "encuesta sobre los distintos sistemas operativos mas utilizados comunmente")
        def pregunta= creaPregunta(encuesta,"¿Que sistema operativo utiliza cotidianamente en su ordenador?", 1)
        creaOpcion(pregunta, "Microsoft Windows", 1)
        creaOpcion(pregunta, "Linux / Ubuntu", 2)
        creaOpcion(pregunta, "Mac Os", 3)
        creaOpcion(pregunta, "Microsoft y Linux", 4)
        creaOpcion(pregunta, "Todos", 4)
        creaOpcion(pregunta, "No Uso ordenador", 5)
        pregunta= creaPregunta(encuesta,"¿Que sistema/s usa en su Telefono Movil?", 2)
        creaOpcion(pregunta, "Android", 1)
        creaOpcion(pregunta, "IOs", 2)
        creaOpcion(pregunta, "Windows Phone", 3)
        creaOpcion(pregunta, "No uso movil", 4)
        pregunta= creaPregunta(encuesta,"¿Que busca primordialmente de un SO?", 3)
        creaOpcion(pregunta, "que sea muy rapido", 1)
        creaOpcion(pregunta, "que se vea muy elegante", 2)
        creaOpcion(pregunta, "poder ver muchas aplicaciones al mismo tiempo", 3)
        pregunta= creaPregunta(encuesta,"¿En un ordenador de que lado le gusta tener la X para el cierre de la ventana?", 4)
        creaOpcion(pregunta, "Izquierda", 1)
        creaOpcion(pregunta, "Derecha", 2)
        creaOpcion(pregunta, "en el centro", 3)
        creaOpcion(pregunta, "Abajo", 4)
        pregunta= creaPregunta(encuesta,"¿Si le regalarian una computadora con Linux que haria?", 5)
        creaOpcion(pregunta, "La aceptaria muy contento", 1)
        creaOpcion(pregunta, "Se la regalaria a alguien que sepa usarla", 2)
        creaOpcion(pregunta, "Le borraria el sistema y le instalaria Windows", 3)
        encuesta.save()
    }

    private static creadorEncuestas5(Usuario usuario){
        def encuesta= creaEncuesta(usuario,"Redes Sociales", "Se intenta conocer que App de Red Social se preferie y por que")
        def pregunta= creaPregunta(encuesta,"¿Que applicacion Utiliza cotidianamente?", 1)
        creaOpcion(pregunta, "Facebook", 1)
        creaOpcion(pregunta, "Instagram", 2)
        creaOpcion(pregunta, "Snapchat", 3)
        creaOpcion(pregunta, "Todas las anteriores", 4)
        pregunta= creaPregunta(encuesta,"¿Cual le gusta mas?", 2)
        creaOpcion(pregunta, "Facebook", 1)
        creaOpcion(pregunta, "Instagram", 2)
        creaOpcion(pregunta, "Snapchat", 3)
        creaOpcion(pregunta, "Todas las anteriores", 4)
        pregunta= creaPregunta(encuesta,"Si tuviera que eliminar una,¿cual seria?", 3)
        creaOpcion(pregunta, "Facebook", 1)
        creaOpcion(pregunta, "Instagram", 2)
        creaOpcion(pregunta, "Snapchat", 3)
        creaOpcion(pregunta, "Ninguna", 4)
        creaOpcion(pregunta, "No me preocuparia eliminar todas", 4)
        pregunta= creaPregunta(encuesta,"¿Cual cree que es mas lenta al cargar?", 4)
        creaOpcion(pregunta, "Facebook", 1)
        creaOpcion(pregunta, "Instagram", 2)
        creaOpcion(pregunta, "Snapchat", 3)
        creaOpcion(pregunta, "Ninguna", 4)
        pregunta= creaPregunta(encuesta,"¿Considera que ocupan mucho espacio?", 5)
        creaOpcion(pregunta, "Si", 1)
        creaOpcion(pregunta, "No", 2)
        creaOpcion(pregunta, "No lo se", 3)
        encuesta.save()
    }

    private static creadorEncuestas$(Usuario usuario){
        def encuesta= creaEncuesta(usuario,"", "")
        def pregunta= creaPregunta(encuesta,"¿?", 1)
        creaOpcion(pregunta, "", 1)
        creaOpcion(pregunta, "", 2)
        creaOpcion(pregunta, "", 3)
        pregunta= creaPregunta(encuesta,"¿?", 2)
        creaOpcion(pregunta, "", 1)
        creaOpcion(pregunta, "", 2)
        creaOpcion(pregunta, "", 3)
        creaOpcion(pregunta, "", 4)
        pregunta= creaPregunta(encuesta,"¿?", 3)
        creaOpcion(pregunta, "", 1)
        creaOpcion(pregunta, "", 2)
        creaOpcion(pregunta, "", 3)
        pregunta= creaPregunta(encuesta,"¿?", 4)
        creaOpcion(pregunta, "", 1)
        creaOpcion(pregunta, "", 2)
        creaOpcion(pregunta, "", 3)
        creaOpcion(pregunta, "", 4)
        pregunta= creaPregunta(encuesta,"¿?", 5)
        creaOpcion(pregunta, "", 1)
        creaOpcion(pregunta, "", 2)
        creaOpcion(pregunta, "", 3)
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
