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
class RatePlanController {

    RatePlanService ratePlanService

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond ratePlanService.list(params), model:[ratePlanCount: ratePlanService.count()]
    }

    def show(Long id) {
        println "REQUEST HERE " +request.getRemoteAddr()
        RatePlan ratePlan = ratePlanService.get(id)
        log.info("RATE PLAN XML TO REQUEST " + ratePlan.getXml())
        respond ratePlanService.get(id)
    }

    @Transactional
    def save(RatePlan ratePlan) {
        if (ratePlan == null) {
            render status: NOT_FOUND
            return
        }
        if (ratePlan.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond ratePlan.errors
            return
        }

        try {
            ratePlanService.save(ratePlan)
        } catch (ValidationException e) {
            respond ratePlan.errors
            return
        }

        respond ratePlan, [status: CREATED, view:"show"]
    }

    @Transactional
    def update(RatePlan ratePlan) {
        if (ratePlan == null) {
            render status: NOT_FOUND
            return
        }
        if (ratePlan.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond ratePlan.errors
            return
        }

        try {
            ratePlanService.save(ratePlan)
        } catch (ValidationException e) {
            respond ratePlan.errors
            return
        }

        respond ratePlan, [status: OK, view:"show"]
    }

    @Transactional
    def delete(Long id) {
        if (id == null) {
            render status: NOT_FOUND
            return
        }

        ratePlanService.delete(id)

        render status: NO_CONTENT
    }
}
