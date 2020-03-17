package al.rb.booking

import grails.gorm.services.Service

@Service(HotelProduct)
interface HotelProductService {

    HotelProduct get(Serializable id)

    List<HotelProduct> list(Map args)

    Long count()

    void delete(Serializable id)

    HotelProduct save(HotelProduct hotelProduct)

}