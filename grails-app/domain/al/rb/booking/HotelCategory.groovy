package al.rb.booking
/**
 * HotelCategory - Property type details.
 * https://connect.booking.com/user_guide/site/en-US/api-reference/HotelCategory/
 */
class HotelCategory {
    PropertyType propertyType
    int existsCode
    static constraints = {
        propertyType nullable: false
        existsCode nullable: true, range: 0..1
    }
}
