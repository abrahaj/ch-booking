package al.rb.booking

import groovy.xml.MarkupBuilder
import groovy.xml.StreamingMarkupBuilder

import javax.xml.transform.OutputKeys
import javax.xml.transform.Source
import javax.xml.transform.Transformer
import javax.xml.transform.TransformerFactory
import javax.xml.transform.stream.StreamResult
import javax.xml.transform.stream.StreamSource
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * OTA_HotelAvailNotif (2003B)
 */
class Availability {
    /**
     * Either "Production" or, may contain "Test", which is used for test properties.
     */
    String target
    /**
     * BookingLimit (optional): Are the amount of available rooms to sell for a hotel.
     * Bookinglimit should be left out when updating restrictions in the same "AvailStatusMessage" block,
     * because a "RatePlanCode" attribute is mandatory in that case and BookingLimit is updated on room level, not rate level.
     */
    int bookingLimit
    /**
     * LocatorID (required): should contain a unique ID (used as RecordID in OTA_HotelAvailNotifRS)
     */
    int locatorId
    /**
     * Start / End (required): the period which you are updating (inclusive end date). Please note, only dates in the future can be updated.
     */
    String startDate
    String endDate

    Room room

    RatePlan ratePlan
    /**
     * the BOOKING.COM rate category ID which you are updating.
     * Needs to be left out when specifying Bookinglimit in AvailStatusMessage, as the availability is updated on room level.
     */
    String ratePlanCode
    /**
     * the BOOKING.COM room ID which you are updating.
     */
    String invTypeCode
    /**
     * ArrivalDateBased can be set to 0 or 1.
     * If this boolean value is set to 1, the restriction set has an effect only on the arrival day of a booking, whereas the '0' value may affect a search for availability or reservation on all the dates that the query covers.
     * The ArrivalDateBased attribute is optional and when left out, '0' is assumed.
     */
    boolean arrivalBased
    /**
     *
     */
    int minTime
    int maxTime
    String minMessageType
    String maxMessageType
    /**
     * Restriction status
     * "Close" (or "Open")
     */
    String restrictionStatus
    /**
     * The restriction attribute may contain "Departure" or "Arrival".
     */
    String restriction
    /**
     * MinAdvancedBookingOffset means the minimum time before the checkin date guests will be allowed to make a booking.
     * To set restrictions, MinAdvanceBookingOffset or MaxAdvanceBookingOffset you must pass a RatePlanCode.
     * If RatePlanCode is not passed an error is displayed.
     */
    String minAdvancedBookingOffset
    /**
     * MaxAdvancedBookingOffset means the maximum time before the checkin date the guests will be allowed to make a booking. Both have the same format.
     */
    String maxAdvancedBookingOffset
    static constraints = {
        target nullable: false, inList: ['Test', 'Production']
        bookingLimit nullable: true
        locatorId nullable:false
        startDate nullable:false
        endDate nullable: false
        ratePlanCode nullable: true
        ratePlan nullable: true
        invTypeCode nullable: true
        arrivalBased nullable: true
        minTime nullable : true
        maxTime nullable : true
        minMessageType nullable: true
        maxMessageType nullable: true
        restrictionStatus nullable: true
        restriction nullable: true, inList: ['Departure','Arrival']
        minAdvancedBookingOffset nullable: true
        maxAdvancedBookingOffset nullable: true
    }

    static mapping = {
        target defaultValue: "'Production'"
    }

