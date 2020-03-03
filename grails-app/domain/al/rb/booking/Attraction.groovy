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
        unit defaultValue: "meters"
    }

}
