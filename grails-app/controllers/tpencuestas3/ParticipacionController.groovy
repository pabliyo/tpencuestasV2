package tpencuestas3

import grails.plugin.springsecurity.annotation.Secured

@Secured('permitAll')
class ParticipacionController {

    ParticipacionService participacionService

    def index(){
        redirect(uri:"/")
    }

    def show(){
        [encuestas: participacionService.encuestasValidas()]
    }

    def propias(){
        [encuestas: participacionService.getEncuestasUsuarioActual()]
    }

    def participar(Long id){
        [encuesta: Encuesta.get(id)]
    }

    def guardarRespuestas(){
        Usuario usuario = participacionService.getUsuarioActual()
        Encuesta encuesta = Encuesta.get(params.id)
        def respuestas = new Respuesta(votante: usuario, encuesta: encuesta)

        println("ids de respuestas!!")
        println(params)

        respuestas = participacionService.guardarVotacion(respuestas,params)

        println("guardado")
        println(respuestas.respuestas)

        //falta implementar busquedas para quitar la encuesta respondida por el usuario

        [encuesta: encuesta]
    }

    def resultados(){
        [encuesta: participacionService.getRespondidas()]
    }

}
