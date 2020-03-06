package al.rb.booking
/**
 * https://connect.booking.com/user_guide/site/en-US/api-reference/PropertyTaxInfo/
 * As per new French legislation which will be effective 1st January 2019, Booking.com is required to collect and remit tourist tax for its non professional partners in France. This will only be the case for the reservations of which Booking.com is facilitating the payment between the booker and the French partner. In order for us to understand whether we are required to do this for you, please answer the questions below. Your answers to the 3 questions will help us determine whether you are professional or not. Only in case you have answered "no" to the 3 questions, we will also ask you to confirm your category. More information about the tax legislation can be found here.
 * //TODO France only
 */
class PropertyTaxInfo {
    short propertyRegisteredInVcs
    short propertyHasVat
    short propertyDeclaresRevenue
    int propertyTaxCategory
    int propertyNatureCategory
    static constraints = {
    }
}
