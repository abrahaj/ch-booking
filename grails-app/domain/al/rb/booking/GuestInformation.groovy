package al.rb.booking
/**
 * Contains various requirements for the guests.
 */
class GuestInformation {
    /**
     * Specifies whether guests must provide an address.
     * Accepts: 1 (required), 0 (not required).
     */
    short requireGuestAddress
    /**
     * Specifies whether guests must provide a contact telephone number.
     * Accepts: 1 (required), 0 (not required).
     */
    short requireGuestContactNumber
    /**
     * Specifies whether guests must have at least one previous stay on Booking.com.
     * Accepts: 1 (required), 0 (not required).
     */
    short requireMinimumStay
    /**
     * Specifies whether there is an age limit to check-in.
     * Accepts: 1 (required), 0 (not required).
     */
    short hasAgeRestriction
    /**
     * Minimum allowed age for guests to check-in. Only effective when @HasAgeRestriction=1.
     * Only effective when @HasAgeRestriction=1.
     */
    int ageRestrictionMin
    /**
     * Maximum allowed age for guests to check-in. Only effective when @HasAgeRestriction=1.
     * Only effective when @HasAgeRestriction=1.
     */
    int ageRestrictionMax
    /**
     * Specifies whether property has a curfew - times at which guests cannot enter/leave the property.
     * Accepts: 1 (has curfew), 0 (does not have curfew).
     */
    short hasCurfew
    /**
     * Curfew start time in HH:MM format.
     * Only effective when @HasCurfew=1.
     */
    String curfewStart
    /**
     * Curfew end time in HH:MM format.
     * Only effective when @HasCurfew=1.
     */
    String curfewEnd
    static constraints = {
        requireGuestAddress nullable: true, inList: [0, 1]
        requireGuestContactNumber nullable: true, inList: [0, 1]
        requireMinimumStay nullable: true, inList: [0, 1]
        hasAgeRestriction nullable: true, inList: [0, 1]
        ageRestrictionMin nullable: true, range: 18..99
        ageRestrictionMax nullable: true, range: 18..99
        hasCurfew nullable: true, inList: [0, 1]
        curfewStart nullable: true, inList: ["00:00", "00:30", "01:00", "01:30", "02:00", "02:30", "03:00", "03:30", "04:00", "04:30", "05:00", "05:30", "06:00", "06:30", "07:00", "07:30", "08:00", "08:30", "09:00", "09:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00", "16:30", "17:00", "17:30", "18:00", "18:30", "19:00", "19:30", "20:00", "20:30", "21:00", "21:30", "22:00", "22:30", "23:00", "23:30", "00:00"]
        curfewEnd nullable: true, inList: ["00:00", "00:30", "01:00", "01:30", "02:00", "02:30", "03:00", "03:30", "04:00", "04:30", "05:00", "05:30", "06:00", "06:30", "07:00", "07:30", "08:00", "08:30", "09:00", "09:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00", "16:30", "17:00", "17:30", "18:00", "18:30", "19:00", "19:30", "20:00", "20:30", "21:00", "21:30", "22:00", "22:30", "23:00", "23:30", "00:00"]

    }
}
