package tpencuestas3

import grails.gorm.services.Service
import org.hibernate.mapping.Map

@Service
class ParticipacionService {

    List encuestasValidas() {
        //def now = new Date()
        Encuesta.findAll()
        //Encuesta.findAllByStartDateLessThanAndExpirationDateGreaterThan(now, now)
    }

    List getEncuestas(){
        Usuario.findAll()
    }


}
