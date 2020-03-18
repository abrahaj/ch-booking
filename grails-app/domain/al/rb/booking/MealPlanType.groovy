package al.rb.booking
/**
 * (MPT) Meal Plan Types (OTA 2014B)
 */
class MealPlanType {
    int code
    String description
    String mapping
    static constraints = {
        code nullable:false
        description nullable: false
        mapping nullable: false
    }

    def buildXml(builder){
        if(code) {
            builder."MealPlan"("MealPlanCode": code)
        }
    }
}
