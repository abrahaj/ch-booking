package al.rb.booking
/**
 * Property management details.
 */
class PropertyManagedBy {
    /**
     * The type of entity that manages the property.
     */
    String organizationType
    static constraints = {
        organizationType nullable: true, inList: ["private_person", "professional_company"]
    }

    def buildXml(builder){
        if (organizationType==null){
            organizationType = "private_person"
        }
        builder."PropertyManagedBy" ("organizationType": this.organizationType)
    }
}
