package al.rb.booking

class PetsPolicy {
    /**
     * Specifies the property's policy regarding pets.
     * Values: Pets Allowed, Pets Not Allowed, Pets By Arrangements.
     */
    String petsAllowedCode
    /**
     * Pets policy details.
     */
    PetsPolicy petsPolicy
    /**
     * The non-refundable fee the property charges for having a pet stay in the room.
     * free,charges_may_apply
     */
    String nonRefundableFee
    static constraints = {
        petsAllowedCode nullable: true, inList: ['Pets Allowed', 'Pets Not Allowed', 'Pets By Arrangements']
        petsPolicy nullable: true
        nonRefundableFee nullable: true, inList: ['free', 'charges_may_apply']
    }
}
