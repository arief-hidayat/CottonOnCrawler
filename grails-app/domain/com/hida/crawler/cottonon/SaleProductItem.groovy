package com.hida.crawler.cottonon

/**
 * Created by hida on 15/6/2014.
 */
class SaleProductItem {
    String productId, title, productUrl, imgUrl
    double priceWas, priceNow
    boolean otherColorHasSale = false

    // derived
    double salePercentage = 0, suggestedIdrPrice = 1

    static mapping = {
        title index: "sale_product_item_idx"
        salePercentage index: "sale_product_item_idx2", sort: "desc"
        batchId index: "sale_product_item_idx1"
    }
    Long batchId
    Date lastUpdated

    void finalized(double rate, double addOnCost) {
        if(priceWas > 0 && priceNow > 0) {
            salePercentage =  toTwoDecimal((priceWas - priceNow) * 100.0/ priceWas)
        }
        suggestedIdrPrice = toTwoDecimal((priceNow * rate) + addOnCost)
    }

    double toTwoDecimal(double val) {
        ((int) (val * 100.0)) / 100.0
    }

    def beforeInsert = { lastUpdated = new Date() }
    def beforeUpdate = { lastUpdated = new Date() }

    static def removeAll(long batchId) {
        executeUpdate("delete from SaleProductItem where batchId=:batchId", [batchId : batchId])
    }
}
