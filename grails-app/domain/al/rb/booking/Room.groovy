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

class Room {
    /**
     * The licence number of the room. Mandatory for certain regions.
     * 	See Licence requirements per region.
     */
    String licenseNumber
    /**
     * Specifies whether the property allows smoking in the room.
     * 1 = non-smoking, 0 = smoking allowed. Default: unknown. If this parameter is not passed in the request,
     * smoking policy is set to unknown which is same as setting both in the extranet. It applies for overlay also.
     */
    String nonsmoking
    /**
     *The number of rooms of this type the property has.
     */
    int quantity
    /**
     * A custom room ID, assigned by the Connectivity Partner, for cross-referencing purposes.
     * The Booking.com room type ID is specified in SellableProduct[@InvCode]
     */
    String roomID
    /**
     * The number or name of the Booking.com Room Type Code.
     */
    RoomTypeCodes roomType
    /**
     * The size of the room.
     * 	Use @SizeMeasurementUnit to specify unit of measurement.
     */
    Float sizeMeasurement
    /**
     * The measurement unit for @SizeMeasurement.
     * Accepts: sqm (square metres), sqft (square feet). Default: sqm
     */
    String sizeMeasurementUnit



    static constraints = {
        licenseNumber nullable: true
        nonsmoking nullable:false, inList: ["0","1","unknown"]
        quantity nullable:true
        roomID nullable: true
        roomType nullable: true
        sizeMeasurement nullable: false
        sizeMeasurementUnit nullable: false, inList: ["sqm","sqft"]
    }

    static mapping = {
        nonsmoking defaultValue: "'unknown'"
        sizeMeasurementUnit defaultValue: "'sqm'"
    }

    def buildXml(builder) {
        def roomAttributes = [:]
        if (licenseNumber) {
            roomAttributes.put("LicenseNumber", licenseNumber)
        }
        if (nonsmoking) {
            roomAttributes.put("Nonsmoking", nonsmoking)
        }else {
            nonsmoking = "unknown"
            roomAttributes.put("Nonsmoking", this.nonsmoking)
        }
        if(quantity){
            roomAttributes.put("Quantity",quantity)
        }
        if(roomID){
            roomAttributes.put("RoomID", roomID)
        }
        if(roomType){
            roomAttributes.put("RoomType",roomType)
        }
        if (sizeMeasurement){
            roomAttributes.put("SizeMeasurement",sizeMeasurement)
        }
        if(sizeMeasurementUnit){
            roomAttributes.put("SizeMeasurementUnit",sizeMeasurementUnit)
        }
        else {
            sizeMeasurementUnit = "sqm"
            roomAttributes.put("SizeMeasurementUnit",this.sizeMeasurementUnit)
        }
        builder."Room"(roomAttributes)
    }
}
