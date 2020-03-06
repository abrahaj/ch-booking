package al.rb.booking

class Route {
    /**
     * Details of the starting point, destination, and transport method.
     */
    Leg leg
    /**
     * Travel time details.
     */
    JourneyTime journeyTime
    /**
     * Travel price details.
     */
    Price price
    static constraints = {
        leg nullable: false
        journeyTime nullable: false
        price nullable: false

    }
}
