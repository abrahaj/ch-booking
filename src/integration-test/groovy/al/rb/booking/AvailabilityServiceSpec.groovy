package al.rb.booking

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class AvailabilityServiceSpec extends Specification {

    AvailabilityService availabilityService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Availability(...).save(flush: true, failOnError: true)
        //new Availability(...).save(flush: true, failOnError: true)
        //Availability availability = new Availability(...).save(flush: true, failOnError: true)
        //new Availability(...).save(flush: true, failOnError: true)
        //new Availability(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //availability.id
    }

    void "test get"() {
        setupData()

        expect:
        availabilityService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Availability> availabilityList = availabilityService.list(max: 2, offset: 2)

        then:
        availabilityList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        availabilityService.count() == 5
    }

    void "test delete"() {
        Long availabilityId = setupData()

        expect:
        availabilityService.count() == 5

        when:
        availabilityService.delete(availabilityId)
        sessionFactory.currentSession.flush()

        then:
        availabilityService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Availability availability = new Availability()
        availabilityService.save(availability)

        then:
        availability.id != null
    }
}
