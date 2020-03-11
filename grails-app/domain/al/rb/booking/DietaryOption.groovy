package al.rb.booking

class DietaryOption {
    String name
    static constraints = {
        name nullable: false, inList: ["dairy free", "gluten free", "halal", "kosher", "vegan", "vegetarian"]
    }
    def buildXml(builder) {
        builder."DietaryOption"("Name": name)
    }
}
