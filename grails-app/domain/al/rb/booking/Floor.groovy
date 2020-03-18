package al.rb.booking
/**
 * Floor in the building where room is located.
 * The floor added should be consistent to the RoomLocationCode in the RoomLocation.
 */
class Floor {
    /**
     * The number of the floor starting from 0(ground floor) in the building.
     * nonnegative, Max value should be less than TotalNumberOfFloors
     */
    int number
    static constraints = {
        number nullable : true, min: 0
    }
    def buildXml(builder) {
        def fAttributes = [:]
        if (number) {
            fAttributes.put("Number", number)
        }
        builder."Floor"(fAttributes)
    }
}
