package com.epam.core.configuration.proxy;

import lombok.experimental.UtilityClass;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.proxy.CaptureType;
import org.openqa.selenium.Proxy;

import java.net.Inet4Address;
import java.net.UnknownHostException;

@UtilityClass
public final class SeleniumProxyConfigurator {

    public Proxy configureProxy(final BrowserMobProxyServer proxy) throws UnknownHostException {
        Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);
        String hostIp = Inet4Address.getLocalHost().getHostAddress();
        seleniumProxy.setHttpProxy(hostIp + ":" + proxy.getPort());
        seleniumProxy.setSslProxy(hostIp + ":" + proxy.getPort());
        proxy.enableHarCaptureTypes(CaptureType.RESPONSE_CONTENT);
        return seleniumProxy;
    }
}
