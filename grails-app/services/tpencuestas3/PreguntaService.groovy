package tpencuestas3

import grails.gorm.services.Service
import grails.gorm.transactions.Transactional

@Service(Pregunta)
abstract class PreguntaService implements IPreguntaService{

    @Transactional
    Pregunta guardar(Pregunta pregunta,Usuario usuario,Encuesta encuesta){
        if(encuesta.puedeAgregarPreguntas(usuario)){
            save(pregunta)
        }
        else{
            throw new NoPremiumException()
        }
    }
}

