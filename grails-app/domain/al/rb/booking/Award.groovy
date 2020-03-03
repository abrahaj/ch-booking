package al.rb.booking
/**
 * Details of an award.
 */
class Award {
    String provider
    int rating
    static constraints = {
        provider nullable: true
        rating nullable: true, range: 1..5
    }
}
