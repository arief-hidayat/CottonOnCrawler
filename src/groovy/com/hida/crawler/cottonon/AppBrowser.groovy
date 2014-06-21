package com.hida.crawler.cottonon

import geb.Browser

/**
 * Created by hida on 21/6/2014.
 */
class AppBrowser {
    private static Browser _browser;
    static Browser getBrowser() {
        if(!_browser) _browser = new Browser()
        _browser
    }
}
