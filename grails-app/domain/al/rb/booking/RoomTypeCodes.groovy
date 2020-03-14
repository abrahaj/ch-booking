package al.rb.booking
/**
 * (BCRT) Booking.com Room Type Codes
 * https://connect.booking.com/user_guide/site/en-US/codes-bcrt/
 */
enum RoomTypeCodes {
    APARTMENT(1, "Apartment", "Separate unit with own bathroom and kitchen/kitchenette."),
    QUADRUPLE(4, "Quadruple", "Unit for 4 persons"),
    SUITE(5,"Suite","Only \"Suite\" units. \"Suite\" have to be mentioned in the Unit name.")

    private final int code
    private final String roomTypeDescription
    private final String notes

    private RoomTypeCodes(code, roomTypeDescription, notes){
        this.code = code
        this.roomTypeDescription = roomTypeDescription
        this.notes = notes
    }

    String getRoomTypeDescription() {
        return roomTypeDescription
    }

    int getCode() {
        return code
    }

    @Override
    String toString() {
        return code + ": " + roomTypeDescription
    }
}
