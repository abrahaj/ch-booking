package al.rb.booking

enum ConfigurationCode {
    STANDARD_ARRANGEMENT(1, "standard arrangement"),
    ALTERNATIVE_ARRANGEMENT(2, "alternative arrangement")

    private final int code
    private final String name

    private ConfigurationCode(code, name) {
        this.code = code
        this.name = name
    }

    String getConfName() {
        return name
    }

    int getCode() {
        return code
    }

    @Override
    String toString() {
        return code + ": " + name
    }
    static constraints = {
    }
}
