package tpencuestas3

import grails.gorm.services.Service
import grails.gorm.transactions.Transactional

@Service(Encuesta)
abstract class EncuestaService implements IEncuestaService {

    @Transactional
    Encuesta guardar(Encuesta encuesta, Usuario usuario) {
        if (encuesta.puedeCrearEncuesta(usuario)) {
            save(encuesta)
        } else {
            throw new NoPremiumException()
        }
    }

    void validacionVigencia(Encuesta encuesta, Usuario usuario){
        usuario.vigenciaDelUsuario(encuesta)
    }

    boolean tieneVotaciones(Encuesta encuesta) {
        encuesta.tieneVotaciones()
    }

}
