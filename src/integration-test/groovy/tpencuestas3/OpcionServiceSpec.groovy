package tpencuestas3

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class OpcionServiceSpec extends Specification {

    OpcionService opcionService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Opcion(...).save(flush: true, failOnError: true)
        //new Opcion(...).save(flush: true, failOnError: true)
        //Opcion opcion = new Opcion(...).save(flush: true, failOnError: true)
        //new Opcion(...).save(flush: true, failOnError: true)
        //new Opcion(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //opcion.id
    }

    void "test get"() {
        setupData()

        expect:
        opcionService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Opcion> opcionList = opcionService.list(max: 2, offset: 2)

        then:
        opcionList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        opcionService.count() == 5
    }

    void "test delete"() {
        Long opcionId = setupData()

        expect:
        opcionService.count() == 5

        when:
        opcionService.delete(opcionId)
        sessionFactory.currentSession.flush()

        then:
        opcionService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Opcion opcion = new Opcion()
        opcionService.save(opcion)

        then:
        opcion.id != null
    }
}
