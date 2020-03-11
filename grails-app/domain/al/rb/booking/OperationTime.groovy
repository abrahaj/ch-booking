package al.rb.booking

class OperationTime {
    /**
     * Specifies whether the @Start and @End times apply on this day of the week.
     */
    int mon, tue, weds, thur, fri, sat, sun
    String start, end
    static constraints = {
        start nullable: false, inList: ["00:00", "00:30", "01:00", "01:30", "02:00", "02:30", "03:00", "03:30", "04:00", "04:30", "05:00", "05:30", "06:00",
                                        "06:30", "07:00", "07:30", "08:00", "08:30", "09:00", "09:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00", "16:30", "17:00", "17:30", "18:00", "18:30", "19:00", "19:30", "20:00", "20:30", "21:00", "21:30", "22:00", "22:30", "23:00", "23:30", "00:00"]
        end nullable: false, inList: ["00:00", "00:30", "01:00", "01:30", "02:00", "02:30", "03:00", "03:30", "04:00", "04:30", "05:00", "05:30", "06:00",
                                      "06:30", "07:00", "07:30", "08:00", "08:30", "09:00", "09:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00", "16:30", "17:00", "17:30", "18:00", "18:30", "19:00", "19:30", "20:00", "20:30", "21:00", "21:30", "22:00", "22:30", "23:00", "23:30", "00:00"]
        mon nullable: true, inList: [0, 1]
        tue nullable: true, inList: [0, 1]
        weds nullable: true, inList: [0, 1]
        thur nullable: true, inList: [0, 1]
        fri nullable: true, inList: [0, 1]
        sat nullable: true, inList: [0, 1]
        sun nullable: true, inList: [0, 1]
    }

    static mapping = {
        mon defaultValue: "0"
        tue defaultValue: "0"
        weds defaultValue: "0"
        thur defaultValue: "0"
        fri defaultValue: "0"
        sat defaultValue: "0"
        sun defaultValue: "0"
    }

    def buildXml(builder){
        def aAttribute = [:]
        if (mon){
            aAttribute.put("Mon",1)
        }
        if (tue){
            aAttribute.put("Tue",1)
        }
        if (weds){
            aAttribute.put("Weds",1)
        }
        if (thur){
            aAttribute.put("Thur",1)
        }
        if (sat){
            aAttribute.put("Sat",1)
        }
        if (sun){
            aAttribute.put("Sun",1)
        }

        if (start){
            aAttribute.put("Start",start)
        }
        if (end){
            aAttribute.put("End",end)
        }
        builder."OperationTime"(aAttribute){

        }
    }
}
