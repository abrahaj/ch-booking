package al.rb.booking

class TPAExtension {
    HotelierMessage hotelierMessage

    NoShowPolicy noShowPolicy
    PrepaymentPolicy prepaymentPolicy
    InternetFeePolicy internetFeePolicy
    ParkingFeePolicy parkingFeePolicy
    GuestInformation guestInformation
    SellMealsThroughBooking sellMealsThroughBooking
    PropertyTaxInfo propertyTaxInfo
    PreventLikelyToBeCancelledBookings preventLikelyToBeCancelledBookings
    CancellationGracePeriod cancellationGracePeriod
    TotalNumberOfFloors totalNumberOfFloors
    PricingType pricingType

//    ArrayList<Ambiance> ambiances
//    ArrayList<DietaryOption> dietaryOptions
//    ArrayList<Route> routes
//    ArrayList<ImageTag> imageTags
//    ArrayList<Condition> conditions
//    ArrayList<StandardPhrase> standardPhrases

    static hasMany = [ambiances: Ambiance, dietaryOptions: DietaryOption, routes: Route, imageTags: ImageTag, conditions: Condition, standardPhrases: StandardPhrase]
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
        propertyTaxInfo nullable: true
        standardPhrases nullable: true
        guestInformation nullable: true
        sellMealsThroughBooking nullable: true
        preventLikelyToBeCancelledBookings nullable: true
        cancellationGracePeriod nullable: true
        totalNumberOfFloors nullable: true
        pricingType nullable: true
    }

    def buildXml(builder) {
        builder."TPAExtension" {
            if (hotelierMessage) {
                hotelierMessage.buildXml(builder)
            }
            if (ambiances){
                builder."Ambiances"{
                    ambiances.each{ab->
                        ab.buildXml(builder)
                    }
                }
            }
            if (dietaryOptions){
                builder."DietaryOptions"{
                    dietaryOptions.each{dop->
                        dop.buildXml(builder)
                    }
                }
            }
        }
    }
}