    def getXml() {
        def xmlWriter = new StringWriter()
        def xmlMarkup = new MarkupBuilder(xmlWriter)
        LocalDateTime now = LocalDateTime.now()
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        String timestamp = now.format(formatter)
        def OTA_HotelAvailNotifRQNS = ["xmlns:xsi":"http://www.w3.org/2001/XMLSchema-instance", xmlns: "http://www.opentravel.org/OTA/2003/05",
                                         "xsi:schemaLocation":"http://www.opentravel.org/OTA/2003/05 OTA_HotelAvailNotifRQ.xsd", "MessageContentCode": "8", "Version": "1.005"]

        OTA_HotelAvailNotifRQNS.put("TimeStamp", timestamp)
        OTA_HotelAvailNotifRQNS.put("Target", target)
        xmlMarkup."OTA_HotelAvailNotifRQ"(OTA_HotelAvailNotifRQNS)

        def availStatusMessage = [:]
        locatorId = 1
        if (bookingLimit) {
            availStatusMessage.put("BookingLimit", this.bookingLimit)
        }
        availStatusMessage.put("LocatorId", this.locatorId)

        def statusApplicationControl =[:]
        if(startDate){
            statusApplicationControl.put("Start", this.startDate)
        }
        if(endDate) {
            statusApplicationControl.put("End", this.endDate)
        }
        if(invTypeCode){
            statusApplicationControl.put("InvTypeCode", this.invTypeCode)
        }else{
            statusApplicationControl.put("InvTypeCode", this.room.roomID.toString())
        }
        def availStatusMessage2 =[:]
        locatorId = locatorId + 1

        availStatusMessage2.put("LocatorId", this.locatorId)

        def statusApplicationControl2 =[:]
        if(startDate){
            statusApplicationControl2.put("Start", this.startDate)
        }
        if(endDate) {
            statusApplicationControl2.put("End", this.endDate)
        }
        if(invTypeCode){
            statusApplicationControl2.put("InvTypeCode", this.invTypeCode)
        }else{
            statusApplicationControl2.put("InvTypeCode", this.room.roomID.toString())
        }
        if(ratePlanCode){
            statusApplicationControl2.put("RatePlanCode", this.ratePlanCode)
        }else {
            statusApplicationControl2.put("RatePlanCode", this.ratePlan.ratePlanCode.toString())
        }
        def lengthsOfStay=[:]
        //if(arrivalBased){
            lengthsOfStay.put("ArrivalBased",(this.arrivalBased == false?0:1))
        //}

        def minlengthOfStay=[:]
        if(minTime){
            minlengthOfStay.put("Time", this.minTime)
        }
        if(minMessageType){
            minlengthOfStay.put("MinMaxMessageType", this.minMessageType)
        }
        def maxlengthOfStay=[:]
        if(maxTime){
            maxlengthOfStay.put("Time", this.maxTime)
        }
        if(maxMessageType){
            maxlengthOfStay.put("MinMaxMessageType", this.maxMessageType)
        }
        def restrictions=[:]
        if(restriction){
            restrictions.put("Restriction", this.restriction)
        }
        if(restrictionStatus){
            restrictions.put("RestrictionStatus", this.restrictionStatus)
        }
        def markupBuilder = new StreamingMarkupBuilder()
        def xml = markupBuilder.bind { builder ->
            OTA_HotelAvailNotifRQ(OTA_HotelAvailNotifRQNS) {
                AvailStatusMessages {
                    AvailStatusMessage (availStatusMessage) {
                        StatusApplicationControl (statusApplicationControl)
                    }
                    AvailStatusMessage (availStatusMessage2) {
                        StatusApplicationControl (statusApplicationControl2)
                        LengthsOfStay (lengthsOfStay){
                            LengthOfStay(minlengthOfStay)
                            LengthOfStay(maxlengthOfStay)
                        }
                        RestrictionStatus(restrictions)
                    }
                }
            }
        }
        log.info("\r\n" + prettyFormat(xml.toString(), 2))
        return xml.toString()
    }

    String prettyFormat(String input, int indent) {
        try {
            Source xmlInput = new StreamSource(new StringReader(input))
            StringWriter stringWriter = new StringWriter()
            StreamResult xmlOutput = new StreamResult(stringWriter)
            TransformerFactory transformerFactory = TransformerFactory.newInstance()
            transformerFactory.setAttribute("indent-number", indent)
            Transformer transformer = transformerFactory.newTransformer()
            transformer.setOutputProperty(OutputKeys.INDENT, "yes")
            transformer.transform(xmlInput, xmlOutput)
            return xmlOutput.getWriter().toString()
        } catch (Exception e) {
            throw new RuntimeException(e) // simple exception handling, please review it
        }
    }
}
