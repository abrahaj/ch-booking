package al.rb.booking

class ParkingFeePolicy {
    /**
     * Specifies the type of parking the property offers.
     * Accepts: on_site, location_nearby, none. Default: on_site
     */
    String parkingType

    /**
     * Specifies whether guests can/must reserve a parking space in advance.
     * Accepts: needed, not_needed, not_available. Default: not_available
     */
    String parkingReservation
    /**
     * Specifies whether the parking area is public or private.
     * Accepts: public, private. Default: public
     */
    String parkingProperty
    static constraints = {
        parkingType nullable: true, inList: ['on_site', 'location_nearby', 'none']
        parkingReservation nullable: true, inList: ['needed', 'not_needed', 'not_available']
        parkingProperty nullable: true, inList: ['public', 'private']
    }
    static mapping = {
        parkingType defaultValue: "'on_site'"
        parkingReservation defaultValue: "'not_available'"
        parkingProperty defaultValue: "'public'"
    }
}
