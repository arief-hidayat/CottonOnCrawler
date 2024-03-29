package com.hida.crawler.cottonon

import geb.spock.GebSpec


/**
 * Created by hida on 15/6/2014.
 */
class CottonOnSalePageSpec extends GebSpec  {

    def "test normal case for rubi sale"() {
        // still hard code
        given:
        System.setProperty("webdriver.firefox.bin", "C:\\Program Files (x86)\\Mozilla Firefox\\Firefox.exe")
        when:
        to RubiSalePage

        List<SaleProductItem> items = getSaleProductItemInfo()
        then:
        println "size ${items.size()}"
        for(SaleProductItem item : items) {
            println """
                    ${item.productId} - ${item.title}
                    image ${item.imgUrl}
                    url ${item.productUrl}
                    Price ${item.priceNow}. was ${item.priceWas}
                    ============================================
"""
        }
        items.size() == 42
    }
}
