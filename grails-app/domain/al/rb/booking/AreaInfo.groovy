package al.rb.booking
/**
 * Details about attractions and amenities near the property.
 */
class AreaInfo {
//    ArrayList<Attraction> attractions
    TPAExtension tpa_extensions
    static hasMany = [attractions: Attraction]
    static constraints = {
        attractions nullable: true
        tpa_extensions nullable: true
    }

    def buildXml(builder) {
        builder."AreaInfo" {
            if (attractions) {
                attractions.each { at ->
                    at.buildXml builder
                }
            }
            if (tpa_extensions){
                tpa_extensions.buildXml(builder)
            }
        }
    }
}
