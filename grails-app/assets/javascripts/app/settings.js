//= require core.settings
//= require_self

App.dt.config.table = {
    CrawlerSubscriber : { columns : [ { "data": "crawlerPageName" }, { "data": "email" }, { "data": "count" }]}
//    asset : {
//        columns : [ { "data": "code" }, { "data": "name" }, { "data": "assetType" }, { "data": "status" }, { "data": "locationCd" }],
//        order : [[1, 'asc']]
//    }
};
//
App.dt.config.customUrl = {
////        asset : {
////            url : "only for custom",
////            extraParams : function(request) { }
////        }
};