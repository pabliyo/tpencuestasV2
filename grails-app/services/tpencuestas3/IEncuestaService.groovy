package tpencuestas3


interface IEncuestaService {

    Encuesta get(Serializable id)

    List<Encuesta> list(Map args)

    Long count()

    void delete(Serializable id)

    Encuesta save(Encuesta encuesta)

}