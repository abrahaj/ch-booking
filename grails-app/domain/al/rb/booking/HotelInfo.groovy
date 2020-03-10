package al.rb.booking

class HotelInfo {
    CategoryCode categoryCode
    GeoPosition position
    ArrayList<Language> languages
    ArrayList<Service> services
    /**
     * Information about the property's relationship to Booking.com.
     */
    ArrayList<OwnershipManagementInfo> ownershipManagementInfos

    ArrayList<RelativePosition> relativePositions
///**
// *   HotelName="The Best Hotel"
// *       LanguageCode="en"
// *       HotelDescriptiveContentNotifType="New"
// * @param builder
// * @return
// */
//    def buildXml(builder){
//        title lang:'en', 'Groovy in Action'
//            builder.address {
//                street streetName
//                houseNumber number
//                buildCity builder, city
//           }
//    }

/**
 *  <CategoryCodes>
 *            <GuestRoomInfo Quantity="100"/>
 *          <HotelCategory Code="20" ExistsCode="1"/>
 *           </CategoryCodes>
 *  *         <Languages>
 *             ... @See BookingLanguage
 *  *         </Languages>
 *  *         <Position Latitude="49.4092" Longitude="1.0900"/>
 *  *         <Services>
 *  *          ... @See Service
 *  *         </Services>
 *  *         <OwnershipManagementInfos>
 *  *           <OwnershipManagementInfo>
 *  *             <CompanyName Code="1234"/>
 *  *           </OwnershipManagementInfo>
 *  *         </OwnershipManagementInfos>
 *  *         <RelativePositions>
 *  *           <RelativePosition>
 *  *             <Transportations>
 *  *               <Transportation>
 *  *                 <TPA_Extensions>
 *  *                   <Route>
 *  *                     <Leg>
 *  *                       <Start Type='airport' Code='AMS'/>
 *  *                       <TransportType Code="5000"/>
 *  *                       <Departure UponArrangement="1"/>
 *  *                     </Leg>
 *  *                     <JourneyTime Minutes="20"/>
 *  *                     <Price Amount="10" CurrencyCode="EUR"/>
 *  *                   </Route>
 *  *                 </TPA_Extensions>
 *  *               </Transportation>
 *  *               <Transportation>
 *  *                 <TPA_Extensions>
 *  *                   <Route>
 *  *                     <Leg>
 *  *                       <Start Type='airport' Code='AMS'/>
 *  *                       <TransportType Code="3"/>
 *  *                       <Line Name="10"/>
 *  *                       <Departure Interval="20"/>
 *  *                     </Leg>
 *  *                     <JourneyTime Minutes="20"/>
 *  *                     <Price Amount="5.5" CurrencyCode="EUR"/>
 *  *                   </Route>
 *  *                 </TPA_Extensions>
 *  *               </Transportation>
 *  *               <Transportation>
 *  *                 <TPA_Extensions>
 *  *                   <Route>
 *  *                     <Leg>
 *  *                       <Start Type='airport' Code='AMS'/>
 *  *                       <TransportType Code="5"/>
 *  *                       <Motorway Name="A2"/>
 *  *                       <Junction Name="Amsterdam Zuid"/>
 *  *                     </Leg>
 *  *                     <JourneyTime Minutes="30"/>
 *  *                   </Route>
 *  *                 </TPA_Extensions>
 *  *               </Transportation>
 *  *             </Transportations>
 *  *           </RelativePosition>
 *  *         </RelativePositions>
 *  *         <TPA_Extensions>
 *  *           <AcceptedPayments>
 *  *             <AcceptedPayment PaymentTypeCode="1"/>
 *  *             <AcceptedPayment PaymentTypeCode="2"/>
 *  *             <AcceptedPayment PaymentTypeCode="3"/>
 *  *           </AcceptedPayments>
 *  *           <HotelierMessage Language="en">Our hotel defies gravity and floats in mid-air ...
 *  *          </HotelierMessage>
 *  *         </TPA_Extensions>
 */
    static constraints = {
        categoryCode nullable: true
        languages nullable: true
        position nullable: true
        services nullable: true
        ownershipManagementInfos nullable: false
        relativePositions nullable: true
    }
}
