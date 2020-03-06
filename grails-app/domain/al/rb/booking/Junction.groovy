package al.rb.booking
/**
 * Motorway/highway junction/exit to take.
 */
class Junction {
    /**
     * The name of the junction/exit to take, if travelling by car.
     */
    String name
    static constraints = {
        name nullable: false
    }
}
