package santander

import java.time.LocalDateTime

class MeetUp {

    String titulo
    String descripcion
    LocalDateTime fecha
    String aprovisionar = ''

    //todo pasar a archivo config
    public static final MULTIPLICADO_SI_HACE_FRIO = 0.75
    public static final MULTIPLICADO_SI_ESTA_NORMAL = 1
    public static final MULTIPLICADO_SI_HACE_CALOR = 2
    public static final BIRRAS_POR_CAJA = 6
    public static final MENSAJE_CALC_CAJAS_NECESARIAS = 'Numero de cajas necesarias para aprovisionar la meetUp: '

    static belongsTo = Usuario
    static hasMany = [invitados: Usuario, asistentes: Usuario]//asistentes = atendee

    static constraints = {
        //todo limitar fecha a el rango de datos obtenibles
    }

    void calcularCantidadDeCajasDeBirra() {
        //la url template tiene la temperatura en kelvin
        BigDecimal clima = getClimaSegunFecha()
        Integer cantidadDePersonas = invitados.size()
        BigDecimal birrasNecesarias
        if (clima.compareTo(new BigDecimal(20)) == -1) {
            birrasNecesarias = cantidadDePersonas * MULTIPLICADO_SI_HACE_FRIO
        } else if (clima.compareTo(new BigDecimal(24)) == -1) {
            birrasNecesarias = cantidadDePersonas * MULTIPLICADO_SI_ESTA_NORMAL
        } else {
            birrasNecesarias = cantidadDePersonas * MULTIPLICADO_SI_HACE_CALOR
        }
        if (birrasNecesarias % BIRRAS_POR_CAJA != 0)
            aprovisionar = MENSAJE_CALC_CAJAS_NECESARIAS + (birrasNecesarias / BIRRAS_POR_CAJA).intValue() + 1
        else {
            aprovisionar = MENSAJE_CALC_CAJAS_NECESARIAS + (birrasNecesarias / BIRRAS_POR_CAJA).intValue()
        }
        //get cantidad de personas , multiplicarlo por segun clima clima.getAll, div por 6 y si el resto es mayor a 0 se le suma 1
    }

    BigDecimal getClimaSegunFecha() {
        for (Clima clima : Clima.findAll()) {
            if (fecha.isBefore(clima.dateTime)) {
                clima.temperatura
            }
        }
        Clima.getAll().get(Clima.getAll().size() - 1).temperatura
    }

    String toString() {
        titulo
    }

}
