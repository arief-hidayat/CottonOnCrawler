package com.hida.crawler.cottonon



class CrawlingCottonOnJob {
    static triggers = {
        cron name:'cronTrigger', cronExpression: '0 0 0/6 * * ?'
//      simple repeatInterval: 5000l // execute job once in 5 seconds
    }
//    cronExpression: "s m h D M W Y"
//    | | | | | | `- Year [optional]
//    | | | | | `- Day of Week, 1-7 or SUN-SAT, ?
//    | | | | `- Month, 1-12 or JAN-DEC
//    | | | `- Day of Month, 1-31, ?
//    | | `- Hour, 0-23
//    | `- Minute, 0-59
//    `- Second, 0-59

    def crawlerService
    def execute() {
        println "execute CrawlingCottonOnJob"
        [RubiSalePage, CottonOnKidsSalePage, CottonOnMenSalePage, CottonOnTypoSalePage, CottonOnWomenSalePage].each {
            println "execute job for ${it}"
            crawlerService.getSaleProductItems(it)
        }
    }
}
