package al.rb.booking

class CancelPenalty {
    TPAExtension tpaExtension
    String policyCode
    static constraints = {
        tpaExtension nullable: true
        policyCode nullable: true
    }

    def buildXml(builder){
        if(policyCode) {
            builder."CancelPenalty"("PoliceCode": policyCode)
        }
    }
}
