package al.rb.booking

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class RatePlanServiceSpec extends Specification {

    RatePlanService ratePlanService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new RatePlan(...).save(flush: true, failOnError: true)
        //new RatePlan(...).save(flush: true, failOnError: true)
        //RatePlan ratePlan = new RatePlan(...).save(flush: true, failOnError: true)
        //new RatePlan(...).save(flush: true, failOnError: true)
        //new RatePlan(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //ratePlan.id
    }

    void "test get"() {
        setupData()

        expect:
        ratePlanService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<RatePlan> ratePlanList = ratePlanService.list(max: 2, offset: 2)

        then:
        ratePlanList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        ratePlanService.count() == 5
    }

    void "test delete"() {
        Long ratePlanId = setupData()

        expect:
        ratePlanService.count() == 5

        when:
        ratePlanService.delete(ratePlanId)
        sessionFactory.currentSession.flush()

        then:
        ratePlanService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        RatePlan ratePlan = new RatePlan()
        ratePlanService.save(ratePlan)

        then:
        ratePlan.id != null
    }
}
