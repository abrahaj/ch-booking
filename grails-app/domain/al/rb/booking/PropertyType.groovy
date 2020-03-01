package al.rb.booking
/**
 * (PCT) Property Class Type Codes (OTA 2014B) — Implemented¶
 * https://connect.booking.com/user_guide/site/en-US/codes-pct/
 */
enum PropertyType {

    APARTMENT(3, "Apartment", "Apartment"),
    BED_AND_BREAKFAST(4, "Bed and breakfast", "Apartment"),
    CAMPGROUND(6, "Campground", "Camping"),
    CHALET(7, "Chalet", "Chalet"),
    CRUISE(12, "Cruise", "Boat"),
    BOATEL(44, "Boatel", "Boat"),
    GUEST_FARM(15, "Guest farm", "Farm stay"),
    PENSION(40, "Pension", "Guest House"),
    RESORT(30, "Resort", "Resort"),
    HOSTEL(19, "Hostel", "Hostel"),
    HOTEL(20, "Hotel", "Hotel"),
    INN(21, "Inn", "Inn"),
    LODGE(22, "Lodge", "Lodge"),
    MOTEL(27, "Motel", "Motel"),
    TENT(33, "Tent", "Tented"),
    VILLA(35, "Villa", "Villa"),
    APARTHOTEL(5000, "", "ApartHotel"),
    RIAD(5001, "", "Riad"),
    RYOKAN(5002, "", "Ryokan"),
    LOVE_HOTEL(5003, "", "Love Hotel"),
    HOMESTAY(5004, "", "Homestay"),
    JAPANESE_STYLE_BUSINESS_HOTEL(5005, "", "Japanese-style Business Hotel"),
    HOLIDAY_HOME(5006, "", "Holiday Home"),
    COUNTRY_HOUSE(5007, "", "Country house"),
    CAPSULE_HOTEL(5008, "", "Capsule Hotel"),
    HOLIDAY_PARK(5009, "", "Holiday Park")

    private final int code
    private final String ota
    private final String bookingName

    private PropertyType(code, ota, String bookingName) {
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
