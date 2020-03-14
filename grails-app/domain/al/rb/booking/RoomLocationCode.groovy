package al.rb.booking
/**
 * Container for room floor level and all possible floors.
 */
enum RoomLocationCode {
    /**
     * The level of the building at which room is available.
     */
    GROUND_FLOOR(1,"ground floor"),
    UPPER_FLOOR(2,"upper floor"),
    GROUND_FLOOR_AND_UPPER_FLOOR(3,"ground and upper floor"),
    NOT_PROVIDED(0,"not provided")

    private final int code
    private final String name

    private RoomLocationCode(code, String name) {
        this.code = code
        this.name = name
    }

    String getRoomLocation() {
        return name
    }

    int getCode() {
        return code
    }

    @Override
    String toString() {
        return code + ": " + name
    }
    static constraints = {
    }
}
