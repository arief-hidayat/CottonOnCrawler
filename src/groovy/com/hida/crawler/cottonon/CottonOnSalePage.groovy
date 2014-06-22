package com.hida.crawler.cottonon

import geb.Page
import geb.navigator.Navigator
import grails.util.Holders

/**
 * Created by hida on 14/6/2014.
 */
class CottonOnSalePage extends Page {
    static final String LOAD_MORE_SELECTOR = "#loadmore", PRODUCT_LIST_SELECTOR = "#product_list"
//    static url = "http://asia.cottonon.com/shop/rubi/sale"
    static at = { $(PRODUCT_LIST_SELECTOR) }

//    static content = {
//        productList { $("#product_list") }
//    }
//
//    public static void main(String[] a) {
//        System.setProperty("webdriver.firefox.bin", "C:\\Program Files (x86)\\Mozilla Firefox\\Firefox.exe")
//        Browser.drive {
//            to RubiSalePage
//            List<SaleProductItem> items = getSaleProductItemInfo()
//            println "size ${items.size()}"
//            for(SaleProductItem item : items) {
//                println """
//                    ${item.id} - ${item.title}
//                    product ${item.imgUrl}
//                    url ${item.productUrl}
//                    Price ${item.priceNow}. was ${item.priceWas}
//"""
//            }
//        }
//    }


    List<SaleProductItem> getSaleProductItemInfo(Comparator<SaleProductItem> sorter){
        displayAllProducts()

        List<SaleProductItem> list = new ArrayList<>()
        for(Navigator product : $(PRODUCT_LIST_SELECTOR).find(".product").iterator()) {
            list.add getSaleProductItem(product)
        }
        list.sort(sorter ?: new RubiItemComparator())
    }


    protected void filterProduct() {
        throw new RuntimeException("to be implemented in next iteration")
    }

    protected void displayAllProducts() {
        Navigator moreBtn = $(LOAD_MORE_SELECTOR);
//        while(!moreBtn.jquery.is(":hidden")) {
//            moreBtn.click()
//            println "click"
//            waitFor(10, 0.5, { true })
////            waitFor moreProductsAreLoaded()
//        }
        try {
            while(true) {
                moreBtn.click()
                println "click"
                waitFor(10, 0.5, { true }) // try 10 seconds every 0.5s
            }
        } catch(Exception _) {

        }
    }

    protected Closure moreProductsAreLoaded() {
        int count = $(PRODUCT_LIST_SELECTOR).find(".product").size()
        println "count = ${count}"
        return {
            int currCount = $(PRODUCT_LIST_SELECTOR).find(".product").size()
            println "currCount = ${currCount}"
            currCount != count
        }
    }

    protected SaleProductItem getSaleProductItem(Navigator product) {
        SaleProductItem item = new SaleProductItem()
        item.title = trimAndToUpper product.parent()?.jquery?.attr("title")
        item.productUrl = product.parent()?.jquery?.attr("href")
        if(!item.productUrl.startsWith("http")) item.productUrl ="http://asia.cottonon.com" + item.productUrl
        item.productId = product.jquery.attr("rel")
        item.imgUrl = product.find("img").jquery.attr("src")
        item.priceNow = toDouble("${product.find('.now').jquery.text()}")
        item.priceWas = toDouble("${product.find('.was').jquery.text()}")
        if(product.find(".more_colours")) item.otherColorHasSale = true

        item.finalized(Holders.config.hida?.conf?.sgd2idr ?: 10_000.00, Holders.config.hida?.conf?.rubi?.addoncost ?: 50_000)
        return item
    }
    private String trimAndToUpper(String val) {
        if(!val) return null;
        val = val.trim();
        if(val.endsWith(".")) val = val.substring(0, val.length() - 1)
        val.trim().toUpperCase()
    }

    protected double toDouble(String val) {
        try {
            if(val) return Double.parseDouble(val.substring(2))
//            if(val) return Double.parseDouble(val.replace(/([^\d]*)([\d]+[.][\d]+)([^\d]*)/, '$2'))
        } catch(Exception e) {
           println "failed to convert [${val}] to double"
        }
        return 0;
    }
}
