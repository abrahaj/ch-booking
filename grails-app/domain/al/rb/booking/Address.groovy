package al.rb.booking
/**
 * Address details.
 */
class Address {
    /**
     * The Booking.com Language Code for the address details.
     */
    LanguageCode language
    /**
     * The full street name and number.
     * Should not contain abbreviations (such as "Rd." for "Road") and should not exceed 255 characters.
     */
    String addressLine
/**
 * The name of the city, town, or village.
 */
    String cityName
    /**
     * Two letter CountryCode, see LanguageCode.code
     */
    String countryName
    /**
     * The name of the property (in a language other than English).
     * Required if @Language is not en.
     */
    String hotelName
    /**
     * Postal/zip code.
     */
    String postalCode
    /**
     * State/province details.
     */
    String stateProv
    /**
     * Two-letter code for the state or province.
     */
    String stateCode
    static constraints = {
        language nullable: true
        addressLine nullable: false, size: 1..255
        cityName nullable: true
        countryName nullable: true, size: 2..2
        hotelName nullable: true
        postalCode nullable: true
        stateProv nullable: true
        stateCode nullable: true, size: 2..2
    }
    static mapping = {
        language defaultValue: "en"
    }
}
