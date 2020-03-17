package al.rb.booking
/**
 * Cancellation and booking policies for the product.
 */
class Policyinfo {
    /**
     * Policies around cancellations and no-shows.
     */
    CancelPolicy cancelPolicy
    /**
     * he Booking.com Check-in/Check-Out Time Code[codes-bcio] for the time by which a guest should check out at the latest.
     * Format: HH:MM (from)
     * The format HH:MM-HH:MM (from-to) is not supported. Only "from" is required; "to" is optional. 24-hour check-in can be specified using 00:00-00:00.
     *
     */
    String checkInTime, checkoutTime
    /**
     * The total number of guests that can stay at the property at a given time.
     */
    int totalGuestCount
    /**
     * Specifies whether the property admits adults and children, or only adults.
     */
    String acceptedGuestType

    static hasMany = [bookingRules: BookingRule]
    static constraints = {
        checkInTime nullable: false, inList: ["00:00", "00:30", "01:00", "01:30", "02:00", "02:30", "03:00", "03:30", "04:00", "04:30", "05:00", "05:30", "06:00",
                                              "06:30", "07:00", "07:30", "08:00", "08:30", "09:00", "09:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00", "16:30", "17:00", "17:30", "18:00", "18:30", "19:00", "19:30", "20:00", "20:30", "21:00", "21:30", "22:00", "22:30", "23:00", "23:30", "00:00"]
        checkoutTime nullable: true, inList: ["00:00", "00:30", "01:00", "01:30", "02:00", "02:30", "03:00", "03:30", "04:00", "04:30", "05:00", "05:30",
                                              "06:00",
                                              "06:30", "07:00", "07:30", "08:00", "08:30", "09:00", "09:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00", "16:30", "17:00", "17:30", "18:00", "18:30", "19:00", "19:30", "20:00", "20:30", "21:00", "21:30", "22:00", "22:30", "23:00", "23:30", "00:00"]
        totalGuestCount nullable: true
        acceptedGuestType nullable: true, inList: ['AdultOnly', 'empty']
        bookingRules nullable: true
        cancelPolicy nullable: true

    }

    def buildXml(builder){
            if (bookingRules.size()>0) {
                builder."BookingRules" {
                    bookingRules.each { bR ->
                        bR.buildXml(builder)
                    }
                }
            }
            if (cancelPolicy) {
                cancelPolicy.buildXml(builder)
            }

    }
}
