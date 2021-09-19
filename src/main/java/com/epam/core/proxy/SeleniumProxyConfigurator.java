package com.epam.core.proxy;

import lombok.SneakyThrows;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.proxy.CaptureType;
import org.openqa.selenium.Proxy;

import java.net.Inet4Address;

public final class SeleniumProxyConfigurator {

    private SeleniumProxyConfigurator() {
    }

    @SneakyThrows
    public static Proxy configureProxy(BrowserMobProxyServer proxy) {
        Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);
        String hostIp = Inet4Address.getLocalHost().getHostAddress();
        seleniumProxy.setHttpProxy(hostIp + ":" + proxy.getPort());
        seleniumProxy.setSslProxy(hostIp + ":" + proxy.getPort());
        proxy.enableHarCaptureTypes(CaptureType.RESPONSE_CONTENT);
        return seleniumProxy;
    }
}