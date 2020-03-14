package al.rb.booking

import grails.gorm.transactions.Transactional

@grails.gorm.services.Service(GuestRoom)
interface GuestRoomService {
    GuestRoom get(Serializable id)

    List<GuestRoom> list(Map args)

    Long count()

    void delete(Serializable id)

    GuestRoom save(GuestRoom property)
}
