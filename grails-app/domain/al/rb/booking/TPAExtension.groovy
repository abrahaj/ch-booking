package al.rb.booking

class TPAExtension {
    HotelierMessage hotelierMessage
    ArrayList<Ambiance> ambiances
    ArrayList<DietaryOption> dietaryOptions
    static constraints = {
        hotelierMessage nullable: true
        ambiances nullable: true
        dietaryOptions nullable: true
    }
}
