package al.rb.booking
/**
 * Allows to set the total number of floors in the building excluding underground floors.
 */
class TotalNumberOfFloors {
    /**
     * Total number of floors the building has (excl. underground floors).
     * Max value can be 200.
     */
    int floorNumber
    static constraints = {
        floorNumber nullable: true, range: 0..200
    }
}
