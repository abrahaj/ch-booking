package al.rb.booking
/**
 * Details of a room.
 */
class GuestRoom {
    /**
     * Details of a property-level amenity.
     */
//    ArrayList<Amenity> amenities
    static hasMany = [amenities: Amenity]
    static constraints = {
        amenities nullable: true
    }

    def buildXml(builder) {
        builder."GuestRoom" {
            if (amenities) {
                builder."Amenities" {
                    amenities.each { am ->
                        am.buildXml(builder)
                    }
                }
            }
        }
    }
}
