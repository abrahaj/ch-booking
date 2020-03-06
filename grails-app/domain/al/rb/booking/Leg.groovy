package al.rb.booking


/**
 * Details of the starting point, destination, and transport method.
 */
class Leg {
    Departure departure
    Junction junction
    Line line
    Motorway motorway
    Start start
    TransportType transportType
    static constraints = {
        departure nullable: true
        junction nullable: true
        line nullable: true
        motorway nullable: true
        start nullable: true
        transportType nullable: true
    }
}
