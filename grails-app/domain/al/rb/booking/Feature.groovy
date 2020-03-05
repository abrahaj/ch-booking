package al.rb.booking

class Feature {
    String descriptiveText
    static constraints = {
        descriptiveText nullable: false, inList: ["a la carte", "buffet", "guests only", "accepts reservations", "outdoor seating"]
    }
}
