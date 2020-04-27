package santander

import grails.gorm.services.Service

@Service(Clima)
interface ClimaService {

    Clima get(Serializable id)

    List<Clima> list(Map args)

    Long count()

    void delete(Serializable id)

    Clima save(Clima clima)

}