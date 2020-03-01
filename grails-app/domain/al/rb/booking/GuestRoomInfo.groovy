package al.rb.booking
/**
 * Room details.
 * int quantity A "sellable unit" is the smallest possible space that a guest can book at the property. In a hotel with 200 rooms, each room is a sellable
 * unit, and the value would be 200. In a holiday home, guests must typically book the home in its entirety, making the value 1.
 */
class GuestRoomInfo {
    int quantity
    static constraints = {
    }
}
