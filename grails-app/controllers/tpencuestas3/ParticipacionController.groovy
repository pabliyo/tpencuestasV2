package tpencuestas3

import grails.plugin.springsecurity.annotation.Secured

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

    def propias(Long id){
        //Usuario usuario = usuarioService.get(id)
        [encuestas: participacionService.getEncuestas()]
    }

    def participar(Long id){
        [encuesta: Encuesta.get(id)]
    }

    def guardarRespuestas(){

    }

}
