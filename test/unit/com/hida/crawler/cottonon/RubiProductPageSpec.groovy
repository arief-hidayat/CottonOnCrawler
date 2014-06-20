package com.hida.crawler.cottonon

import geb.spock.GebSpec


/**
 * Created by hida on 15/6/2014.
 */
class RubiProductPageSpec extends GebSpec  {

    def "test normal case for product page"() {
        // still hard code
        given:
        System.setProperty("webdriver.firefox.bin", "C:\\Program Files (x86)\\Mozilla Firefox\\Firefox.exe")
        when:
        go "http://asia.cottonon.com/shop/product/rylan-peep-lemon/"
        then:
        at RubiProductPage
        productName == "RYLAN PEEP"
        oldPrice == "S\$29.95"
        salePrice == "S\$15.00"
        colour == "LEMON"
        availableSizes == ["37", "38"]
    }
}
