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
class MealPlanTypeController {

    MealPlanTypeService mealPlanTypeService

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond mealPlanTypeService.list(params), model:[mealPlanTypeCount: mealPlanTypeService.count()]
    }

    def show(Long id) {
        respond mealPlanTypeService.get(id)
    }

    @Transactional
    def save(MealPlanType mealPlanType) {
        if (mealPlanType == null) {
            render status: NOT_FOUND
            return
        }
        if (mealPlanType.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond mealPlanType.errors
            return
        }

        try {
            mealPlanTypeService.save(mealPlanType)
        } catch (ValidationException e) {
            respond mealPlanType.errors
            return
        }

        respond mealPlanType, [status: CREATED, view:"show"]
    }

    @Transactional
    def update(MealPlanType mealPlanType) {
        if (mealPlanType == null) {
            render status: NOT_FOUND
            return
        }
        if (mealPlanType.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond mealPlanType.errors
            return
        }

        try {
            mealPlanTypeService.save(mealPlanType)
        } catch (ValidationException e) {
            respond mealPlanType.errors
            return
        }

        respond mealPlanType, [status: OK, view:"show"]
    }

    @Transactional
    def delete(Long id) {
        if (id == null) {
            render status: NOT_FOUND
            return
        }

        mealPlanTypeService.delete(id)

        render status: NO_CONTENT
    }
}
