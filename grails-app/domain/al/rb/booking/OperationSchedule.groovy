package al.rb.booking

class OperationSchedule {
    ArrayList<OperationTime> operationTimes
    static constraints = {
        operationTimes nullable: true
    }
}
