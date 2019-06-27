package tpencuestas3

class Vigencia {

    Boolean estaVigente
    Date fechaInicio
    Date fechaFin


    static constraints = {
        estaVigente()
        fechaInicio()
        fechaFin()
    }


}
