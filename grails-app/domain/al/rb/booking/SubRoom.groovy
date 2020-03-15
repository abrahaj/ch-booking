package al.rb.booking

class SubRoom {
    /**
     *The maximum number of guests allowed.
     */
    int maxGuests
    /**
     * The number of subrooms of the specified type.
     */
    int number
    /**
     *Specifies whether the subroom has a private bathroom (1) or not (0).
     * Accepts: 0, 1
     */
    boolean  privateBathroom
    /**
     * The type of subroom.
     * Accepts: Bedroom, Living room, Bathroom
     */
    String roomType
    /**
     * Subroom amenities.
     */
    static hasMany = [amenities: Amenity]
    static constraints = {
        maxGuests nullable:true
        number nullable:true
        privateBathroom defaultValue:0
        roomType nullable: true,inList: ["Bedroom", "Living room", "Bathroom"]
        amenities nullable: true
    }
    def buildXml(builder) {
        def sbAttributes = [:]
        if (roomType) {
            sbAttributes.put("RoomType", roomType)
        }
        if (privateBathroom) {
            sbAttributes.put("PrivateBathroom", privateBathroom)
        }else {
            privateBathroom = false
            sbAttributes.put("PrivateBathroom", this.privateBathroom)
        }
        if (maxGuests) {
            sbAttributes.put("MaxGuests", maxGuests)
        }
        if (number) {
            sbAttributes.put("Number", number)
        }
        builder."SubRoom"(sbAttributes)
        if (amenities) {
            builder."Amenities" {
                amenities.each { am ->
                    am.buildXmlPerRoom(builder)
                }
            }
        }

    }
}
