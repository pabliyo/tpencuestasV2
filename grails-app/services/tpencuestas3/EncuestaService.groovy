package tpencuestas3

import grails.gorm.services.Service
import grails.gorm.transactions.Transactional

@Service(Encuesta)
abstract class EncuestaService implements IEncuestaService{

    @Transactional
    Encuesta guardar(Encuesta encuesta, Usuario usuario){
        if (encuesta.puedeCrearEncuesta(usuario)){
            save(encuesta)
        }
        else {
            throw new NoPremiumException()
        }
    }

    boolean tieneVotaciones(Encuesta encuesta){
        List<Respuesta> lista = Respuesta.findAllWhere(encuesta: encuesta)
        return !lista.isEmpty()
    }

}
