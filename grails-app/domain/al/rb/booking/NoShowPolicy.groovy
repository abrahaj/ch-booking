package al.rb.booking

class NoShowPolicy {
    /**
     * Specifies how much the property charges if the guest doesn't show up on the check-in date.
     */
    String penalty
    static constraints = {
        penalty nullable: false, inList: ['total_price', 'null', 'default ']
    }
}
