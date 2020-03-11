package al.rb.booking
/**
 * Contains star ratings and other awards.
 */
class AffiliationInfo {
//    ArrayList<Award> awards
    static hasMany = [awards: Award]
    static constraints = {
        awards nullable: true
    }

    def buildXml(builder) {
        builder."AffiliateInfo" {
            if (awards) {
                awards.each { aw ->
                    aw.buildXml builder
                }
            }
        }
    }
}
