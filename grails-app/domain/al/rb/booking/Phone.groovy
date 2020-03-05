package al.rb.booking
/**
 * Phone numbers for a contact person/point.
 */
class Phone {
    /**
     * The extension number that must be dialled in addition to the @PhoneNumber.
     */
    String extension
    /**
     * The international phone number.
     * Format: \+[0-9]+
     */
    String phoneNumber
    /**
     * Phone Technology Type Codes (OTA 2014B)
     * The type of phone line/device.
     */
    PhoneTechType phoneTechType

    static constraints = {
        extension nullable: true
        phoneNumber nullable: true, matches: "[0-9]+"
        phoneTechType nullable: true
    }
}
