package al.rb.booking

class PrepaymentPolicy {
    String effectiveFrom

    static constraints = {
        effectiveFrom nullable: false, inList: ['after_reservation_is_made', 'after_cancellation_fee_begins']
    }
    static mapping = {
        effectiveFrom defaultValue: "'after_reservation_is_made'"
    }
}
