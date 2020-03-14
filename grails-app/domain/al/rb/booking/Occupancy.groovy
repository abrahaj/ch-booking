package al.rb.booking
/**
 * Room occupancy details.
 */
class Occupancy {
    /**
     * The maximum number of guests allowed to stay in the room (not including children).
     * You can separately specify extra bed options at property level.
     */
    int maxOccupancy

    static constraints = {
        maxOccupancy nullable: true
    }

    def buildXml(builder){
        builder."Occupancy"("MaxOccupancy":maxOccupancy)
    }
}
