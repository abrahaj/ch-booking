package al.rb.booking
/**
 * Details of a service.
 * https://connect.booking.com/user_guide/site/en-US/api-reference/Service/
 */
class Service {
    HotelAmenityCode code
    /**
     * Specifies if the service exists, i.e. it sets if the facility is present or missing.
     * 1 = yes(present), 2 = no(missing). Default: 1. Required if @Code="218" (children welcome).
     */
    int existCode
    /**
     * Specifies whether the service is included in the room price or comes at an extra charge.
     */
    boolean included
    /**
     * The price for the service, in the property's default currency.
     * Required if @Code is 173 (breakfast), 6000 (lunch), 6001 (dinner).
     */
    int price
    /**
     * Details about service features.
     */
    ArrayList<Feature> features

    /**
     * The types of service offered.
     * Currently used for breakfast services only.
     */
    ArrayList<Type> types
    /**
     * The items for the service offered.
     * Currently used for breakfast services only.
     */
    ArrayList<Item> items

    ArrayList<OperationTime> operationTimes

    static constraints = {
        code nullable: false
        existCode nullable: true, inList: [1, 2]
        price nullable: true
        features nullable: true
        types nullable: true
        items nullable: true
    }

/**
 *  <Service Code="5" Included="false"/>
 *  *           <Service Code="173" Price="14" CurrencyCode="EUR">
 *  *             <Types>
 *  *               <Type Code="5001"/>
 *  *               <Type Code="5004"/>
 *  *               <Type Code="5009"/>
 *  *             </Types>
 *  *             <Items>
 *  *               <Item Code="5005"/>
 *  *               <Item Code="5006"/>
 *  *               <Item Code="5007"/>
 *  *               <Item Code="5009"/>
 *  *               <Item Code="5011"/>
 *  *             </Items>
 *  *             <OperationTimes>
 *  *               <OperationTime Mon='1' Tue='1' Weds='1' Thur='1' Fri='1' Sat='1' Sun='1' Start="08:00" End="10:00"/>
 *  *             </OperationTimes>
 *  *           </Service>
 *  *           <Service Code="218" ExistsCode="1" Quantity="2">
 *  *             <Features>
 *  *               <Feature>
 *  *                 <DescriptiveText>max_age_12</DescriptiveText>
 *  *                 <Charge Amount="12300" DecimalPlaces="2"/>
 *  *               </Feature>
 *  *             </Features>
 *  *           </Service>
 *  *           <Service Code="5038" Quantity="4">
 *  *             <Features>
 *  *               <Feature ID="adult_bed">
 *  *                 <Charge Amount="123"/>
 *  *               </Feature>
 *  *               <Feature ID="infant_bed">
 *  *                 <Charge Amount="321"/>
 *  *               </Feature>
 *  *               <Feature ID="children_bed">
 *  *                 <DescriptiveText>max_age_6</DescriptiveText>
 *  *                 <Charge Amount="246"/>
 *  *               </Feature>
 *  *             </Features>
 *  *           </Service>
 */

}
