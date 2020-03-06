package al.rb.booking
/**
 * The departure interval in minutes, if the transportation departs multiple times a day.
 */
class Departure {
    /**
     * The departure interval in minutes, if the transportation departs multiple times a day.
     */
    int interval
    /**
     * Specifies whether the transportation is available only by arrangement.
     * 1 = true, 0 false
     */
    short uponArrangement
    static constraints = {
        interval nullable: true
        uponArrangement nullable: true, inList: [0, 1]
    }
}
