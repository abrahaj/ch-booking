package al.rb.booking
/**
 * An Entity represents a stakeholder that may have access to register and retrieve information related to properties in the system.
 *
 * Typical use: A hotel Owner; a Reseller Account
 */
class Entity {
    String name
    String email
    static constraints = {
        name nullable: false
        email nullable: false
    }
}
