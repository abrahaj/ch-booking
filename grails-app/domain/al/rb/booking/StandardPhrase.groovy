package al.rb.booking

class StandardPhrase {
    /**
     * Specifies whether the standard phrase is enabled.
     */
    short enabled
    /**
     * Specifies which standard phrase must be displayed.
     */
    String name
    static constraints = {
        enabled nullable: false, inList: [0,1]
        name nullable: false, inList: ["GuestIdentification" ,"InformArrivalTime","KeyCollection","PayBeforeStay","Renovation","SecurityDeposit",
                                   "TattooRestriction","RequireInvoice","EarlyDeparture","PedestrianZoneOnly","LimitedParking","AccessByUnpavedRoad","OnsiteFunctions","NoHenStagParty","ResidentialArea","BusyArea","HalfBoardNoDrinks","NoPublicTransport"]
    }
}
