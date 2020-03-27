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
class GuestRoomController {

    GuestRoomService guestRoomService

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond guestRoomService.list(params), model:[guestRoomCount: guestRoomService.count()]
    }

    def show(Long id) {
        println "REQUEST HERE " +request.getRemoteAddr()
        GuestRoom guestRoom = guestRoomService.get(id)
        log.info("HERE IS THE XML TO REQUEST " + guestRoom.getXml())
        respond guestRoomService.get(id)
    }

    @Transactional
    def save(GuestRoom guestRoom) {
        if (guestRoom == null) {
            render status: NOT_FOUND
            return
        }
        def statusCode
        def errorString
        if (guestRoom.hasErrors()) {
            errorString = guestRoom.errors
            statusCode = StatusCodeRB.FAILED_ROOM
            transactionStatus.setRollbackOnly()
            respond guestRoom.errors
            return
        }

        try {
            if(guestRoom.property == null){
                errorString = guestRoom.errors
                statusCode = StatusCodeRB.FAILED_ROOM
                transactionStatus.setRollbackOnly()
                respond guestRoom.errors
                return
            }else {
                guestRoomService.save(guestRoom)
                statusCode = StatusCodeRB.OK_ROOM
            }
        } catch (ValidationException e) {
            errorString = guestRoom.errors
            statusCode = StatusCodeRB.FAILED_ROOM
            respond guestRoom.errors
            return
        }
        def responseData = [
                'guestRoom' : guestRoom,
                'code': statusCode.getCode(),
                'status': statusCode.getDescription(),
                'error': errorString
        ]
        render responseData as JSON

       // respond guestRoom, [status: CREATED, view:"show"]
    }

    @Transactional
    def update(GuestRoom guestRoom) {
        if (guestRoom == null) {
            render status: NOT_FOUND
            return
        }
        if (guestRoom.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond guestRoom.errors
            return
        }

        try {
            guestRoomService.save(guestRoom)
        } catch (ValidationException e) {
            respond guestRoom.errors
            return
        }

        respond guestRoom, [status: OK, view:"show"]
    }

    @Transactional
    def delete(Long id) {
        if (id == null) {
            render status: NOT_FOUND
            return
        }

        guestRoomService.delete(id)

        render status: NO_CONTENT
    }
}
