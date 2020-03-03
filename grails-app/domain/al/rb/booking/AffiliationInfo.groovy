package al.rb.booking
/**
 * Contains star ratings and other awards.
 */
class AffiliationInfo {
    ArrayList<Award> awards
    static constraints = {
        awards nullable: true
    }
}
