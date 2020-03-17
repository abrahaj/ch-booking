package al.rb.booking

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class MealPlanTypeServiceSpec extends Specification {

    MealPlanTypeService mealPlanTypeService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new MealPlanType(...).save(flush: true, failOnError: true)
        //new MealPlanType(...).save(flush: true, failOnError: true)
        //MealPlanType mealPlanType = new MealPlanType(...).save(flush: true, failOnError: true)
        //new MealPlanType(...).save(flush: true, failOnError: true)
        //new MealPlanType(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //mealPlanType.id
    }

    void "test get"() {
        setupData()

        expect:
        mealPlanTypeService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<MealPlanType> mealPlanTypeList = mealPlanTypeService.list(max: 2, offset: 2)

        then:
        mealPlanTypeList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        mealPlanTypeService.count() == 5
    }

    void "test delete"() {
        Long mealPlanTypeId = setupData()

        expect:
        mealPlanTypeService.count() == 5

        when:
        mealPlanTypeService.delete(mealPlanTypeId)
        sessionFactory.currentSession.flush()

        then:
        mealPlanTypeService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        MealPlanType mealPlanType = new MealPlanType()
        mealPlanTypeService.save(mealPlanType)

        then:
        mealPlanType.id != null
    }
}
