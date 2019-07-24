package tpencuestas3

import grails.gorm.services.Service
import grails.plugin.springsecurity.SpringSecurityService
import org.hibernate.mapping.Map
import org.springframework.beans.factory.annotation.Autowired

@Service
class ParticipacionService {

    @Autowired
    SpringSecurityService springSecurityService

    Usuario getUsuarioActual() {
        springSecurityService.getCurrentUser() as Usuario
    }

    List encuestasValidas() {
        //def now = new Date()
        Encuesta.findAll()
        //Encuesta.findAllByStartDateLessThanAndExpirationDateGreaterThan(now, now)
    }

    List getEncuestasUsuarioActual(){
        Encuesta.findAllByUsuario(springSecurityService.getCurrentUser() as Usuario)
    }

    List getRespondidas(){
        Respuesta.findAllByVotante(springSecurityService.getCurrentUser())
    }

    void guardarRespuestas(Usuario usuario, Encuesta encuesta) {
        def respuesta = new Respuesta(votante: usuario, encuesta: encuesta)
        def preguntas = encuesta.preguntas
        preguntas.each { preguntaId ->

            println(preguntaId)
            if(preguntaId){
                preguntaId. { opcionValue ->

                    println(opcionId)

                }
            }

                //def opcion = new Opcion(pregunta: preguntas, orden: preguntaId.opciones.getOrden() ,descripcion: Opcion.getDescripcion())

               // print(opcion)
               // respuesta.pregunta=preguntaId
               // respuesta.elecciones.add(preguntas.get(preguntaId).getOrden(), preguntaId.opciones)


        }
        respuesta.save()
    }



}
