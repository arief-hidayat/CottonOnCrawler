package com.hida.crawler.cottonon

import geb.Browser
import geb.Page
import grails.util.Holders

import static grails.async.Promises.*

class TriggerController {
    def mailService
    
    private static final String DEFAULT_EMAIL_RECIPIENT = Holders.config.hida?.defaultrecipient ?: "mr.arief.hidayat@gmail.com"

    def job() {
        try {
            CrawlingCottonOnJob.triggerNow()
        } catch(Exception e) {
            while(e.cause) { e = e.cause }
            println "Failed on CrawlingCottonOnJob. ${e.getMessage()}"
        }
        renderMessage("Background job is running. Please wait few minutes.")
    }
    def show() {
        Class<Page> pageClass = RubiSalePage
        switch (params.type) {
            case "women"    : pageClass = CottonOnWomenSalePage; break;
            case "men"      : pageClass = CottonOnMenSalePage; break;
            case "typo"     : pageClass = CottonOnTypoSalePage; break;
            case "kids"    : pageClass = CottonOnKidsSalePage; break;
            default: pageClass = RubiSalePage
        }
        render(view: "_cottononSale", model: [products : getSaleProductItems(pageClass)])
//        render(template: "cottononSaleEmail", model: [products : getSaleProductItems(pageClass)])
    }

    def crawlerService
    protected List<SaleProductItem> getSaleProductItems(Class<Page> pageClass) {
        crawlerService.getSaleProductItems(pageClass)
    }

    protected void sendAllSaleItemsToEmail(Class<Page> pageClass, String clientEmail, String emailSubject = "Rubi Sale!!") {
        if(clientEmail) {
            List<String> subscriberEmails = CrawlerSubscriber.findAllByCrawlerPageName(pageClass.simpleName)*.email ?: []
            println "sending to email ${clientEmail} with bcc to ${subscriberEmails}"
            mailService.sendMail {
                to clientEmail
                if(subscriberEmails) bcc subscriberEmails.toArray()
                subject emailSubject
//                html g.render(template: "cottononSaleItem", collection: getSaleProductItems(pageClass), var: 'item')
                html g.render(template: "cottononSaleEmail", model: [products : getSaleProductItems(pageClass)])
            }
        }
    }

    def rubi() {
        String email = params.email ?: DEFAULT_EMAIL_RECIPIENT
        sendAllSaleItemsToEmail(RubiSalePage, email )
        renderMessage "Sale Info will be sent to $email in few minutes."
    }

    def women() {
        String email = params.email ?: DEFAULT_EMAIL_RECIPIENT
        sendAllSaleItemsToEmail(CottonOnWomenSalePage, email )
        renderMessage "Sale Info will be sent to $email in few minutes."
    }

    def men() {
        String email = params.email ?: DEFAULT_EMAIL_RECIPIENT
        sendAllSaleItemsToEmail(CottonOnMenSalePage, email )
        renderMessage "Sale Info will be sent to $email in few minutes."
    }

    def typo() {
        String email = params.email ?: DEFAULT_EMAIL_RECIPIENT
        sendAllSaleItemsToEmail(CottonOnTypoSalePage, email )
        renderMessage "Sale Info will be sent to $email in few minutes."
    }

    def kids() {
        String email = params.email ?: DEFAULT_EMAIL_RECIPIENT
        sendAllSaleItemsToEmail(CottonOnKidsSalePage, email)
        renderMessage "Sale Info will be sent to $email in few minutes."
    }

    protected def renderMessage(String msg) {
        render(template: "message", model: [ message : msg])
    }

    def msg() {
        renderMessage "Please check your email"
    }

    def index() {
        sendAllSaleItemsToEmail(RubiSalePage, DEFAULT_EMAIL_RECIPIENT )
//        System.setProperty("webdriver.firefox.bin", "C:\\Program Files (x86)\\Mozilla Firefox\\Firefox.exe")
//
//        StringBuilder sb = new StringBuilder()
//
//        Browser.drive({
//            to RubiSalePage
//
//            List<SaleProductItem> items = getSaleProductItemInfo()
//            println "size ${items.size()}"
//            for(SaleProductItem item : items) {
//                sb.append """
//                    ${item.id} - ${item.title}
//                    image ${item.imgUrl}
//                    url ${item.productUrl}
//                    Price ${item.priceNow}. was ${item.priceWas}
//                    ============================================
//"""
//            }
//
//        mailService.sendMail {
//            to DEFAULT_EMAIL_RECIPIENT
//            subject "Test: Rubi Sale!!"
//            html g.render(template: "cottononSaleItem", collection: items, var: 'item')
//        }
//        }).quit()

        render "Please check your email"
    }

    def email() {
        mailService.sendMail {
            to "mr.arief.hidayat@gmail.com"
            subject "Test: Rubi Sale!!"
            body "test test test"
        }
    }
}
