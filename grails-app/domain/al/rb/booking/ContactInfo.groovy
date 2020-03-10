package al.rb.booking
/**
 * Details of a single contact person/point.
 */
class ContactInfo {

    /**
     * ContactInfo[@ContactProfileType] accepts the following values. Each value corresponds with a similarly named heading on the Contacts page in our extranet.
     *
     * general 	Primary point of contact for the property. 	Required
     * contract 	Contact for contract matters. 	Optional
     * reservations 	Contact for reservations. 	Optional
     * invoices 	Contact for accounts payable. 	Required
     * availability 	Contact for questions about availability. 	Optional
     * site_content 	Contact for photos, descriptions, and other website content. 	Optional
     * parity 	Contact for pricing and rate matters. 	Optional
     * requests 	Contact for special requests. 	Optional
     * central_reservations 	Contact for central reservations. Applies to properties that manage reservations from another location. 	Optional
     * PhysicalLocation 	Address details for the property's physical location. 	Required
     */
    String contactProfileType
    /**
     * Contact addresses.
     */
    ArrayList<Address> addresses
    /**
     * Email addresses.
     */
    ArrayList<String> emails
    /**
     * Contact person names.
     */
    ArrayList<Name> names
    /**
     * Phone numbers.
     */
    ArrayList<Phone> phones

    static hasMany = [addresses: Address, emails: String, names: Name, phones: Phone]

    static constraints = {
        contactProfileType nullable: false, inList: ['general', 'contract', 'reservations', 'invoices', 'availability', 'site_content', 'parity', 'requests',
                                                     'central_reservations', 'PhysicalLocation']
        addresses nullable: true
        emails nullable: true
        names nullable: true
        phones nullable: true
    }

    def buildContactInfo(builder) {
        builder."ContactInfo" "ContactProfileType": contactProfileType
        log.info("Called")
    }
/**
 *   <ContactInfo ContactProfileType="PhysicalLocation">
 *  *           <Addresses>
 *  *             <Address>
 *  *               <AddressLine>15 Station Street</AddressLine>
 *  *               <CityName>Sotteville</CityName>
 *  *               <PostalCode>50340</PostalCode>
 *  *               <CountryName>FR</CountryName>
 *  *             </Address>
 *  *             <Address Language="fr">
 *  *               <HotelName>Le Faux Hôtel 001</HotelName>
 *  *               <CityName>Sotteville-lès-Rouen</CityName>
 *  *               <CountryName>FR</CountryName>
 *  *               <PostalCode>76300</PostalCode>
 *  *               <AddressLine>15 Rue de la Gare</AddressLine>
 *  *             </Address>
 *  *           </Addresses>
 *  *         </ContactInfo>
 *  *         <ContactInfo ContactProfileType="general">
 *  *           <Names>
 *  *             <Name Gender="Female" Language="en">
 *  *               <GivenName>Carol</GivenName>
 *  *               <Surname>Xu</Surname>
 *  *               <JobTitle>Owner</JobTitle>
 *  *             </Name>
 *  *           </Names>
 *  *           <Addresses>
 *  *             <Address>
 *  *               <AddressLine>1000 Eighth Avenue</AddressLine>
 *  *               <CityName>New York</CityName>
 *  *               <PostalCode>10000</PostalCode>
 *  *               <StateProv StateCode="NY"/>
 *  *               <CountryName>US</CountryName>
 *  *             </Address>
 *  *           </Addresses>
 *  *           <Emails>
 *  *             <Email>carol.xu@fakehotel.com</Email>
 *  *           </Emails>
 *  *           <Phones>
 *  *             <Phone Extension="30" PhoneNumber="+31622222220" PhoneTechType="1"/>
 *  *           </Phones>
 *  *         </ContactInfo>
 *  *         <ContactInfo ContactProfileType="availability" Index="1">
 *  *           <Names>
 *  *             <Name Gender="Male" Language="en">
 *  *               <GivenName>Steve</GivenName>
 *  *               <Surname>Martin</Surname>
 *  *               <JobTitle>Administration Employee</JobTitle>
 *  *             </Name>
 *  *           </Names>
 *  *           <Addresses>
 *  *             <Address>
 *  *               <AddressLine>200 rue de Paris</AddressLine>
 *  *               <CityName>Sotteville</CityName>
 *  *               <CountryName>FR</CountryName>
 *  *               <PostalCode>50340</PostalCode>
 *  *             </Address>
 *  *           </Addresses>
 *  *           <Emails>
 *  *             <Email>admin@fakehotel.com</Email>
 *  *           </Emails>
 *  *           <Phones>
 *  *             <Phone PhoneNumber="+33622111110" PhoneTechType="1" Extension="30"/>
 *  *             <Phone PhoneNumber="+33622111111" PhoneTechType="1" Extension="30"/>
 *  *             <Phone PhoneNumber="+33622111112" PhoneTechType="1" Extension="30"/>
 *  *             <Phone PhoneNumber="+33622111113" PhoneTechType="5"/>
 *  *             <Phone PhoneNumber="+33622111114" PhoneTechType="3"/>
 *  *           </Phones>
 *  *         </ContactInfo>
 *  *         <ContactInfo ContactProfileType="invoices">
 *  *           <Names>
 *  *             <Name>
 *  *               <GivenName>Carol</GivenName>
 *  *               <Surname>Xu</Surname>
 *  *             </Name>
 *  *           </Names>
 *  *           <Addresses>
 *  *             <Address>
 *  *               <AddressLine>1000 Eighth Avenue</AddressLine>
 *  *               <CityName>New York</CityName>
 *  *               <PostalCode>10000</PostalCode>
 *  *               <StateProv StateCode="NY"/>
 *  *               <CountryName>US</CountryName>
 *  *             </Address>
 *  *           </Addresses>
 *  *           <Emails>
 *  *             <Email>carol.xu@fakehotel.com</Email>
 *  *           </Emails>
 *  *           <Phones>
 *  *             <Phone Extension="30" PhoneNumber="+31622222220" PhoneTechType="1"/>
 *  *           </Phones>
 *  *         </ContactInfo>
 *  *         <ContactInfo ContactProfileType="availability" Index="2">
 *  *           <Names>
 *  *             <Name Gender="Female" Language="xt">
 *  *               <GivenName>Carol</GivenName>
 *  *               <Surname>Xu</Surname>
 *  *               <JobTitle>Owner</JobTitle>
 *  *             </Name>
 *  *           </Names>
 *  *           <Addresses>
 *  *             <Address>
 *  *               <AddressLine>1000 Eighth Avenue</AddressLine>
 *  *               <CityName>New York</CityName>
 *  *               <PostalCode>10000</PostalCode>
 *  *               <StateProv StateCode="NY"/>
 *  *               <CountryName>US</CountryName>
 *  *             </Address>
 *  *           </Addresses>
 *  *           <Emails>
 *  *             <Email>carol.xu@fakehotel.com</Email>
 *  *           </Emails>
 *  *           <Phones>
 *  *             <Phone PhoneNumber="+31622222220" PhoneTechType="1" Extension="30"/>
 *  *             <Phone PhoneNumber="+31622222221" PhoneTechType="1" Extension="30"/>
 *  *             <Phone PhoneNumber="+31622222222" PhoneTechType="1" Extension="30"/>
 *  *             <Phone PhoneNumber="+31622222223" PhoneTechType="5"/>
 *  *             <Phone PhoneNumber="+31622222224" PhoneTechType="3"/>
 *  *           </Phones>
 *  *         </ContactInfo>
 */
}
