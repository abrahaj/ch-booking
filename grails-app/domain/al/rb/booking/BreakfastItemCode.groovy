package al.rb.booking
/**
 * (BCBI) Booking.com Breakfast Item Codes
 * https://connect.booking.com/user_guide/site/en-US/codes-bcbi/
 */
enum BreakfastItemCode {

    BREAD(5001, "Bread")
/**...*/

    private final int code
    private final String bookingName

    private BreakfastItemCode(code,  String bookingName) {
        this.code = code
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
