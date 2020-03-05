package al.rb.booking

class Amenity {
    RoomAmenityCode roomAmenityCode
    int quantity
    static constraints = {
        roomAmenityCode nullable: false
        quantity nullable: true
    }
}
