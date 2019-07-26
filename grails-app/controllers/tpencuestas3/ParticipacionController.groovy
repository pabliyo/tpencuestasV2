package tpencuestas3

import grails.plugin.springsecurity.annotation.Secured
import jdk.nashorn.internal.runtime.options.Option
import org.hibernate.mapping.List
import org.hibernate.mapping.Map

@Secured('permitAll')
class ParticipacionController {

    ParticipacionService participacionService
    UsuarioService usuarioService
    EncuestaService encuestaService

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
        [encuesta: Encuesta.get(id) , respuestas: Respuesta.crearListaDeRespuestas(Encuesta.get(id))]
        //inicializar rta con los valores ya conociods fecha usr ect
    }

    def guardarRespuestas(){
        Usuario usuario = participacionService.getUsuarioActual()
        Encuesta encuesta = Encuesta.get(params.encuestaId)
        Respuesta respuestas = new Respuesta(params)
        println("aquiiii")
        println(respuestas)

        //participacionService.guardarRespuestas(usuario, encuesta)

        [encuesta: encuesta]
    }

    def resultados(){
        [encuesta: participacionService.getRespondidas()]
    }

}
