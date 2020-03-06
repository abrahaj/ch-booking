package al.rb.booking
/**
 * (ACC) Attraction Category Codes (OTA 2014B)
 */
enum TransportTypeCode {
    SHUTTLE_ARRANGE_BY_PROPERTY(5000, "Shuttle arranged by property"),
    PUBLIC_SHUTTLE(5001, "Public shuttle"),
    BUS(3, "Bus"),
    CAR(5, "car"),
    METRO(10, "metro"),
    SUBWAY(18, "subway"),
    TAXI(20, "Taxi"),
    TRAIN(21, "Train"),
    GENERALSUPPLIES(47, "generalsupplies"),
    TROLLEY_TRAM(22, "Trolley "),
    WALK(25, "Walk "),

    private final int code
    private final String name

    private TransportTypeCode(code, String name) {
        this.code = code
        this.name = name
    }

    String getAttraction() {
        return name
    }

    int getCode() {
        return code
    }

    static constraints = {
    }
}
