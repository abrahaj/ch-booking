package al.rb.booking

class InternetFeePolicy {
    /**
     * Specifies the type of internet connection.
     * Accepts: wired, wifi, none. Default: wifi
     */
    String type
    /**
     * Specifies the area covered by internet.
     * Accepts: entire_property, public_areas, all_rooms, some_rooms, business_centre. Default: entire_property
     */
    String internetCoverage
    static constraints = {
        type nullable: true, inList: ['wired', 'wifi']
        internetCoverage nullable: true, inList: ['entire_property', 'public_areas', 'all_rooms', 'some_rooms', 'business_centre']
    }
    static mapping = {
        type defaultValue: "wifi"
        internetCoverage defaultValue: "entire_property"
    }
}
