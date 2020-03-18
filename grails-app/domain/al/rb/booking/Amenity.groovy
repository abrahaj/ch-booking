package al.rb.booking
/**
 * Details of a property-level amenity.
 */
class Amenity {
    /**
     * The Room Amenity Type Code.
     * Details of a property-level amenity.
     */
    RoomAmenityCode roomAmenityCode
    /**
     * The available number of amenities of this type.
     * Details of a property-level amenity.
     */
    int quantity
    /**
     * The Room Amenity Type Code.
     * Details of a room-level amenity.
     */
    RoomAmenityCode amenityCode
    /**
     * Specifies the quantity for OTA bed types (bed type amenities).
     * Details of a room-level amenity.
     */
    int value
    /**
     * Specifies whether the amenity is available in the standard or alternative room arrangement.
     * Accepted only when RMA code is a bed type. Accepts: 1 (standard arrangement), 2 (alternative arrangement)
     * Details of a room-level amenity.
     */
    ConfigurationCode configuration


    static constraints = {
        roomAmenityCode nullable: true
        quantity nullable: true
        amenityCode nullable: true
        value nullable:true
        configuration nullable : true
    }

    def buildXml(builder) {
        def aAttributes = [:]
        if (roomAmenityCode) {
            aAttributes.put("RoomAmenityCode", roomAmenityCode.getCode())
        }
        if (quantity) {
            aAttributes.put("Quantity", quantity)
        }
        builder."Amenity"(aAttributes)
    }
    def buildXmlPerRoom(builder) {
        def aAttributes = [:]
        if (amenityCode) {
            aAttributes.put("AmenityCode", amenityCode.getCode())
        }
        if (configuration) {
            aAttributes.put("Configuration", configuration.getCode())
        }
        if (value) {
            aAttributes.put("Value", value)
        }
        builder."Amenity"(aAttributes)
    }
}
