package al.rb.booking

class TaxPolicy {
    TaxPolicyCode code
    /**
     * The amount charged, in the country's local currency.
     * Instead of @Amount, you can also use @Percent.
     */
    Price amount
    /**
     * The number of decimal places to apply to @Amount.
     */
    int decimalPlaces

    /**
     * The percentage of the room price that will be added as taxes.
     */
    int percent
    /**
     * Specifies whether the tax is included in the room price or not.
     * Accepts: Inclusive, Exclusive.
     */
    String type
    /**
     * The Charge Type Code that specifies the basis for the charge (e.g. once per stay, every day).
     * Default: 21 (Per person per night)
     */
    ChargeTypeCode chargeFrequency
    /**
     * The Booking.com room type ID that this fee applies to.
     * A fee can only apply to one room type at a time. To apply the same fee to multiple rooms, duplicate the entire TaxPolicy element. To apply a fee to all rooms in the property, remove @InvCode.
     * Get the room type ID from OTA_HotelInvNotifRS[@ResponseInvCode] on creation of room type.
     */
    String invCode
    static constraints = {
        code nullable: false
        amount nullable: true
        decimalPlaces nullable: true
        percent nullable: true
        type nullable: true, inList: ['Inclusive', 'Exclusive']
        chargeFrequency nullable: true
        invCode nullable: true
    }
}
