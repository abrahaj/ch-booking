package al.rb.booking

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class GuestRoomServiceSpec extends Specification {

    GuestRoomService guestRoomService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new GuestRoom(...).save(flush: true, failOnError: true)
        //new GuestRoom(...).save(flush: true, failOnError: true)
        //GuestRoom guestRoom = new GuestRoom(...).save(flush: true, failOnError: true)
        //new GuestRoom(...).save(flush: true, failOnError: true)
        //new GuestRoom(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //guestRoom.id
    }

    void "test get"() {
        setupData()

        expect:
        guestRoomService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<GuestRoom> guestRoomList = guestRoomService.list(max: 2, offset: 2)

        then:
        guestRoomList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        guestRoomService.count() == 5
    }

    void "test delete"() {
        Long guestRoomId = setupData()

        expect:
        guestRoomService.count() == 5

        when:
        guestRoomService.delete(guestRoomId)
        sessionFactory.currentSession.flush()

        then:
        guestRoomService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        GuestRoom guestRoom = new GuestRoom()
        guestRoomService.save(guestRoom)

        then:
        guestRoom.id != null
    }
}
