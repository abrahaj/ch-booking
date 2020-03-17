package al.rb.booking
/**
 * Room types in the product.
 */
class RoomType {
    /**
     * The Booking.com room type ID.
     */
    String roomTypeCode
    /**
     * Specifies the maximum number of guests for all combinations of room type and rate plan in the product.
     */
    int  maxOccupancy
    static constraints = {
        roomTypeCode nullable: true
        maxOccupancy nullable: true
    }

    def buildXml(builder){
        def rTAttributes = [:]
        if (roomTypeCode) {
            rTAttributes.put("RoomTypeCode", roomTypeCode)
        }
        if (maxOccupancy) {
            rTAttributes.put("MaxOccupancy", maxOccupancy)
        }
        builder."RoomType"(rTAttributes)
    }
}
