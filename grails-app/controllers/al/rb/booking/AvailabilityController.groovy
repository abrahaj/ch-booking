package al.rb.booking

import grails.plugin.springsecurity.annotation.Secured
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.CREATED
import static org.springframework.http.HttpStatus.NOT_FOUND
import static org.springframework.http.HttpStatus.NO_CONTENT
import static org.springframework.http.HttpStatus.OK
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY

import grails.gorm.transactions.ReadOnly
import grails.gorm.transactions.Transactional
@Secured('permitAll')
@ReadOnly
class AvailabilityController {

    AvailabilityService availabilityService

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond availabilityService.list(params), model:[availabilityCount: availabilityService.count()]
    }

    def show(Long id) {
        log.info("REQUEST HERE " +request.getRemoteAddr())
        Availability availability = availabilityService.get(id)
        log.info("AVAILABILITY XML TO REQUEST " + availability.getXml())
        respond availabilityService.get(id)
    }

    @Transactional
    def save(Availability availability) {
        if (availability == null) {
            render status: NOT_FOUND
            return
        }
        if (availability.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond availability.errors
            return
        }

        try {
            availabilityService.save(availability)
        } catch (ValidationException e) {
            respond availability.errors
            return
        }

        respond availability, [status: CREATED, view:"show"]
    }

    @Transactional
    def update(Availability availability) {
        if (availability == null) {
            render status: NOT_FOUND
            return
        }
        if (availability.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond availability.errors
            return
        }

        try {
            availabilityService.save(availability)
        } catch (ValidationException e) {
            respond availability.errors
            return
        }

        respond availability, [status: OK, view:"show"]
    }

    @Transactional
    def delete(Long id) {
        if (id == null) {
            render status: NOT_FOUND
            return
        }

        availabilityService.delete(id)

        render status: NO_CONTENT
    }
}
