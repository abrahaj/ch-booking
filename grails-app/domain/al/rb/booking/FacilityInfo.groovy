package al.rb.booking
/**
 * Details of property facilities.
 */
class FacilityInfo {
//    ArrayList<GuestRoom> guestRooms
//    ArrayList<Restaurant> restaurants
    static hasMany = [guestRooms: GuestRoom, restaurants: Restaurant]
    TPAExtension tpaExtension
    static constraints = {
        guestRooms nullable: true
        restaurants nullable: true
        tpaExtension nullable: true
    }

    def buildXml(builder) {
        builder."FacilityInfo" {
            if (guestRooms) {
                builder."GuestRooms" {
                    guestRooms.each { gr ->
                        gr.buildXml builder
                    }
                }
            }
            if (restaurants) {
                builder."Restaurants" {
                    restaurants.each { gr ->
                        gr.buildXml builder
                    }
                }
            }
            if (tpaExtension){
                tpaExtension.buildXml(builder)
            }
        }
    }
}
