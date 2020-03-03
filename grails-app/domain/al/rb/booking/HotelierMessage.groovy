package al.rb.booking

class HotelierMessage {
    LanguageCode language
    String text
    static constraints = {
        language nullable: true
        text nullable: true
    }
}
