package tpencuestas3

import grails.gorm.services.Service

@Service(Pregunta)
interface PreguntaService {

    Pregunta get(Serializable id)

    List<Pregunta> list(Map args)

    Long count()

    void delete(Serializable id)

    Pregunta save(Pregunta pregunta)

}