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
        phoneNumber nullable: true, matches: "[0-9+]+"
        phoneTechType nullable: true, inList: [1, 3, 5]
    }

    def buildXml(builder) {
        def phoneAttributes = [:]
        if (extension != null) {
            phoneAttributes.put("Extension",extension)
        }
        if (phoneNumber != null) {
            phoneAttributes.put("PhoneNumber", phoneNumber)
        }
        if (phoneTechType != null) {
            phoneAttributes.put("PhoneTechType", phoneTechType)
        }
        builder."Phone"(phoneAttributes)
    }
}
