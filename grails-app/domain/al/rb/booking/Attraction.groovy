package al.rb.booking

class Attraction {
    AttractionCategoryCode attractionCategoryCode
    String name
    float distance
    LanguageCode languageCode
    /**
     * Accepts: meters, kilometers, feet, miles. Default: meters
     */
    String unit
    static constraints = {
        attractionCategoryCode nullable: false
        name nullable: false
        distance nullable: false
        unit nullable: true, inList: ["meters", "kilometers", "feet", "miles"]
        languageCode nullable: true
    }
    static mapping = {
        unit defaultValue: "'meters'"
    }
    def buildXml(builder) {
        def aAttributes = [:]
        if (attractionCategoryCode != null) {
            aAttributes.put("AttractionCategoryCode",attractionCategoryCode.getCode())
        }
        if (name) {
            aAttributes.put("AttractionName", name)
        }
        if (distance) {
            aAttributes.put("Distance", distance)
        }
        if (unit) {
            aAttributes.put("DistanceUnit", unit)
        }
        if (languageCode) {
            aAttributes.put("LanguageCode", languageCode.getCode())
        }
        builder."Attraction"(aAttributes)
    }
}
