package al.rb.booking

enum PhoneTechType {
    VOICE(1, "Voice"),
    FAX(3, "Fax"),
    MOBILE(5, "Mobile")
    private final int code
    private final String name

    private PhoneTechType(code, String name) {
        this.code = code
        this.name = name
    }

    String getPhoneTechType() {
        return name
    }

    int getCode() {
        return code
    }


    static constraints = {
    }
}
