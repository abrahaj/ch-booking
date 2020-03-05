package al.rb.booking

enum CusineCode {
    American(1, "American"),
    French(2, "French"),
    Italian(3, "Italian"),
    Seafood(4, "Seafood"),
    Indian(5, "Indian"),
    Asian(6, "Asian"),
    Mexican(8, "Mexican"),
    Greek(9, "Greek"),
    Thai(10, "Thai"),
    Chinese(11, "Chinese"),
    Vietnamese(12, "Vietnamese"),
    Middle_eastern(13, "Middle eastern"),
    Japanese(14, "Japanese"),
    Moroccan(15, "Moroccan"),
    European(17, "European"),
    Ethiopian(18, "Ethiopian"),
    Spanish(19, "Spanish"),
    Turkish(22, "Turkish"),
    Russian(23, "Russian"),
    German(24, "German"),
    Texmex(26, "Texmex"),
    Irish(29, "Irish"),
    Scottish(30, "Scottish"),
    Argentinian(33, "Argentinian"),
    Brazilian(34, "Brazilian"),
    Creole(41, "Creole"),
    Korean(42, "Korean"),
    Latin_american(43, "Latin american"),
    Steakhouse(44, "Steakhouse"),
    International(49, "International"),
    Mediterranean(51, "Mediterranean"),
    Pizza(53, "Pizza"),
    Austrian(66, "Austrian"),
    Australian(67, "Australian"),
    Indonesian(73, "Indonesian"),
    Grill_bbq(74, "Grill bbq"),
    Polish(76, "Polish"),
    African(78, "African"),
    Belgian(79, "Belgian"),
    Portuguese(80, "Portuguese"),
    British(5000, "British"),
    Cambodian(5001, "Cambodian"),
    Cantonese(5002, "Cantonese"),
    Croation(5003, "Croation"),
    Dutch(5004, "Dutch"),
    Hungarian(5005, "Hungarian"),
    Local(5006, "Local"),
    Malaysian(5007, "Malaysian"),
    Nepalese(5008, "Nepalese"),
    Peruvian(5009, "Peruvian"),
    Sichuan(5010, "Sichuan"),
    Singaporean(5011, "Singaporean"),
    South_African(5012, "South African"),
    Sushi(5013, "Sushi")

    private final int code
    private final String name

    private CusineCode(code, String name) {
        this.code = code
        this.name = name
    }

    String getName() {
        return name
    }

    int getCode() {
        return code
    }

    static constraints = {
    }
}
