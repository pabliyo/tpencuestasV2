package tpencuestas3

import grails.gorm.services.Service

@Service(Opcion)
interface OpcionService {

    Opcion get(Serializable id)

    List<Opcion> list(Map args)

    Long count()

    void delete(Serializable id)

    Opcion save(Opcion opcion)

}