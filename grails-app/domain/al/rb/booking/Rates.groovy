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
 * The OTA_HotelRateAmountNotif messages are used to update the price per rate/room/date on booking.com. F
 * or more information on pricing models
 */
class Rates {
    /**
     * Either 'Production' or, may contain 'Test', which will not update anything in the Booking.com database.
     */
    String target
    /**
     * LocatorID (required): should contain a unique ID (used as RecordID in OTA_HotelRateAmountNotifRS)
     */
    int locatorId
    /**
     * required
     * the period which you are updating (inclusive end date). Please note, only dates in the future can be updated.
     */
     Date startDate, endDate
    /**
     * (required): the Booking.com rate category ID which you are updating.
     */
    String ratePlanCode
    RatePlan ratePlan
    /**
     * (required): the Booking.com room ID which you are updating.
     */
    String roomTypeCode
    Room room
    /**
     * AmountAfterTax/AmountBeforeTax (required): the price for the given room for the given date for the given rate category.
     * The currency used for pricing is always the same for the hotel and set by Booking.com.
     * The currencies used per country can be found in the documentation under Overview, static information.
     * Prices cannot be removed after a value has been set.
     */
    Float amountAfterTax, amountBeforeTax
    /**
     * DecimalPlaces (optional):
     * the number of decimal places for a particular currency (eg. 8550 with DecimalPlaces="2" represents 85.50).
     */
    int decimalPlaces
    /**
     * NumberOfGuests (optional): 1, if set, the single use price is set.
     * Please note, Booking.com is only able to set prices per room night or for 1 person in a room, per night (so called single-use)
     */
    int numberOfGuests
    /**
     * CurrencyCode: Currency code of the hotel.
     * It must match the hotel currency code configuration on Booking.com, Otherwise you will get an error.
    */
    CurrencyCode currencyCode
    static constraints = {
        target nullable: true, inList: ["Test","Production"]
        locatorId nullable:true
        ratePlanCode nullable: true
        roomTypeCode  nullable: true
        amountAfterTax nullable: true
        amountBeforeTax nullable: true
        decimalPlaces nullable: true
        numberOfGuests numberOfGuests : true
        currencyCode nullable: true

    }

    static mapping = {
        target defaultValue: "'Production'"
    }

    def getXml(){
        def xmlWriter = new StringWriter()
        def xmlMarkup = new MarkupBuilder(xmlWriter)
        LocalDateTime now = LocalDateTime.now()
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        String timestamp = now.format(formatter)
        def OTA_HotelRateAmountNotifRQNS  = ["xmlns:xsi":"http://www.w3.org/2001/XMLSchema-instance",
                xmlns               : "http://www.opentravel.org/OTA/2003/05",
                "xsi:schemaLocation":"http://www.opentravel.org/OTA/2003/05 OTA_HotelRateAmountNotifRQ.xsd",
                                           "Version":"3.000"]

        OTA_HotelRateAmountNotifRQNS .put("TimeStamp", timestamp)
        OTA_HotelRateAmountNotifRQNS .put("Target", target)
        xmlMarkup."OTA_HotelRateAmountNotifRQ"(OTA_HotelRateAmountNotifRQNS )

        def rateAmountMessage = [:]
        if (locatorId == null){
            locatorId = this.id
        }
        if (locatorId) {
            rateAmountMessage.put("LocatorID", this.locatorId)
        }
        if (currencyCode == null) {
            currencyCode = this.ratePlan.property.currencyCode
        }
        if (ratePlanCode == null){
            ratePlanCode = this.ratePlan.ratePlanCode
        }
        if(roomTypeCode == null){
            roomTypeCode = this.room.roomTypeCode
        }

        def markupBuilder = new StreamingMarkupBuilder()
        def xml = markupBuilder.bind { builder ->
            OTA_HotelRateAmountNotifRQ(OTA_HotelRateAmountNotifRQNS) {
                RateAmountMessages{
                    RateAmountMessage(rateAmountMessage) {
                        StatusApplicationControl("Start":this.startDate, "End": this.endDate, "RatePlanCode": this.ratePlanCode, "InvTypeCode": this.roomTypeCode )
                        "Rates" {
                            Rate {
                                BaseByGuestAmts{
                                    BaseByGuestAmt("AmountAfterTax": this.amountAfterTax, "CurrencyCode" : this.currencyCode.getCode())
                                }
                            }
                        }
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
