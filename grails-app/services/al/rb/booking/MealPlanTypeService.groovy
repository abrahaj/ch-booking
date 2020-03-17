package al.rb.booking

import grails.gorm.services.Service

@Service(MealPlanType)
interface MealPlanTypeService {

    MealPlanType get(Serializable id)

    List<MealPlanType> list(Map args)

    Long count()

    void delete(Serializable id)

    MealPlanType save(MealPlanType mealPlanType)

}