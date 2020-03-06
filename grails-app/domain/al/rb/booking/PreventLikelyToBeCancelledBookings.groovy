package al.rb.booking

class PreventLikelyToBeCancelledBookings {
    /**
     * Enable/disable the feature for current property.
     * 1 - opt in to the feature. 0 - opt out of the feature.
     */
    short enabled
    static constraints = {
        enabled nullable: false, inList: [0, 1]
    }
}
