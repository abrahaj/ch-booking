package al.rb.booking

import grails.gorm.services.Service

@Service(Rates)
interface RatesService {

    Rates get(Serializable id)

    List<Rates> list(Map args)

    Long count()

    void delete(Serializable id)

    Rates save(Rates rates)

}