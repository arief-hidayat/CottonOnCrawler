package com.hida.crawler.cottonon

class CrawlerSubscriber {

    String crawlerPageName
    String userName
    String email
    long count = 0

    static constraints = {
        userName nullable: true
        email unique: "crawlerPageName"
        crawlerPageName inList: [RubiSalePage.simpleName, CottonOnWomenSalePage.simpleName, CottonOnTypoSalePage.simpleName,
                                 CottonOnMenSalePage.simpleName, CottonOnKidsSalePage.simpleName]
    }
}
