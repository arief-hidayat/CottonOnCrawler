package com.hida.crawler.cottonon

import geb.spock.GebSpec


/**
 * Created by hida on 15/6/2014.
 */
class CottonOnProductPageSpec extends GebSpec  {

    def "test normal case for product page"() {
        // still hard code
        given:
        System.setProperty("webdriver.firefox.bin", "C:\\Program Files (x86)\\Mozilla Firefox\\Firefox.exe")
        when:
        go "http://asia.cottonon.com/shop/product/rylan-peep-lemon/"
        then:
        at CottonOnProductPage
        productName == "RYLAN PEEP"
        oldPrice == "S\$29.95"
        salePrice == "S\$15.00"
        colour == "LEMON"
        availableSizes == ["37", "38"]
    }


    def "test normal case for other cotton on product page"() {
        // still hard code
        given:
        System.setProperty("webdriver.firefox.bin", "C:\\Program Files (x86)\\Mozilla Firefox\\Firefox.exe")
        when:
        go "http://asia.cottonon.com/shop/product/festival-crew-white/"
        then:
        at CottonOnProductPage
        productName == "festival crew"
        oldPrice == "S\$14.95"
        salePrice == "S\$10.00"
        colour == "WHITE"
        availableSizes == ["XXS", "XS", "S", "M", "L", "XL"]
    }
}
