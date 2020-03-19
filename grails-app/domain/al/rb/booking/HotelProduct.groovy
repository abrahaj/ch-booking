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
 * Products (combinations of room types, rate plans, and policies).
 */
class HotelProduct {
    /**
     * The Booking.Com Language Code for the provided content.
     */
    LanguageCode languageCode
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
     * Specifies the type of action being performed on the product.
     * Accepts: New (create product), Remove (delete product).
     */
    String productNotifType
    /**
     * Room types in the product.
     * Rate plans in the product.
     * Mealplans in the product.
     */
    static hasMany = [roomTypes: RoomType, ratesPlan: RatePlan, mealPlan: MealPlanType]
    /**
     * Cancellation and booking policies for the product.
     */
    Policyinfo policyinfo
    /**
     * Container for PricingType.
     */
    TPAExtension tpaExtension

    static constraints = {
        languageCode nullable: true
        target nullable: false, inList: ["Test", "Production"]
        hotelCode nullable: true
        productNotifType nullable: false, inList: ["New", "Remove"]
        policyinfo nullable: true
        tpaExtension nullable: true
    }

    def getXml() {
        def xmlWriter = new StringWriter()
        def xmlMarkup = new MarkupBuilder(xmlWriter)
        LocalDateTime now = LocalDateTime.now()
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        String timestamp = now.format(formatter)
        def OTA_HotelProductNotifRQNS = [xmlns: "http://www.opentravel.org/OTA/2003/05", "MessageContentCode": "8", "Version": "1.005"]

        if (languageCode == null) {
            languageCode = LanguageCode.EN
        }
        OTA_HotelProductNotifRQNS.put("PrimaryLangID", languageCode.getCode())
        OTA_HotelProductNotifRQNS.put("TimeStamp", timestamp)
        OTA_HotelProductNotifRQNS.put("Target", target)
        xmlMarkup."OTA_HotelProductNotifRQ"(OTA_HotelProductNotifRQNS)
        def hotelProducts = [:]
        if (hotelCode) {
            hotelProducts.put("HotelCode", hotelCode)
        }else {
            hotelProducts.put("HotelCode", property.code.toString())
        }

        def hotelProduct = [:]
        if (productNotifType) {
            hotelProduct.put("ProductNotifType", this.productNotifType)
        }

        def markupBuilder = new StreamingMarkupBuilder()
        def xml = markupBuilder.bind { builder ->
            OTA_HotelProductNotifRQ(OTA_HotelProductNotifRQNS) {
                HotelProducts(hotelProducts) {
                    "HotelProduct"(hotelProduct) {
                        builder."RoomTypes" {
                            if (roomTypes.size() > 0) {
                                roomTypes.each { rt ->
                                    rt.buildXml(builder)
                                }
                            }
                        }
                        builder."RatesPlan" {
                            if (ratesPlan.size() > 0) {
                                ratesPlan.each { rp ->
                                    rp.buildXmlHotelProduct(builder)
                                }
                            }
                        }
                        builder."ValueAddInclusions" {
                            if (mealPlan.size() > 0) {
                                mealPlan.each { v ->
                                    v.buildXml(builder)
                                }
                            }
                        }

                            builder."PolicyInfo" {
                                if (policyinfo) {
                                    policyinfo.buildXml(builder)
                                }
                            }
                            if (tpaExtension) {
                                tpaExtension.buildXmlPerHotelProduct(builder)
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
