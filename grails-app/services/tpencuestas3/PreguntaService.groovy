package tpencuestas3

import grails.gorm.services.Service
import grails.gorm.transactions.Transactional

@Service(Pregunta)
abstract class PreguntaService implements IPreguntaService{

    @Transactional
    Pregunta save(Pregunta pregunta,Usuario usuario,Encuesta encuesta){
        if(encuesta.puedeAgregarPreguntas(usuario)){
            super.save(pregunta)
        }
        else{
            throw new NoPremiumException()
        }
    }

}
