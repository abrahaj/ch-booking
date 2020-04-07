package al.rb.booking

import grails.converters.JSON
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
class RatesController {

    RatesService ratesService

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond ratesService.list(params), model:[ratesCount: ratesService.count()]
    }

    def show(Long id) {
        log.info("REQUEST HERE " +request.getRemoteAddr())
        Rates rates = ratesService.get(id)
        log.info("RATE PLAN XML TO REQUEST " + rates.getXml())
        respond ratesService.get(id)
    }

    @Transactional
    def save() {
        def rates = request.getJSON()
        def responseData = null

        rates.each{ rate->
            def property = Property.findByBookingId(rate.property)
            def ratePlan = RatePlan.findByNameAndProperty(rate.ratePlan,property)
            def room = Room.findByRoomID(rate.room)
            def statusCode
            def errorString
            try {

            def rts = new Rates("target": rate.target, "startDate": rate.startDate, "endDate": rate.endDate,
                    "room"  : room, "ratePlan": ratePlan, "amountAfterTax": rate.amountAfterTax, "currencyCode": rate.currencyCode)
                rts.save(flush:true, failOnError:true)
                rts.locatorId = (int) rts.id
                rts.save(flush:true, failOnError:true)
                statusCode = StatusCodeRB.OK_RATE
            }catch(Exception e){
                errorString = e.getLocalizedMessage()
                statusCode = StatusCodeRB.FAILED_RATE
            }
            responseData = [
                    'rates' : rates,
                    'code': statusCode.getCode(),
                    'status': statusCode.getDescription(),
                    'error': errorString
            ]
        }
        render responseData as JSON
    }

    @Transactional
    def update(Rates rates) {
        if (rates == null) {
            render status: NOT_FOUND
            return
        }
        if (rates.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond rates.errors
            return
        }

        try {
            ratesService.save(rates)
        } catch (ValidationException e) {
            respond rates.errors
            return
        }

        respond rates, [status: OK, view:"show"]
    }

    @Transactional
    def delete(Long id) {
        if (id == null) {
            render status: NOT_FOUND
            return
        }

        ratesService.delete(id)

        render status: NO_CONTENT
    }
}
