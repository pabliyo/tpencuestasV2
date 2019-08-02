package tpencuestas3

import grails.gorm.services.Service

interface IOpcionService {

    Opcion get(Serializable id)

    List<Opcion> list(Map args)

    Long count()

    void delete(Serializable id)

    Opcion save(Opcion opcion)

}