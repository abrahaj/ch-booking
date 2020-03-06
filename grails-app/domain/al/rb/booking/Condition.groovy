package al.rb.booking
/**
 * Cleaning fee condition details.
 */
class Condition {
    /**
     * Specifies when a guest must pay the extra cleaning fee.
     */
    String type

    static constraints = {
        type nullable: false, inList: ['guest_brings_pet', 'guest_doesnt_clean_before_checkout', 'guest_smokes']
    }
}
