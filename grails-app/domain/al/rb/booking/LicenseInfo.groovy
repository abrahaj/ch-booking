package al.rb.booking
/**
 * Property licence details.
 */
class LicenseInfo {
    /**
     * The property's 13-digit licence number.
     */
    String propertyLicenseNumber
    /**
     * Property management details.
     */
    PropertyManagedBy propertyManagedBy
    /**
     * Residence type details.
     */
    TypeOfResidence typeOfResidence
    static constraints = {
        propertyLicenseNumber nullable: true
        propertyManagedBy nullable: true
        typeOfResidence nullable: true
    }
    def buildXml(builder){
        builder."PropertyManagedBy" ("PropertyLicenseNumber": propertyLicenseNumber)
        if(propertyManagedBy) {
            propertyManagedBy.buildXml(builder)
        }
        if(typeOfResidence){
            typeOfResidence.buildXml(builder)
        }
    }
}
