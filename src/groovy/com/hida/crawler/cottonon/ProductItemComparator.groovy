package com.hida.crawler.cottonon

/**
 * Created by hida on 21/6/2014.
 */
public class ProductItemComparator {

}

class RubiItemComparator implements Comparator<SaleProductItem> {
    @Override
    int compare(SaleProductItem item1, SaleProductItem item2) {
        return item2.salePercentage <=> item1.salePercentage
    }
}
