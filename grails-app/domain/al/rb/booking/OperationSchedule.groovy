package al.rb.booking

class OperationSchedule {
//    ArrayList<OperationTime> operationTimes
    static hasMany = [operationTimes: OperationTime]
    static constraints = {
        operationTimes nullable: true
    }

    def buildXml(builder) {
        if (operationTimes) {
            builder."OperationTime" {
                operationTimes.each{ot->
                    ot.buildXml builder
                }
            }
        }
    }
}
