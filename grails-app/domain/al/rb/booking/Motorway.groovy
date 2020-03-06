package al.rb.booking

class Motorway {
    /**
     * The name of the motorway to take, if travelling by car.
     * Only allowed when TransportType[@Code="5"] (car).
     */
    String name
    static constraints = {
        name nullable: true
    }
}
