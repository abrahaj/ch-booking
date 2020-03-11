package al.rb.booking
/**
 * Restaurant details.
 */
class Restaurant {
    /**
     * Name of the restaurant.
     */
    String name
    /**
     * Specifies whether restaurant offers breakfast.
     */
    short offerBreakfast, offerBrunch, offerDinner, offerLunch
    /**
     * Container for cuisine details.
     */
//    ArrayList<CusineCode> cusineCodes
    /**
     * Container for operation schedules (opening/closing times).
     */
//    ArrayList<OperationSchedule> operationSchedules
    /**
     * Container for special features.
     */
//    ArrayList<Feature> features
    static hasMany = [features: Feature, operationSchedules: OperationSchedule, cusineCodes: CusineCode]
    TPAExtension tpa_extension
    static constraints = {
        name nullable: true
        offerBreakfast nullable: true, range:0..1
        offerBrunch nullable: true, range:0..1
        offerDinner nullable: true, range:0..1
        offerLunch nullable: true, range:0..1
        cusineCodes nullable: true, range:0..1
        operationSchedules nullable: true
        features nullable: true
        tpa_extension nullable: true

    }
    static mapping = {
        offerBreakfast defaultValue: "0"
        offerBrunch defaultValue: "0"
        offerDinner defaultValue: "0"
        offerLunch defaultValue: "0"
    }

    def buildXml(builder) {
        def aAttribute = [:]
        if (name) {
            aAttribute.put("RestaurantName", name)
        }
        if (offerBreakfast) {
            aAttribute.put("OfferBreakfast", offerBreakfast)
        }
        if (offerBrunch) {
            aAttribute.put("OfferBrunch", offerBrunch)
        }
        if (offerDinner) {
            aAttribute.put("OfferDinner", offerDinner)
        }
        if (offerLunch) {
            aAttribute.put("OfferLunch", offerLunch)
        }

        builder."Restaurant"(aAttribute){
            if (cusineCodes){
                builder."CuisineCodes" {
                    cusineCodes.each { cc ->
                        builder."CuisineCode"("Code":cc.getCode())
                    }
                }
            }
            if (operationSchedules){
                builder."OperationSchedules" {
                    operationSchedules.each { os ->
                        os.buildXml builder
                    }
                }
            }
            if (features){
                builder."Features" {
                    features.each { ft ->
                        ft.buildXml builder
                    }
                }
            }
            if (tpa_extension){
                tpa_extension.buildXml(builder)
            }
        }

    }
}
