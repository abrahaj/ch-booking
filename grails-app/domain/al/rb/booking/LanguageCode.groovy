/*
 *
 *  New Ventures Shpk
 *  __________________
 *
 *   [2019] New Ventures Shpk
 *   All Rights Reserved.
 *
 *  NOTICE:  All information contained herein is, and remains
 *  the property of New Ventures Shpk and its suppliers,
 *  if any.  The intellectual and technical concepts contained
 *  herein are proprietary to New Ventures Shpk.
 *  Dissemination of this information or reproduction of this material
 *  is strictly forbidden unless prior written permission is obtained
 *  from New Ventures Shpk.
 *
 */

package al.rb.booking
/**
 * (BCL) Booking.com Language Codes¶
 * https://connect.booking.com/user_guide/site/en-US/codes-bcl/
 */
enum LanguageCode {
    AR("ar", "Arabic"),
    AZ("az", "Azerbaijani"),
    BG("bg", "Bulgarian"),
    CA("ca", "Catalan"),
    CS("cs", "Czech"),
    DA("da", "Danish"),
    DE("de", "German"),
    EL("el", "Greek"),
    EN("en", "English (UK)"),
    ENUS("en-us", "English (American)"),
    ES("es", "Spanish"),
    ESAR("es-ar", "Spanish (Argentine)"),
    ET("et", "Estonian"),
    FR("fr", "French"),
    FI("fi", "Finnish"),
    HE("he", "Hebrew"),
    HI("hi", "Hindi"),
    HR("hr", "Croatian"),
    HU("hu", "Hungarian"),
    ID("id", "Indonesian"),
    IS("is", "Icelandic"),
    IT("it", "Italian"),
    JA("ja", "Japanese"),
    KM("km", "Khmer"),
    KO("ko", "Korean"),
    LO("lo", "Lao"),
    LT("lt", "Lithuanian"),
    LV("lv", "Latvian"),
    MS("ms", "Malay"),
    NL("nl", "Dutch"),
    NO("no", "Norwegian"),
    PL("pl", "Polish"),
    PT("pt", "Portuguese"),
    PTBR("pt-br", "Portuguese (Brazilian)"),
    RU("ru", "Russian"),
    RO("ro", "Romanian"),
    SK("sk", "Slovak"),
    SL("sl", "Slovenian"),
    SR("sr", "Serbian"),
    SV("sv", "Swedish"),
    TL("tl", "Tagalog"),
    TH("th", "Thai"),
    TR("tr", "Turkish"),
    UK("uk", "Ukrainian"),
    VI("vi", "Vietnamese"),
    XA("xa", "Spanish (Argentine)"),
    XB("xb", "Portuguese (Brazilian)"),
    XS("xs", "Spanish (South American)"),
    XT("xt", "Chinese (Cantonese)"),
    XU("xu", "English (American)"),
    ZH("zh", "Chinese (Mandarin)"),
    ZHCN("zh-cn", "Chinese (Simplified)"),
    ZHTW("zh-tw", "Chinese (Traditional)")

    private final String code
    private final String language

    private LanguageCode(code, String language) {
        this.code = code
        this.language = language
    }

    String getLanguage() {
        return language
    }

    String getCode() {
        return code
    }

    @Override
    String toString() {
        return code + ": " + language
    }
}
