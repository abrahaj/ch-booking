package al.rb.booking
/**
 * (HAC) Hotel Amenity Codes (OTA 2014B)¶
 * https://connect.booking.com/user_guide/site/en-US/codes-hac/
 */
enum HotelAmenityCode {

    HOURS_24(1, "24-hour front desk", "24-hour front desk")


    private final int code
    private final String ota
    private final String bookingName

    private HotelAmenityCode(code, ota, String bookingName) {
        this.code = code
        this.ota = ota
        this.bookingName = bookingName
    }

    String getPropertyType() {
        return bookingName
    }

    int getCode() {
        return code
    }

    @Override
    String toString() {
        return code + ": " + bookingName
    }
}
