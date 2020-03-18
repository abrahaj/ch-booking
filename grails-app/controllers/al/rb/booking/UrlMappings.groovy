package al.rb.booking

class UrlMappings {

    static mappings = {
        delete "/$controller/$id(.$format)?"(action: "delete")
        get "/$controller(.$format)?"(action: "index")
        get "/$controller/$id(.$format)?"(action: "show")
        post "/$controller(.$format)?"(action: "save")
        put "/$controller/$id(.$format)?"(action: "update")
        patch "/$controller/$id(.$format)?"(action: "patch")
        get "/$controller/$id(.$format)/rooms?"(action: "rooms")
        "/"(controller: 'application', action: 'index')
        "/index"(controller: 'application', action: 'index')
        "/login/auth"(controller: 'login', action: 'auth')
        "500"(view: '/error')
        "404"(view: '/notFound')
        "401"(view: '/notFound')
    }
}
