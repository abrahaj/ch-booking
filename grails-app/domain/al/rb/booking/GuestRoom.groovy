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
 * Details of a room.
 */
class GuestRoom {
    /**
     * Specifies whether the request is targeted at a test or production property.
     * Accepts: Test, Production. Default: Test
     */
    String target
    /**
     * property ID
     */
    String hotelCode
    Property property
    /**
     * Accepts: Initial, Active, Deactivated. Default: Initial
     * Specifies whether the request is to create (Initial), activate (Active), or deactivate the room type (Deactivated).
     */
    String invStatusType
    /**
     * Accepts: New, Overlay
     * Specifies whether the request is to create a room type (New) or modify an existing one (Overlay).
     */
    String invNotifType
    /**
     * Required when modifying an existing room type.
     * The Booking.com room type ID, assigned on creation of the room type.
     */
    String invCode
    /**
     * Details of a property-level amenity.
     */
//    ArrayList<Amenity> amenities
    static hasMany = [amenities: Amenity]
    /**
     * Contains descriptive text and images for a room type.
     */
    Description description
    /**
     * Room occupancy details.
     */
    Occupancy occupancy
    /**
     * Container for room ID, size, and other details.
     */
    Room room
    /**
     * Container for room floor level and all possible floors.
     */
    RoomLocation roomLocation
    /**
     * Container for SubRooms and LicenseInfos.
     */
    TPAExtension tpaExtension

    static constraints = {
        target nullable: true, inList: ["Test","Production"]
        hotelCode nullable: true
        invStatusType nullable: true
        invNotifType nullable: true
        invCode nullable: true
        amenities nullable: true
        description nullable: true
        occupancy nullable: true
        room nullable: true
        roomLocation nullable: true
        tpaExtension nullable: true
        property nullable: true
    }

    static mapping = {
        target defaultValue: "'Production'"
    }

    def buildXml(builder) {
        builder."GuestRoom" {
            if (amenities) {
                builder."Amenities" {
                    amenities.each { am ->
                        am.buildXml(builder)
                    }
                }
            }
        }
    }

    def getXml(){
        def xmlWriter = new StringWriter()
        def xmlMarkup = new MarkupBuilder(xmlWriter)
//        LocalDateTime now = LocalDateTime.now()
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
//        String timestamp = now.format(formatter)
        def OTA_HotelInvNotifRQNS = [xmlns               : "http://www.opentravel.org/OTA/2003/05", "xmlns:xsi": "http://www.w3.org/2001/XMLSchema-instance",
                                     "xsi:schemaLocation": "http://www.opentravel.org/2014B/OTA_HotelInvNotifRQ.xsd",
                                     id: "OTA2014B", Version: "6.000", TransactionIdentifier:"5"]


        OTA_HotelInvNotifRQNS.put("Target", target)
        xmlMarkup."OTA_HotelInvNotifRQ"(OTA_HotelInvNotifRQNS)
        def sellableProductsAttributes = [:]
        if (hotelCode) {
            sellableProductsAttributes.put("HotelCode", this.hotelCode)
        }else {
            sellableProductsAttributes.put("HotelCode", this.property.code.toString())
        }
        def sellableProductAttributes = [:]
        if (invStatusType) {
            sellableProductAttributes.put("InvStatusType", this.invStatusType)
        }

        def markupBuilder = new StreamingMarkupBuilder()
        def xml = markupBuilder.bind { builder ->
            OTA_HotelInvNotifRQ(OTA_HotelInvNotifRQNS) {
                SellableProducts (sellableProductsAttributes) {
                    SellableProduct(sellableProductAttributes) {
                        builder."GuestRoom" {
                            if (occupancy) {
                                occupancy.buildXml(builder)
                            }
                            if (room) {
                                room.buildXml(builder)
                            }
                            if (amenities) {
                                builder."Amenities" {
                                    amenities.each { am ->
                                        am.buildXmlPerRoom(builder)
                                    }
                                }
                            }
                            if (description) {
                                description.buildXml(builder)
                            }
                            if (roomLocation) {
                                roomLocation.buildXml(builder)
                            }
                            if (tpaExtension) {
                                tpaExtension.buildXmlPerGuestRoom()
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
