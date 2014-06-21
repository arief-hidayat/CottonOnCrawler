package com.hida.crawler.cottonon

import geb.Browser
import geb.Page
import grails.transaction.Transactional

import static grails.async.Promises.task

@Transactional
class CrawlerService {

    private static final Long ONE_HOUR = 60 * 60 * 1_000

    protected boolean canUsePreviousCrawlingRecord(CrawlingRecord crawling) {
        if(!crawling) return false
        long current = new Date().time, previousCrawling = crawling.requestDate.time
        return (current - previousCrawling) < (6 * ONE_HOUR) // less than 6 hours
    }

    List<SaleProductItem> getSaleProductItems(Class<Page> pageClass) {
        String url = pageClass.url
        CrawlingRecord crawling = CrawlingRecord.findByUrl(url)
        if(canUsePreviousCrawlingRecord(crawling)) {
            return SaleProductItem.findAllByBatchId(crawling.id, [ sort : "salePercentage", order : "desc"])
        } else {
            def retrieval = task {
                List<SaleProductItem> list = new ArrayList<>()
                Browser.drive(AppBrowser.browser, {
                    to pageClass
                    list = getSaleProductItemInfo()
                })
                list
            }
            List<SaleProductItem> items = retrieval.get()
            saveSaleProductItems(url, items)
            return items
        }
    }

    @Transactional
    protected void saveSaleProductItems(String url, List<SaleProductItem> items) {
        CrawlingRecord batch = CrawlingRecord.findByUrl(url)
        if(!batch) {
            batch =  new CrawlingRecord(url: url, requestDate: new Date())
        } else {
            SaleProductItem.removeAll(batch.id)
        }
        batch.requestDate = new Date()
        batch.save(failOnError: true)

        for(SaleProductItem item : items) {
            item.batchId = batch.id
            item.save(failOnError: true)
        }
    }
}
