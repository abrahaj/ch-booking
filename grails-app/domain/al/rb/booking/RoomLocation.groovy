package al.rb.booking
/**
 * Container for room floor level and all possible floors.
 */
class RoomLocation {
    /**
     * The level of the building at which room is available.
     * 1 = ground floor(adds floor number '0' by default if not provided), 2 = upper floor,
     * 3 = both ground and upper floor(adds floor number '0' by default if not provided). Required
     */
    RoomLocationCode roomLocationCode
    /**
     * All the floors in the building which have the room
     * Add/Update TotalNumberOfFloors in the building before adding any floors for the room.
     */
    static hasMany =[floors:Floor]

    static constraints = {
        roomLocationCode nullable:false
        floors nullable:true
    }

    def buildXml(builder) {
        def roomLocationAttributes = [:]
        if (roomLocationCode) {
            roomLocationAttributes.put("RoomLocationCode", roomLocationCode.getCode())
        }
        builder."RoomLocation"(roomLocationAttributes)
        if (floors) {
            builder."Floors" {
                floors.each { f ->
                    f.buildXml(builder)
                }
            }
        }

    }

}
