package al.rb.booking

import grails.gorm.services.Service

@Service(RatePlan)
interface RatePlanService {

    RatePlan get(Serializable id)

    List<RatePlan> list(Map args)

    Long count()

    void delete(Serializable id)

    RatePlan save(RatePlan ratePlan)

}