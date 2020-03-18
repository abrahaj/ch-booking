package al.rb.booking
/**
 * Residence type details.
 */
class TypeOfResidence {
    /**
     * Type of residence.
     */
    String type
    static constraints = {
        type nullable: true,inList: [ "primary", "secondary", "other"]
    }
    def buildXml(builder){
        if (type==null){
            type = "primary"
        }
        builder."TypeOfResidence" ("Type": this.type)
    }
}
