package al.rb.booking

class CategoryCode {
    GuestRoomInfo guestRoomInfo
    HotelCategory hotelCategory
    static constraints = {
        guestRoomInfo nullable: false
        hotelCategory nullable: false
    }
}
