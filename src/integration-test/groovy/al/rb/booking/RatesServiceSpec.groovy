package al.rb.booking

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class RatesServiceSpec extends Specification {

    RatesService ratesService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Rates(...).save(flush: true, failOnError: true)
        //new Rates(...).save(flush: true, failOnError: true)
        //Rates rates = new Rates(...).save(flush: true, failOnError: true)
        //new Rates(...).save(flush: true, failOnError: true)
        //new Rates(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //rates.id
    }

    void "test get"() {
        setupData()

        expect:
        ratesService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Rates> ratesList = ratesService.list(max: 2, offset: 2)

        then:
        ratesList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        ratesService.count() == 5
    }

    void "test delete"() {
        Long ratesId = setupData()

        expect:
        ratesService.count() == 5

        when:
        ratesService.delete(ratesId)
        sessionFactory.currentSession.flush()

        then:
        ratesService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Rates rates = new Rates()
        ratesService.save(rates)

        then:
        rates.id != null
    }
}
