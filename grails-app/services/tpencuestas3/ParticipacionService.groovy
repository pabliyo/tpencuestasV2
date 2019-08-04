package tpencuestas3

import grails.gorm.services.Service
import grails.plugin.springsecurity.SpringSecurityService
import java.util.Map
import org.springframework.beans.factory.annotation.Autowired

@Service
class ParticipacionService {

    @Autowired
    SpringSecurityService springSecurityService

    RespuestaService respuestaService

    Usuario getUsuarioActual() {
        springSecurityService.getCurrentUser() as Usuario
    }

    List encuestasValidas(){
        Encuesta.findAllByIdNotInListAnd (getRespondidas().encuesta.id)
    }

    List getEncuestasUsuarioActual() {
        Encuesta.findAllByUsuario(springSecurityService.getCurrentUser() as Usuario)
    }

    List getRespondidas() {
        Respuesta.findAllByVotante(springSecurityService.getCurrentUser() as Usuario)
    }

    Respuesta guardar(Encuesta encuesta, Map params, Respuesta respuesta){
        if(respuestasValidas(encuesta,params,respuesta)){
            respuestaService.save(respuesta)
        }
        else{
            throw new NoRespondioException()
        }
    }

    boolean respuestasValidas(Encuesta encuesta, Map params, Respuesta respuesta){
        boolean validas = true
        int cantPreg = encuesta.cantidadPreguntas()
        int i = 0
        respuesta.initCollection()
        params.each{ preguntaId, opcionId ->
            if(i < cantPreg) {
                if ((!preguntaId.isLong())||(!opcionId.isLong())) {
                    validas = false
                }
                if (preguntaId.isLong()) {
                    respuesta.agregarRespuesta(Opcion.get(opcionId))
                }
                else{
                    throw new NoRespondioException()
                }
                i=i+1
            }
        }
        respuesta.agregarFechaVotacion()
        return validas
    }

}
