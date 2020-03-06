package al.rb.booking

class PricingType {
    /**
     * Accepts: Standard, LOS, OBP. Default: Standard. For length of stay pricing (LOS) and Occupancy Based pricing (OBP) provider must be certified for these pricing types. Please check certification process with Booking.com support team.
     */
    String pricingTypeValue
    static constraints = {
        pricingTypeValue nullable: true, inList: ['Standard', 'LOS', 'OBP']
    }
}
