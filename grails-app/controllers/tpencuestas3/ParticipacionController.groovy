package tpencuestas3

import grails.plugin.springsecurity.annotation.Secured

import java.util.Map

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
        def respuesta = new Respuesta(votante: usuario, encuesta: encuesta)

        println("ids de respuestas!!")
        println(params)

        respuesta = participacionService.guardarVotacion(respuesta, generarMapFromParams)

        println("guardado")
        println(respuesta.respuestas)

        //falta implementar busquedas para quitar la encuesta respondida por el usuario

        [encuesta: encuesta]
    }

    def resultados(){
        [encuesta: participacionService.getRespondidas()]
    }

    private Map generarMapFromParams
    {
        Map paramsMap = new HashMap()

        params.each { key, value ->
            paramsMap.put(key, value)
        }
        paramsMap
    }

}
