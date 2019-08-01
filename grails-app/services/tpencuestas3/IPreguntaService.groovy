package tpencuestas3

interface IPreguntaService {

    Pregunta get(Serializable id)

    List<Pregunta> list(Map args)

    Long count()

    void delete(Serializable id)

    Pregunta save(Pregunta pregunta)

}