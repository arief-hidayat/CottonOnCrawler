import com.hida.crawler.cottonon.AppBrowser

class BootStrap {

    def init = { servletContext ->
        System.setProperty("webdriver.firefox.bin", "C:\\Program Files (x86)\\Mozilla Firefox\\Firefox.exe")
    }
    def destroy = {
        println "quit browser... before destroying app"
        AppBrowser.browser.quit()
    }
}
