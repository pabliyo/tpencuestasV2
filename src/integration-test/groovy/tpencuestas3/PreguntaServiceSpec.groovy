package tpencuestas3

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class PreguntaServiceSpec extends Specification {

    PreguntaService preguntaService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Pregunta(...).save(flush: true, failOnError: true)
        //new Pregunta(...).save(flush: true, failOnError: true)
        //Pregunta pregunta = new Pregunta(...).save(flush: true, failOnError: true)
        //new Pregunta(...).save(flush: true, failOnError: true)
        //new Pregunta(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //pregunta.id
    }

    void "test get"() {
        setupData()

        expect:
        preguntaService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Pregunta> preguntaList = preguntaService.list(max: 2, offset: 2)

        then:
        preguntaList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        preguntaService.count() == 5
    }

    void "test delete"() {
        Long preguntaId = setupData()

        expect:
        preguntaService.count() == 5

        when:
        preguntaService.delete(preguntaId)
        sessionFactory.currentSession.flush()

        then:
        preguntaService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Pregunta pregunta = new Pregunta()
        preguntaService.save(pregunta)

        then:
        pregunta.id != null
    }
}
