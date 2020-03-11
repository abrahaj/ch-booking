package al.rb.booking

class HotelierMessage {
    LanguageCode language
    String text
    static constraints = {
        language nullable: true
        text nullable: true
    }

    def buildXml(builder) {
        if (language != null) {
            language = LanguageCode.EN
        }
        def aAtributes = ["Language": language.getCode()]
        builder."HotelierMessage"(aAtributes) { mkp.yield(text) }
    }
}
