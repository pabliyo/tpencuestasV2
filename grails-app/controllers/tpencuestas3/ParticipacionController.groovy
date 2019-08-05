package tpencuestas3

import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_ADMIN', 'ROLE_USER'])
class ParticipacionController {

    ParticipacionService participacionService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(){
        redirect(uri:"/")
    }

    def show(){
        [encuestas: participacionService.encuestasValidas()]
    }

    def propias(){
        [encuestasUsuarioActual: participacionService.getEncuestasUsuarioActual()]
    }

    def resultados(){
        [respuestasUsuarioActual: participacionService.getRespondidasUsuarioActual(),
         esUsuarioActualPremium: participacionService.esUsuarioActualPremium(),
         respuestasTodosUsuarios: participacionService.getRespondidasPorTodos()]
    }

    def participar(Long id){
        [encuesta: Encuesta.get(id)]
    }

    def guardarRespuestas(){
        Usuario usuario = participacionService.getUsuarioActual()
        Encuesta encuesta = Encuesta.get(params.id)
        def respuestas = new Respuesta(votante: usuario, encuesta: encuesta)

        try{
            participacionService.guardar(encuesta,params,respuestas)
        } catch (NoRespondioException e) {
            flash.message = e.getMessage()
            respond encuesta, view: 'participar', id: encuesta.id
            return
        }
        [encuesta: encuesta]
    }

}
