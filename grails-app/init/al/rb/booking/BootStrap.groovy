package al.rb.booking

import grails.plugin.springsecurity.SpringSecurityService

class BootStrap {
    SpringSecurityService springSecurityService
    def init = { servletContext ->
        if (User.list().size() < 1) {
            def userRole = new Role(authority: 'ROLE_ADMIN').save()
            def me = new User(username: 'realbookers', password: springSecurityService.encodePassword('54321')).save()
            //UserRole is not being saved
            def urole = new UserRole(user: me, role: userRole)
            urole.save()
        }
    }
    def destroy = {
    }
}
