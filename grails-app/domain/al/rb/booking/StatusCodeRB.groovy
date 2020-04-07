package al.rb.booking

enum StatusCodeRB {

    OK_PROPERTY (4201, "Property is successfully mapped."),
    OK_ROOM (4202, "Room is successfully mapped."),
    OK_RATE (4203, "Rates are successfully synchronized."),
    OK_AVAILABILITY (4204, "Availabilities are successfully synchronized."),
    OK_PRODUCT (4205, "Hotel Product is successfully mapped."),
    //NO_AVAILABILITY(4205, "No availabilities on the requested date"),
    FAILED_PROPERTY(4210, "FAILED mapping a property."),
    FAILED_ROOM(4211, "FAILED mapping a room."),
    FAILED_RATE(4212, "FAILED sync rates."),
    FAILED_AVAILABILITY(4213, "FAILED sync availability."),
    FAILED_PRODUCT(4214, "FAILED creating a product.")

    private final int code
    private final String description

    private StatusCodeRB(int code, String description) {
        this.code = code
        this.description = description
    }

    String getDescription() {
        return description
    }

    int getCode() {
        return code
    }

    @Override
    String toString() {
        return code + ": " + description
    }
}
