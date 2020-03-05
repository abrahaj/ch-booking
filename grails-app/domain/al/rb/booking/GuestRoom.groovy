package al.rb.booking
/**
 * Details of a room.
 */
class GuestRoom {
    /**
     * Details of a property-level amenity.
     */
    ArrayList<Amenity> amenities
    static constraints = {
        amenities nullable: true
    }
}
