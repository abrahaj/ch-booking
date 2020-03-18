package al.rb.booking

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class HotelProductServiceSpec extends Specification {

    HotelProductService hotelProductService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new HotelProduct(...).save(flush: true, failOnError: true)
        //new HotelProduct(...).save(flush: true, failOnError: true)
        //HotelProduct hotelProduct = new HotelProduct(...).save(flush: true, failOnError: true)
        //new HotelProduct(...).save(flush: true, failOnError: true)
        //new HotelProduct(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //hotelProduct.id
    }

    void "test get"() {
        setupData()

        expect:
        hotelProductService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<HotelProduct> hotelProductList = hotelProductService.list(max: 2, offset: 2)

        then:
        hotelProductList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        hotelProductService.count() == 5
    }

    void "test delete"() {
        Long hotelProductId = setupData()

        expect:
        hotelProductService.count() == 5

        when:
        hotelProductService.delete(hotelProductId)
        sessionFactory.currentSession.flush()

        then:
        hotelProductService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        HotelProduct hotelProduct = new HotelProduct()
        hotelProductService.save(hotelProduct)

        then:
        hotelProduct.id != null
    }
}
