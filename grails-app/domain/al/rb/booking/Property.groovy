package al.rb.booking

import groovy.xml.MarkupBuilder

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class Property {
    /**
     * Name of the property as it should be displayed on Booking.com
     * https://partner.booking.com/en-gb/help/property-page/how-rename-your-property-listing-title
     */
    String name
    /**
     * Your custom property ID, as assigned when you created the property.
     */
    String bookingId
    /**
     * The property licence number, as provided by the local authority.
     */
    String propertyLicenseNumber
    /**
     * The property licence issue date, for properties in Japan.
     * Format: YYYY-MM-DD
     */
    String propertyLicenseIssueDate
    /**
     * The property licence type, for properties in Japan.
     */
    String propertyLicenseType
    /**
     * @HotelCode The Booking.com property ID, returned on creation of the property.
     */
    int code

    /**
     * Accepts these values: New, Overlay. Default: New
     * Specifies whether the request is meant to create a new property or overlay an existing one.
     */
    String hotelDescriptiveContentNotifType
    /**
     * The Booking.Com Language Code for the provided content.
     */
    LanguageCode languageCode

    CurrencyCode currencyCode
    /**
     * Contains star ratings and other awards.
     */
    AffiliationInfo affiliationInfo
    /**
     * Details about attractions and amenities near the property.
     */
    AreaInfo areaInfo

    /**
     * Contact information.
     */
    ArrayList<ContactInfo> contactInfo
    /**
     * Property details and descriptions.
     */
    HotelInfo hotelInfo

    /**
     * Details of property facilities.
     */
    FacilityInfo facilityInfo
    /**
     * Property photos.
     */
    ArrayList<MultimediaDescription> multimediaDescriptions

    /**
     * Property policies around services, taxes, etc.
     */
    ArrayList<Policy> policies
    TPAExtension tpaExtension

    def getXml() {
        def xmlWriter = new StringWriter()
        def xmlMarkup = new MarkupBuilder(xmlWriter)
        def OTA_HotelDescriptiveContentNotifRQ = [xmlns               : "http://www.opentravel.org/OTA/2003/05", "xmlns:xsi": "http://www.w3.org/2001/XMLSchema-instance",
                                                  PrimaryLangID       : "en-us", EchoToken: UUID.randomUUID(), TimeStamp: LocalDateTime.now().format(DateTimeFormatter.ISO_INSTANT),
                                                  "xsi:schemaLocation": "http://www.opentravel.org/2014B/OTA_HotelDescriptiveContentNotifRQ.xsd", id: "OTA2014B", Version: "8.0", Target: "Production"]
        xmlMarkup."OTA_HotelDescriptiveContentNotifRQ" = OTA_HotelDescriptiveContentNotifRQ
    }


    static constraints = {
        name nullable: false
        id nullable: true
        code nullable: true
        languageCode nullable: true
        contactInfo nullable: false
        hotelDescriptiveContentNotifType nullable: true, inList: ['New', 'Overlay']
        currencyCode nullable: false
        propertyLicenseNumber nullable: true
        propertyLicenseIssueDate nullable: false
        propertyLicenseType nullable: false
        affiliationInfo nullable: true
        areaInfo nullable: true
        facilityInfo nullable: true
        hotelInfo nullable: true
        multimediaDescriptions nullable: true

    }
    static mapping = {
        hotelDescriptiveContentNotifType defaultValue: "'New'"
        languageCode defaultValue: "'en'"
    }
    /**
     * <?xml version="1.0" encoding="UTF-8"?>
     * <OTA_HotelDescriptiveContentNotifRQ xmlns="http://www.opentravel.org/OTA/2003/05" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" PrimaryLangID="en-us" EchoToken="GUID" TimeStamp="2015-06-09T09:30:47Z" xsi:schemaLocation="http://www.opentravel.org/2014B/OTA_HotelDescriptiveContentNotifRQ.xsd" id="OTA2014B" Version="8.0" Target="Test">
     *   <HotelDescriptiveContents>
     *     <HotelDescriptiveContent HotelName="Fake Hotel" ID="101010" LanguageCode="en" PropertyLicenseNumber="AB-CD-1234" HotelDescriptiveContentNotifType="New">
     *       <ContactInfos>
     *       ... @See ContactInfo
     *       </ContactInfos>
     *       <HotelInfo>
     *        ... @See HotelInfo
     *       </HotelInfo>
     *       <FacilityInfo>
     *         <GuestRooms>
     *           <GuestRoom>
     *             <Amenities>
     *               <Amenity RoomAmenityCode="91" Quantity="4"/>
     *             </Amenities>
     *           </GuestRoom>
     *         </GuestRooms>
     *         <Restaurants>
     *           <Restaurant RestaurantName='Orient' OfferBreakfast='1' OfferDinner='1'>
     *             <CuisineCodes>
     *               <CuisineCode Code="1"/>
     *               <CuisineCode Code="5000"/>
     *             </CuisineCodes>
     *             <OperationSchedules>
     *               <OperationSchedule>
     *                 <OperationTimes>
     *                   <OperationTime Mon='1' Tue='1' Thur='1' Fri='1' Start='16:00' End='22:00'/>
     *                   <OperationTime Weds='1' Sun='1' Start='14:00' End='18:00'/>
     *                 </OperationTimes>
     *               </OperationSchedule>
     *             </OperationSchedules>
     *             <Features>
     *               <Feature DescriptiveText="a la carte"/>
     *               <Feature DescriptiveText="buffet"/>
     *               <Feature DescriptiveText="guests only"/>
     *               <Feature DescriptiveText="accepts reservations"/>
     *               <Feature DescriptiveText="outdoor seating"/>
     *             </Features>
     *             <TPA_Extensions>
     *               <Ambiances>
     *                 <Ambiance Name="modern"/>
     *               </Ambiances>
     *               <DietaryOptions>
     *                 <DietaryOption Name="halal"/>
     *                 <DietaryOption Name="vegan"/>
     *               </DietaryOptions>
     *             </TPA_Extensions>
     *           </Restaurant>
     *         </Restaurants>
     *         <TPA_Extensions>
     *           <HotelierMessage Language="en">We use jetpacks to go for weekend joyrides with our guests ...
     *          </HotelierMessage>
     *         </TPA_Extensions>
     *       </FacilityInfo>
     *       <AreaInfo>
     *         <TPA_Extensions>
     *           <HotelierMessage Language="en">We float above the heart of the city with easy access to ...
     *          </HotelierMessage>
     *         </TPA_Extensions>
     *         <Attractions>
     *           <Attraction AttractionName="test_market" AttractionCategoryCode="29" Distance="1.2" DistanceUnit="miles" LanguageCode="en"/>
     *           <Attraction AttractionName="test_restaurant" AttractionCategoryCode="41" Distance="3"/>
     *         </Attractions>
     *       </AreaInfo>
     *       <Policies>
     *         <Policy>
     *           <PolicyInfo CheckInTime="17:00-20:00" CheckOutTime="12:00"/>
     *           <PetsPolicies PetsAllowedCode="Pets By Arrangements">
     *             <PetsPolicy NonRefundableFee="charges_may_apply"/>
     *           </PetsPolicies>
     *           <CancelPolicy>
     *             <CancelPenalty PolicyCode="1">
     *               <TPA_Extensions>
     *                 <NoShowPolicy Penalty="default"/>
     *               </TPA_Extensions>
     *             </CancelPenalty>
     *             <CancelPenalty PolicyCode="29">
     *               <TPA_Extensions>
     *                 <NoShowPolicy Penalty="total_price"/>
     *               </TPA_Extensions>
     *             </CancelPenalty>
     *           </CancelPolicy>
     *           <GuaranteePaymentPolicy>
     *             <GuaranteePayment PolicyCode="1">
     *               <TPA_Extensions>
     *                 <PrepaymentPolicy EffectiveFrom="after_reservation_is_made"/>
     *               </TPA_Extensions>
     *             </GuaranteePayment>
     *             <GuaranteePayment PolicyCode="29">
     *               <TPA_Extensions>
     *                 <PrepaymentPolicy EffectiveFrom="after_cancellation_fee_begins"/>
     *               </TPA_Extensions>
     *             </GuaranteePayment>
     *           </GuaranteePaymentPolicy>
     *           <TaxPolicies>
     *             <TaxPolicy Code="36" Percent="1200" DecimalPlaces="2" Type="Exclusive"/>
     *             <TaxPolicy Code="3" Amount="800" DecimalPlaces="2" Type="Inclusive" ChargeFrequency="20"/>
     *           </TaxPolicies>
     *           <FeePolicies>
     *             <FeePolicy Code="5009" Amount="1500" DecimalPlaces="2" Type="Conditional" ChargeFrequency="12">
     *               <TPA_Extensions>
     *                 <Conditions>
     *                   <Condition Type="guest_doesnt_clean_before_checkout"/>
     *                 </Conditions>
     *               </TPA_Extensions>
     *             </FeePolicy>
     *             <FeePolicy Code="5035" Amount="10000" DecimalPlaces="2" Type="Exclusive" ChargeFrequency="5001">
     *               <TPA_Extensions>
     *                 <InternetFeePolicy InternetType="wired" InternetCoverage="public_areas"/>
     *               </TPA_Extensions>
     *             </FeePolicy>
     *            <FeePolicy Code="5036" Amount="10000" DecimalPlaces="2" Type="Exclusive" ChargeFrequency="2">
     *               <TPA_Extensions>
     *                 <ParkingFeePolicy ParkingType="location_nearby" ParkingReservation="needed" ParkingProperty="private"/>
     *               </TPA_Extensions>
     *             </FeePolicy>
     *           </FeePolicies>
     *         </Policy>
     *       </Policies>
     *       <AffiliationInfo>
     *         <Awards>
     *           <Award Provider="Star rating" Rating="3"/>
     *         </Awards>
     *       </AffiliationInfo>
     *       <MultimediaDescriptions>
     *         <MultimediaDescription>
     *           <ImageItems>
     *             <ImageItem>
     *               <ImageFormat Main="1">
     *                 <URL>https://pathtoimage/hotelimage001.jpg</URL>
     *               </ImageFormat>
     *               <TPA_Extensions>
     *                 <ImageTags>
     *                   <ImageTag ID="1"/>
     *                 </ImageTags>
     *               </TPA_Extensions>
     *             </ImageItem>
     *             <ImageItem>
     *               <ImageFormat Sort="1">
     *                 <URL>https://pathtoimage/hotelimage002.jpg</URL>
     *               </ImageFormat>
     *             </ImageItem>
     *           </ImageItems>
     *         </MultimediaDescription>
     *       </MultimediaDescriptions>
     *       <TPA_Extensions>
     *         <!-- Standard Phrases -->
     *         <StandardPhrases>
     *           <StandardPhrase Enabled="1" Name="KeyCollection">
     *             <Options>
     *               <Option Name="KeyCollectionAddressLine">15 Station Street</Option>
     *               <Option Name="KeyCollectionCityName">Sotteville</Option>
     *               <Option Name="KeyCollectionPostalCode">76000</Option>
     *             </Options>
     *           </StandardPhrase>
     *           <StandardPhrase Enabled="1" Name="GuestIdentification"/>
     *           <StandardPhrase Enabled="1" Name="InformArrivalTime"/>
     *           <StandardPhrase Enabled="1" Name="PayBeforeStay"/>
     *           <StandardPhrase Enabled="1" Name="TattooRestriction"/>
     *           <StandardPhrase Enabled="1" Name="Renovation">
     *             <Options>
     *               <Option Name="RenovationFrom">2017-01-01</Option>
     *               <Option Name="RenovationUntil">2017-12-31</Option>
     *             </Options>
     *           </StandardPhrase>
     *           <StandardPhrase Enabled="1" Name="SecurityDeposit">
     *             <Options>
     *               <Option Name="SecurityDepositAmount">10.43</Option>
     *               <Option Name="SecurityDepositReturnMethod">cash</Option>
     *               <Option Name="SecurityDepositReturnWhen">on_checkout</Option>
     *               <Option Name="SecurityDepositCollectNumDays">0</Option>
     *               <Option Name="SecurityDepositCollectMethod">cash</Option>
     *               <Option Name="SecurityDepositCollectWhen">upon_arrival</Option>
     *             </Options>
     *           </StandardPhrase>
     *         </StandardPhrases>
     *           <TotalNumberOfFloors Number="10"/>
     *         <PricingType Value="LOS"/>
     *       </TPA_Extensions>
     *     </HotelDescriptiveContent>
     *   </HotelDescriptiveContents>
     * </OTA_HotelDescriptiveContentNotifRQ>
     */
}
