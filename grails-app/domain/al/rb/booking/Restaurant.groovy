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
    ArrayList<CusineCode> cusineCodes
    /**
     * Container for operation schedules (opening/closing times).
     */
    ArrayList<OperationSchedule> operationSchedules
    /**
     * Container for special features.
     */
    ArrayList<Feature> features
    TPAExtension tpa_extension
    static constraints = {
        name nullable: true
        offerBreakfast nullable: true, inList: [0, 1]
        offerBrunch nullable: true, inList: [0, 1]
        offerDinner nullable: true, inList: [0, 1]
        offerLunch nullable: true, inList: [0, 1]
        cusineCodes nullable: true
        operationSchedules nullable: true
        features nullable: true

    }
    static mapping = {
        offerBreakfast defaultValue: "0"
        offerBrunch defaultValue: "0"
        offerDinner defaultValue: "0"
        offerLunch defaultValue: "0"
    }
}
