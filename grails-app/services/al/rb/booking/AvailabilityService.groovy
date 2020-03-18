package al.rb.booking

import grails.gorm.services.Service

@Service(Availability)
interface AvailabilityService {

    Availability get(Serializable id)

    List<Availability> list(Map args)

    Long count()

    void delete(Serializable id)

    Availability save(Availability availability)

}