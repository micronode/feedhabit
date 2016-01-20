import org.openqa.selenium.remote.DesiredCapabilities
import org.openqa.selenium.remote.RemoteWebDriver

driver = {
    new RemoteWebDriver(new URL("http://${System.env['DOCKER_HOST'] ?: 'localhost'}:4444/wd/hub"),
            DesiredCapabilities.chrome())
}
