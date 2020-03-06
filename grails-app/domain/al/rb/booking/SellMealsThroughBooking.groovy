package al.rb.booking
/**
 * Controls whether to sell meals as addons through booking or not.
 */
class SellMealsThroughBooking {
    /**
     * Enable/disable selling meal plans as addons through booking or not.
     * 1 will enable selling meals as addons through booking, 0 will disable that.
     */
    short enabled
    static constraints = {
        enabled nullable: false, inList: [0, 1]
    }
}
