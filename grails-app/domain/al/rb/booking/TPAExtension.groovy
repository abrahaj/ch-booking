package al.rb.booking

class TPAExtension {
    HotelierMessage hotelierMessage
    ArrayList<Ambiance> ambiances
    ArrayList<DietaryOption> dietaryOptions
    ArrayList<Route> routes
    ArrayList<ImageTag> imageTags
    NoShowPolicy noShowPolicy
    PrepaymentPolicy prepaymentPolicy
    ArrayList<Condition> conditions
    InternetFeePolicy internetFeePolicy
    ParkingFeePolicy parkingFeePolicy
    ArrayList<StandardPhrase> standardPhrases
    GuestInformation guestInformation
    SellMealsThroughBooking sellMealsThroughBooking
    PropertyTaxInfo propertyTaxInfo
    PreventLikelyToBeCancelledBookings preventLikelyToBeCancelledBookings
    CancellationGracePeriod cancellationGracePeriod
    TotalNumberOfFloors totalNumberOfFloors
    PricingType pricingType
    static constraints = {
        hotelierMessage nullable: true
        ambiances nullable: true
        dietaryOptions nullable: true
        routes nullable: true
        imageTags nullable: true
        noShowPolicy nullable: true
        prepaymentPolicy nullable: true
        conditions nullable: true
        internetFeePolicy nullable: true
        parkingFeePolicy nullable: true

    }
}
