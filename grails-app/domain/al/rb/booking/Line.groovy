package al.rb.booking
/**
 * Public transport line details.
 */
class Line {
    /**
     * The name or number of the line to take, if travelling by shuttle or public transport.
     * Only allowed when TransportType[@Code] is 3, 10, 18, 22, or 5001.
     */
    String name
    static constraints = {
        name nullable: true
    }
}
