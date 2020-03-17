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
class HotelProductController {

    HotelProductService hotelProductService

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond hotelProductService.list(params), model:[hotelProductCount: hotelProductService.count()]
    }

    def show(Long id) {
        println "REQUEST HERE " +request.getRemoteAddr()
        HotelProduct hotelProduct = hotelProductService.get(id)
        log.info("HOTEL PRODUCT XML TO REQUEST " + hotelProduct.getXml())
        hotelProduct.ratesPlan.each { rp->
            RatePlan ratePlan = RatePlan.get(rp.id)
            log.info("RATE PLAN XML TO REQUEST " + ratePlan.getXml())
        }
        respond hotelProductService.get(id)
    }

    @Transactional
    def save(HotelProduct hotelProduct) {
        if (hotelProduct == null) {
            render status: NOT_FOUND
            return
        }
        if (hotelProduct.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond hotelProduct.errors
            return
        }

        try {
            hotelProductService.save(hotelProduct)
        } catch (ValidationException e) {
            respond hotelProduct.errors
            return
        }

        respond hotelProduct, [status: CREATED, view:"show"]
    }

    @Transactional
    def update(HotelProduct hotelProduct) {
        if (hotelProduct == null) {
            render status: NOT_FOUND
            return
        }
        if (hotelProduct.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond hotelProduct.errors
            return
        }

        try {
            hotelProductService.save(hotelProduct)
        } catch (ValidationException e) {
            respond hotelProduct.errors
            return
        }

        respond hotelProduct, [status: OK, view:"show"]
    }

    @Transactional
    def delete(Long id) {
        if (id == null) {
            render status: NOT_FOUND
            return
        }

        hotelProductService.delete(id)

        render status: NO_CONTENT
    }
}
