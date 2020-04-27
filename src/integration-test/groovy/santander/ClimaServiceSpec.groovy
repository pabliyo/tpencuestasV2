package santander

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class ClimaServiceSpec extends Specification {

    ClimaService climaService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Clima(...).save(flush: true, failOnError: true)
        //new Clima(...).save(flush: true, failOnError: true)
        //Clima clima = new Clima(...).save(flush: true, failOnError: true)
        //new Clima(...).save(flush: true, failOnError: true)
        //new Clima(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //clima.id
    }

    void "test get"() {
        setupData()

        expect:
        climaService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Clima> climaList = climaService.list(max: 2, offset: 2)

        then:
        climaList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        climaService.count() == 5
    }

    void "test delete"() {
        Long climaId = setupData()

        expect:
        climaService.count() == 5

        when:
        climaService.delete(climaId)
        sessionFactory.currentSession.flush()

        then:
        climaService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Clima clima = new Clima()
        climaService.save(clima)

        then:
        clima.id != null
    }
}
