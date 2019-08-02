package tpencuestas3

import grails.gorm.services.Service
import grails.gorm.transactions.Transactional

@Service(Opcion)
abstract class OpcionService implements IOpcionService{

    @Transactional
    Opcion guardar(Opcion opcion, Usuario usuario, Pregunta pregunta){
        if(pregunta.puedeAgregarOpciones(usuario)){
            save(opcion)
        }
        else{
            throw new NoPremiumException()
        }
    }
}
