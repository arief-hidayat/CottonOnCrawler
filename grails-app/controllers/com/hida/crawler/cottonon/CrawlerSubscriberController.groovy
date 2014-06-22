package com.hida.crawler.cottonon




import org.springframework.http.HttpStatus

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class CrawlerSubscriberController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def createForm() {
        println "inside create form"
        render(model: [crawlerSubscriberInstance: new CrawlerSubscriber(params)], view: "_partialCreate")
    }
    def editForm(CrawlerSubscriber crawlerSubscriberInstance) {
        render(model: [crawlerSubscriberInstance: crawlerSubscriberInstance], view: "_partialEdit")
    }
    def showForm(CrawlerSubscriber crawlerSubscriberInstance) {
        render(model: [crawlerSubscriberInstance: crawlerSubscriberInstance], view: "_partialShow") //
    }

    def deleteJSON() {
        CrawlerSubscriber crawlerSubscriberInstance = CrawlerSubscriber.get(params.id)
        if(crawlerSubscriberInstance == null) {
            renderJsonMessage(message(code: 'default.not.found.message', args: [message(code: 'crawlerSubscriber.label', default: 'CrawlerSubscriber'), params.id]), params, NOT_FOUND)
            println "item not found"
            return
        }
        try {
            crawlerSubscriberInstance.delete flush: true
            renderJsonMessage(message(code: 'default.deleted.message', args: [message(code: 'crawlerSubscriber.label', default: 'CrawlerSubscriber'), crawlerSubscriberInstance.id]), params, OK)
            println "deleted successfully"
        } catch(Exception e) {
            log.error("Failed to delete CrawlerSubscriber. params ${params}", e)
            renderJsonMessage(message(code: 'default.not.deleted.message', args: [message(code: 'crawlerSubscriber.label', default: 'CrawlerSubscriber'), crawlerSubscriberInstance.id]), params, INTERNAL_SERVER_ERROR)
            println "item couldn't be deleted"
        }
    }

    private def renderJsonMessage(String msg, def parameter, HttpStatus status) {
        render(status: status, contentType: "application/json;  charset=utf-8") {
            [message : msg, params : parameter]
        }
    }

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond new CrawlerSubscriber()
    }

    def show(CrawlerSubscriber crawlerSubscriberInstance) {
        if(params._partial) {
            render(model: [crawlerSubscriberInstance: crawlerSubscriberInstance], view: "_partialShow")
            return
        }
        respond crawlerSubscriberInstance
    }

    def create() {
        if(params._partial) {
            render(model: [crawlerSubscriberInstance: new CrawlerSubscriber(params)], view: "_partialCreate")
            return
        }
        respond new CrawlerSubscriber(params)
    }

    @Transactional
    def save(CrawlerSubscriber crawlerSubscriberInstance) {
        if (crawlerSubscriberInstance == null) {
            notFound()
            return
        }

        if (crawlerSubscriberInstance.hasErrors()) {
            if(params._partial) {
                response.status = 412
                render(model: [crawlerSubscriberInstance: crawlerSubscriberInstance], view: "_partialCreate")
                return
            }
            respond crawlerSubscriberInstance.errors, view:'create'
            return
        }



        String msg = message(code: 'default.created.message', args: [message(code: 'crawlerSubscriber.label', default: 'CrawlerSubscriber'), crawlerSubscriberInstance.id])
        try {
            crawlerSubscriberInstance.save flush:true, failOnError: true
            if(params._partial) {
                render(model: [crawlerSubscriberInstance: crawlerSubscriberInstance], view: "_partialShow")
                return
            }
        } catch(Exception e) {
            if(params._partial) {
                response.status = 500
                if (!crawlerSubscriberInstance.hasErrors()) {
                    flash.message = e.getMessage()
                }
                render(model: [crawlerSubscriberInstance: crawlerSubscriberInstance], view: "_message")
                return
            }
        }

        request.withFormat {
            form multipartForm {
                flash.message = msg
                redirect crawlerSubscriberInstance
            }
            '*' { respond crawlerSubscriberInstance, [status: CREATED] }
        }
    }

    def edit(CrawlerSubscriber crawlerSubscriberInstance) {
        if(params._partial) {
            render(model: [crawlerSubscriberInstance: crawlerSubscriberInstance], view: "_partialEdit")
            return
        }
        respond crawlerSubscriberInstance
    }

    @Transactional
    def update(CrawlerSubscriber crawlerSubscriberInstance) {
        if (crawlerSubscriberInstance == null) {
            notFound()
            return
        }

        if (crawlerSubscriberInstance.hasErrors()) {
            if(params._partial) {
                response.status = 412
                render(model: [crawlerSubscriberInstance: crawlerSubscriberInstance], view: "_partialEdit")
                return
            }
            respond crawlerSubscriberInstance.errors, view:'edit'
            return
        }
        String msg = message(code: 'default.updated.message', args: [message(code: 'CrawlerSubscriber.label', default: 'CrawlerSubscriber'), crawlerSubscriberInstance.id])
        try {
            crawlerSubscriberInstance.save flush:true, failOnError: true
            if(params._partial) {
                render(model: [crawlerSubscriberInstance: crawlerSubscriberInstance], view: "_partialShow")
                return
            }
        } catch(Exception e) {
            if(params._partial) {
                response.status = 500
                if (!crawlerSubscriberInstance.hasErrors()) {
                    flash.message = e.getMessage()
                }
                render(model: [crawlerSubscriberInstance: crawlerSubscriberInstance], view: "_message")
                return
            }
        }

        request.withFormat {
            form multipartForm {
                flash.message = msg
                redirect crawlerSubscriberInstance
            }
            '*'{ respond crawlerSubscriberInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(CrawlerSubscriber crawlerSubscriberInstance) {

        if (crawlerSubscriberInstance == null) {
            notFound()
            return
        }

        String msg = message(code: 'default.deleted.message', args: [message(code: 'CrawlerSubscriber.label', default: 'CrawlerSubscriber'), crawlerSubscriberInstance.id])
        try {
            crawlerSubscriberInstance.delete flush:true
            if(params._partial) {
                render(model: [crawlerSubscriberInstance: crawlerSubscriberInstance], view: "_partialCreate")
                return
            }
        } catch(Exception e) {
            if(params._partial) {
                response.status = 500
                if (!crawlerSubscriberInstance.hasErrors()) {
                    flash.message = e.getMessage()
                }
                render(model: [crawlerSubscriberInstance: crawlerSubscriberInstance], view: "_message")
                return
            }
        }
        request.withFormat {
            form multipartForm {
                flash.message = msg
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        String msg = message(code: 'default.not.found.message', args: [message(code: 'crawlerSubscriber.label', default: 'CrawlerSubscriber'), params.id])
        if(params._partial) {
            render(status: NOT_FOUND, text: msg)
            return
        }
        request.withFormat {
            form multipartForm {
                flash.message = msg
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
