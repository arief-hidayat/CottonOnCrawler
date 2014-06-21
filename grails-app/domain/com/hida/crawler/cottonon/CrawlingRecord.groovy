package com.hida.crawler.cottonon

class CrawlingRecord {
     // id is the batch id
    String url
    Date requestDate

    static mapping = {
        url index: "crawling_req_idx1", sort: "desc"
    }
}
