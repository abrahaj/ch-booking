package al.rb.booking

class CancellationGracePeriod {
    /**
     * Grace period - amount of hours after booking when free cancellation is available.
     * Accepts: 0, 1, 4, 24.
     */
    int hoursAfterBooking
    /**
     * Advance cancellation - amount of weeks before check-in when free cancellation is available.
     * Accepts: 0, 4, 8, 12
     */
    int weeksBeforeCheckIn
    static constraints = {
        hoursAfterBooking nullable: true, range: 1..24
        weeksBeforeCheckIn nullable: true, range: 0..12
    }
}
