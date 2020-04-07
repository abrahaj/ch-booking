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
class HotelProductController {

    HotelProductService hotelProductService

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond hotelProductService.list(params), model:[hotelProductCount: hotelProductService.count()]
    }

    def show(Long id) {
        log.info("REQUEST HERE " +request.getRemoteAddr())
        HotelProduct hotelProduct = hotelProductService.get(id)
        log.info("HOTEL PRODUCT XML TO REQUEST " + hotelProduct.getXml())
        hotelProduct.ratesPlan.each { rp->
            RatePlan ratePlan = RatePlan.get(rp.id)
            log.info("RATE PLAN XML TO REQUEST " + ratePlan.getXml())
        }
        respond hotelProductService.get(id)
    }

    @Transactional
    def save() {
        def hotelProducts = request.getJSON()
        // println(request.getJSON())
        def responseData = null
        hotelProducts.each { hotelProduct ->
            def mealPlanType = []
            hotelProduct.mealPlan.each { m ->
                def ml = MealPlanType.findByCode(m.toInteger())
                mealPlanType << ml.id
            }

            def property = Property.findByBookingId(hotelProduct.property)
            def ratesPlan = hotelProduct.ratesPlan//.first().target
            def rateplan = []
            ratesPlan.each { rp ->
                rateplan << [target: rp.target, hotelCode: rp.hotelCode, property: Property.findByCode(rp.hotelCode.toInteger()), ratePlanNotifType: rp.ratePlanNotifType, name: rp.name, ratePlanID: rp.ratePlanID]
            }

            def statusCode
            def errorString
            HotelProduct hp
            try {
                hp = new HotelProduct(primaryLangID: hotelProduct.primaryLangID, target: hotelProduct.target,
                        property: property, roomTypes: hotelProduct.roomTypes, productNotifType: hotelProduct.productNotifType,
                        ratesPlan: ratesPlan, mealPlan: mealPlanType)
                hp.save(flush: true, failOnError: true)
                statusCode = StatusCodeRB.OK_PRODUCT
            } catch (Exception e) {
                errorString = e.getLocalizedMessage()
                statusCode = StatusCodeRB.FAILED_PRODUCT
            }
            responseData = [
                    'hotelProduct' : hotelProduct,
                    'hp':hp,
                    'code': statusCode.getCode(),
                    'status': statusCode.getDescription(),
                    'error': errorString
            ]
        }

        render responseData as JSON
        //respond hotelProduct, [status: CREATED, view:"show"]
    }
//    def save(HotelProduct hotelProduct) {
//        println(hotelProduct)
//        MealPlanType mealPlanType =[]
//        hotelProduct.mealPlan.each{m->
//            mealPlanType << m.id
//        }
//        if (hotelProduct == null) {
//            render status: NOT_FOUND
//            return
//        }
//        def statusCode
//        def errorString
//        if (hotelProduct.hasErrors()) {
//            errorString = hotelProduct.errors
//            statusCode = StatusCodeRB.FAILED_PRODUCT
//            transactionStatus.setRollbackOnly()
//            respond hotelProduct.errors
//            return
//        }
//
//        try {
//            hotelProductService.save(hotelProduct)
//            statusCode = StatusCodeRB.OK_PRODUCT
//        } catch (ValidationException e) {
//            errorString = hotelProduct.errors
//            statusCode = StatusCodeRB.FAILED_PRODUCT
//            respond hotelProduct.errors
//            return
//        }
//        def responseData = [
//                'hotelProduct' : hotelProduct,
//                'code': statusCode.getCode(),
//                'status': statusCode.getDescription(),
//                'error': errorString
//        ]
//        render responseData as JSON
//        //respond hotelProduct, [status: CREATED, view:"show"]
//    }

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
