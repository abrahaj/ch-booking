package al.rb.booking
/**
 * (ACC) Attraction Category Codes (OTA 2014B)
 */
enum AttractionCategoryCode {
    MARKET(29, "market"),
    RESTAURANT(41, "restaurant"),
    MOUNTAIN(31, "mountain"),
    LAKE(25, "lake"),
    RIVER(42, "river"),
    BEACH(5, "beach"),
    SEAOCEAN(33, "seaocean"),
    SKILIFIT(45, "skilift"),
    GENERALSUPPLIES(47, "generalsupplies"),
    CAFEBAR(73, "cafebar"),

    private final int code
    private final String name

    private AttractionCategoryCode(code, String name) {
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
