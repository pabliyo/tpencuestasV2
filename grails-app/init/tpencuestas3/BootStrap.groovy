package tpencuestas3

class BootStrap {

    def init = { servletContext ->
        8.times { encuestaIndex ->

           // Encuesta encuesta = new Encuesta(titulo: "Encuesta ${encuestaIndex}", descripcion: "descripcion ${encuestaIndex}")
            //2.times { preguntaIndex ->
            //    encuesta.addToPreguntas(enunciado: "enunciado ${encuestaIndex} - ${preguntaIndex}" )
            }
            // encuesta.save()
    }
    def destroy = {
    }
}
