package al.rb.booking

import grails.converters.JSON
import grails.gorm.transactions.ReadOnly
import grails.gorm.transactions.Transactional
import grails.plugin.springsecurity.annotation.Secured
import grails.validation.ValidationException

import static org.springframework.http.HttpStatus.*

@ReadOnly
//@Secured(["ROLE_ADMIN"])
class PropertyController {

    PropertyService propertyService

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond propertyService.list(params), model: [propertyCount: propertyService.count()]
    }

    def show(Long id) {
        log.info("REQUEST HERE " +request.getRemoteAddr())

        Property property = propertyService.get(id)
        log.info("HERE IS THE XML TO REQUEST " + property.getXml())
        respond property
    }

    def rooms(Long id) {
        log.info("REQUEST HERE " +request.getRemoteAddr())

        Property property = propertyService.get(id)
        List<GuestRoom> guest = GuestRoom.findAllByProperty(property)
        List<Room> rooms=[]
        guest.each {g->
            rooms << g.room
        }
        //log.info("HERE IS THE XML TO REQUEST " + property.getXml())
        respond rooms
    }

    @Transactional
    def save(Property property) {
        if (property == null) {
            render status: NOT_FOUND
            return
        }
        def statusCode
        def errorString
        if (property.hasErrors()) {
            errorString = property.errors
            statusCode = StatusCodeRB.FAILED_PROPERTY
            transactionStatus.setRollbackOnly()
            respond property.errors
            return
        }

        try {
            propertyService.save(property)
            statusCode = StatusCodeRB.OK_PROPERTY
        } catch (ValidationException e) {
            errorString = property.errors
            statusCode = StatusCodeRB.FAILED_PROPERTY
            respond property.errors
            return
        }
        def responseData = [
                'property' : property,
                'code': statusCode.getCode(),
                'status': statusCode.getDescription(),
                'error': errorString
        ]
        render responseData as JSON //, [status: CREATED, view: "show"]
    }

    @Transactional
    def update(Property property) {
        if (property == null) {
            render status: NOT_FOUND
            return
        }
        if (property.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond property.errors
            return
        }

        try {
            propertyService.save(property)
        } catch (ValidationException e) {
            respond property.errors
            return
        }

        respond property, [status: OK, view: "show"]
    }

    @Transactional
    def delete(Long id) {
        if (id == null) {
            render status: NOT_FOUND
            return
        }

        propertyService.delete(id)

        render status: NO_CONTENT
    }
}
