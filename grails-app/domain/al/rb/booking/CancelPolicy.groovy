package al.rb.booking

class CancelPolicy {
    static hasMany = [cancelPenalty: CancelPenalty]
    static constraints = {
    }

    def buildXml(builder){
        if(cancelPenalty.size() >0){
            builder."CancelPolicy"{
                cancelPenalty.each { cp->
                    cp.buildXml(builder)
                }
            }
        }
    }
}
