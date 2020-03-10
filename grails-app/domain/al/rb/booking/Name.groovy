package al.rb.booking

class Name {
    /**
     * The contact's spoken language.
     */
    LanguageCode language
    /**
     * Contact person's gender.
     */
    String gender
    /**
     * Contact person's given name/first name.
     */
    String givenName
    /**
     * Contact person's last name/family name.
     */
    String surName
    /**
     * Te contact's Booking.com Job Title Code.
     */
    String jobTitle
    static constraints = {
        language nullable: false
        gender nullable: true, inList: ['Male', 'Female']
        givenName nullable: true
        surName nullable: true
        jobTitle nullable: true, inList: ['Administration Employee', 'Director of Business Development', 'E-Commerce Manager', 'Finance Manager', 'Front Office Employee', 'Front Office Manager', 'General Manager', 'Marketing Manager', 'Owner', 'Reservations Employee', 'Reservations Manager', 'Revenue Manager', 'Rooms Division Manager', 'Sales & Marketing Manager', 'Sales Executive', 'Sales Manager', 'Unknown']
    }

    def buildXml(builder){
        if (language==null){
            language = LanguageCode.EN
        }
        if (gender==null){
            gender = "Male"
        }
        builder."Name"("Language":language.getCode(),"Gender":gender){
            "GivenName" this.givenName
            "Surname" this.surName
            "JobTitle" this.jobTitle
        }
    }
}
