package al.rb.booking

class Ambiance {
    String name
    static constraints = {
        name nullable: false, inList: ["family/kids friendly", "modern", "romantic", "traditional"]
    }

    def buildXml(builder) {
        builder."Ambiance"("Name": name)
    }
}
