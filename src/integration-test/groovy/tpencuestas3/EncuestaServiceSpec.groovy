package tpencuestas3

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class EncuestaServiceSpec extends Specification {

    EncuestaService encuestaService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Encuesta(...).save(flush: true, failOnError: true)
        //new Encuesta(...).save(flush: true, failOnError: true)
        //Encuesta encuesta = new Encuesta(...).save(flush: true, failOnError: true)
        //new Encuesta(...).save(flush: true, failOnError: true)
        //new Encuesta(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //encuesta.id
    }

    void "test get"() {
        setupData()

        expect:
        encuestaService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Encuesta> encuestaList = encuestaService.list(max: 2, offset: 2)

        then:
        encuestaList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        encuestaService.count() == 5
    }

    void "test delete"() {
        Long encuestaId = setupData()

        expect:
        encuestaService.count() == 5

        when:
        encuestaService.delete(encuestaId)
        sessionFactory.currentSession.flush()

        then:
        encuestaService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Encuesta encuesta = new Encuesta()
        encuestaService.save(encuesta)

        then:
        encuesta.id != null
    }
}
