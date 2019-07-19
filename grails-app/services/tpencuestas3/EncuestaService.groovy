package tpencuestas3

import grails.gorm.services.Service

@Service(Encuesta)
interface EncuestaService {

    Encuesta get(Serializable id)

    //Encuesta diseñar(Usuario usuario)

    //void set(Serializable id)

    List<Encuesta> list(Map args)

    Long count()

    void delete(Serializable id)

    Encuesta save(Encuesta encuesta)

}