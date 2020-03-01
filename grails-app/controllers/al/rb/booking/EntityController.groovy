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

@Secured(["ROLE_ADMIN"])
@ReadOnly
class EntityController {

    EntityService entityService

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond entityService.list(params), model:[entityCount: entityService.count()]
    }

    def show(Long id) {
        respond entityService.get(id)
    }

    @Transactional
    def save(Entity entity) {
        if (entity == null) {
            render status: NOT_FOUND
            return
        }
        if (entity.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond entity.errors
            return
        }

        try {
            entityService.save(entity)
        } catch (ValidationException e) {
            respond entity.errors
            return
        }

        respond entity, [status: CREATED, view:"show"]
    }

    @Transactional
    def update(Entity entity) {
        if (entity == null) {
            render status: NOT_FOUND
            return
        }
        if (entity.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond entity.errors
            return
        }

        try {
            entityService.save(entity)
        } catch (ValidationException e) {
            respond entity.errors
            return
        }

        respond entity, [status: OK, view:"show"]
    }

    @Transactional
    def delete(Long id) {
        if (id == null) {
            render status: NOT_FOUND
            return
        }

        entityService.delete(id)

        render status: NO_CONTENT
    }
}
