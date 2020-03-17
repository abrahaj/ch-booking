package al.rb.booking
/**
 * Rules for how long in advance the room can be booked.
 */
class BookingRule {
    /**
     * Minimum amount of time in advance the room must be booked, relative to midnight (24:00 CE(S)T) on the day of check-in.
     * Example: P5H means the room must be booked before 19:00 on the check-in date.
     * P[0-9]+(Y|M|D|H)
     */
    String minAdvancedBookingOffset
    /**
     * Maximum amount of time in advance the room can be booked, relative to midnight (24:00 CE(S)T) on the day of check-in.
     * Example: P14D means the room can be booked a maximum of 14 days before the check-in date.
     * P[0-9]+(Y|M|D|H)
     */
    String maxAdvancedBookingOffset
    static constraints = {
        minAdvancedBookingOffset nullable: true
        maxAdvancedBookingOffset nullable: true
    }

    def buildXml(builder){
        if(minAdvancedBookingOffset){
            builder."BookingRule"("MinAdvancedBookingOffset": minAdvancedBookingOffset)
        }
        if (maxAdvancedBookingOffset){
            builder."BookingRule"("MaxAdvancedBookingOffset": maxAdvancedBookingOffset)
        }
    }
}
