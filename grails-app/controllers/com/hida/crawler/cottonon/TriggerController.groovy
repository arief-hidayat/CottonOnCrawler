package com.hida.crawler.cottonon

import geb.Browser
import geb.Page
import static grails.async.Promises.*

class TriggerController {
    def mailService

    def job() {
        CrawlingCottonOnJob.triggerNow()
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
        render(template: "cottononSaleItem", collection: getSaleProductItems(pageClass), var: 'item')
    }

    def crawlerService
    protected List<SaleProductItem> getSaleProductItems(Class<Page> pageClass) {
        crawlerService.getSaleProductItems(pageClass)
    }

    protected void sendAllSaleItemsToEmail(Class<Page> pageClass, String clientEmail, String emailSubject = "Rubi Sale!!") {
        if(clientEmail) {
            mailService.sendMail {
                to clientEmail
                subject emailSubject
                html g.render(template: "cottononSaleItem", collection: getSaleProductItems(pageClass), var: 'item')
            }
        }
    }

    def rubi() {
        String email = params.email ?: "niayohan@gmail.com"
        sendAllSaleItemsToEmail(RubiSalePage, email )
        renderMessage "Sale Info will be sent to $email in few minutes."
    }

    def women() {
        String email = params.email ?: "niayohan@gmail.com"
        sendAllSaleItemsToEmail(CottonOnWomenSalePage, email )
        renderMessage "Sale Info will be sent to $email in few minutes."
    }

    def men() {
        String email = params.email ?: "niayohan@gmail.com"
        sendAllSaleItemsToEmail(CottonOnMenSalePage, email )
        renderMessage "Sale Info will be sent to $email in few minutes."
    }

    def typo() {
        String email = params.email ?: "niayohan@gmail.com"
        sendAllSaleItemsToEmail(CottonOnTypoSalePage, email )
        renderMessage "Sale Info will be sent to $email in few minutes."
    }

    def kids() {
        String email = params.email ?: "niayohan@gmail.com"
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
        sendAllSaleItemsToEmail(RubiSalePage, "niayohan@gmail.com" )
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
//            to "niayohan@gmail.com"
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
