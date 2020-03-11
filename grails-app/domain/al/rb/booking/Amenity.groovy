package al.rb.booking

class Amenity {
    RoomAmenityCode roomAmenityCode
    int quantity
    static constraints = {
        roomAmenityCode nullable: false
        quantity nullable: true
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
}
