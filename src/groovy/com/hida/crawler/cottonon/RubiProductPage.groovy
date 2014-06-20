package com.hida.crawler.cottonon

import geb.Page
import geb.navigator.Navigator

/**
 * Created by hida on 20/6/2014.
 */
class RubiProductPage extends Page {
    static at = { $('.product-details .colours .selectBox')} // make sure selectBox is loaded

    SaleProductItem getDetail(boolean checkOtherColors) {

    }

    def getProductName() {
        $(".product-details h2")?.jquery?.text()?.trim()
    }

    def getOldPrice() {
        def price = $('.product-details .variations').find("li")[0]
        price.find('.old-price')?.jquery?.text()?.trim()
    }

    def getSalePrice() {
        def price = $('.product-details .variations').find("li")[0]
        price.find('.sale-price')?.jquery?.text()?.trim()
    }

    def getColour() {
       $('.product-details .colours a .selectBox-label')?.jquery?.text()?.trim()
    }

    def getUrlForOtherAvailableColors() {
        def colors = []
        for( Navigator color : $(".colours .selectBox")[0].find("option").iterator()) {
            def url = color.jquery.data("slug")
            if(url) colors << "http://asia.cottonon.com" + url
        }
        colors
    }

    def getAvailableSizes() {
        def sizes  = []
        for(Navigator size : $(".sizes ").find("input:enabled[name='option2']").iterator()) {
            sizes << size.jquery.val()
        }
        sizes
    }
}
