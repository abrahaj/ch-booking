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

class RatePlan {
    /**
     * Specifes whether the request is for production (Production) or testing (Test).
     */
    String target
    /**
     * Specifies the ID of the property you create a rate plan for.
     */
    String hotelCode
    Property property
    /**
     * Specifies the type of operation you want to perform on the rate plan. Possible values: New, Overlay, Remove, and Activate.
     */
    String ratePlanNotifType
    /**
     * Specifies the name of the rate plan.
     */
    String name
    /**
     * OTA_HotelProductNotifRQ
     * The Booking.com rate plan ID.
     * Returned in OTA_HotelRatePlanNotifRS upon creation of the rate plan.
     */
    String ratePlanCode
    /**
     * OTA_HotelRatePlanNotifRQ
     * A custom rate plan ID, specified by the Connectivity Partner, for cross-referencing purposes.
     * Only allowed when @RatePlanNotifType="New".
     */
    String ratePlanID
    static constraints = {
        target nullable: false, inList: ["Test","Production"]
        ratePlanNotifType nullable: false, inList: ["New", "Overlay", "Remove", "Activate"]
        ratePlanCode nullable: true
        ratePlanID nullable: true
    }

    static mapping = {
        target defaultValue: "'Production'"
        ratePlanNotifType defaultValue: "'New'"
    }

    def buildXmlRatePlan(builder){
        def rPAttributes = [:]
        if (ratePlanNotifType) {
            rPAttributes.put("RatePlanNotifType", ratePlanNotifType)
        }
        if (ratePlanID) {
            rPAttributes.put("RatePlanID", ratePlanID)
        }
        builder."RatePlan"(rPAttributes)
    }
    def buildXmlHotelProduct(builder){
        def hPAttributes = [:]
        if (ratePlanCode) {
            hPAttributes.put("RatePlanCode", ratePlanCode)
        }
        builder."RatePlan"(hPAttributes)
    }
    def getXml(){
        def xmlWriter = new StringWriter()
        def xmlMarkup = new MarkupBuilder(xmlWriter)
        LocalDateTime now = LocalDateTime.now()
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        String timestamp = now.format(formatter)
        def OTA_HotelRatePlanNotifRQNS  = [xmlns               : "http://www.opentravel.org/OTA/2003/05", "MessageContentCode":"8",
                                     "Version":"1.005"]

        OTA_HotelRatePlanNotifRQNS .put("TimeStamp", timestamp)
        OTA_HotelRatePlanNotifRQNS .put("Target", target)
        xmlMarkup."OTA_HotelRatePlanNotifRQ"(OTA_HotelRatePlanNotifRQNS )
        def ratePlans = [:]
        if (hotelCode) {
            ratePlans.put("HotelCode", this.hotelCode)
        }
        def ratePlan = [:]
        if (ratePlanNotifType) {
            ratePlan.put("RatePlanNotifType", this.ratePlanNotifType)
        }

        def markupBuilder = new StreamingMarkupBuilder()
        def xml = markupBuilder.bind { builder ->
            OTA_HotelRatePlanNotifRQ(OTA_HotelRatePlanNotifRQNS) {
                RatePlans (ratePlans) {
                    RatePlan(ratePlan) {
                        Description("name":this.name)
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
