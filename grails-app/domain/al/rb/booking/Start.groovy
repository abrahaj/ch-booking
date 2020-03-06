package al.rb.booking
/**
 * Starting point details.
 */
class Start {
    /**
     * The type of starting point.
     */
    String type
    /**
     * The 3-letter IATA code of the airport that serves as the starting location.
     * https://www.iata.org/en/publications/directories/code-search/
     */
    String code
    static constraints = {
        type nullable: false, inList: ['airport']
        code nullable: false
    }
}
