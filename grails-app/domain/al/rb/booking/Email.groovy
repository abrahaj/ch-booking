package al.rb.booking

class Email {
    String email
    static constraints = {
        email nullable:true
    }

    def buildEmail(builder){
        builder."Email"{
            "Email" email
        }
    }
}
