package al.rb.booking
/**
 * (BCRT) Booking.com Room Type Codes
 * https://connect.booking.com/user_guide/site/en-US/codes-bcrt/
 */
enum RoomTypeCodes {

    APARTMENT(1, "Apartment", "Separate unit with own bathroom and kitchen/kitchenette."),
    QUADRUPLE(4, "Quadruple", "Unit for 4 persons"),
    SUITE(5,"Suite","Only \"Suite\" units. \"Suite\" have to be mentioned in the Unit name."),
    TRIPLE(7,"Triple","Unit for 3 persons."),
    TWIN(8,"Twin","Unit for 2 persons with 2 beds.."),
    DOUBLE(9,"Double","Unit for 2 persons with Double/Queen/King bed."),
    SINGLE(10,"Single","Unit for 1 person."),
    STUDIO(12,"Studio",	"Unit with kitchenette or kitchen."),
    FAMILY(13,"Family",	"Unit with \"Family\" in the unit name. It must have 2+ occupancy.."),
    TWIN_DOUBLE(24,"Twin/Double",	"Standard Double or Twin Room."),
    DORMITORY_ROOM(25,"Dormitory room",	"Whole dormitory unit that is sold as one unit. \"Dormitory room\" needs to be mentioned in the room name."),
    BED_IN_DORMITORY(26,"Bed in Dormitory",	"Unit for 1 person only. Please specify number of beds in the availability calls."),
    BUNGALOW(27,"Bungalow",	"Any type of bungalow units."),
    CHALET(28,"Chalet",	"Any type of Chalet units."),
    HOLIDAY_HOME(29,"Holiday home",	"House, Town House, Holiday home units."),
    VILLA(31,"Villa",	"Only Villa units. \"Villa\" have to be mentioned in the unit name."),
    MOBILE_HOME(32,"Mobile home",	"Any type of Mobile Home units."),
    TENT(33,"Tent",	"\tAny type of tents.")

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
