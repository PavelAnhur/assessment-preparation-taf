package com.epam.core.configuration.proxy;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.proxy.CaptureType;
import org.openqa.selenium.Proxy;

import java.net.Inet4Address;
import java.net.UnknownHostException;

@Slf4j
@UtilityClass
public final class SeleniumProxyConfigurator {

    public Proxy configureProxy(final BrowserMobProxyServer proxy) {
        Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);
        String hostIp = null;
        try {
            hostIp = Inet4Address.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            log.error(e.getMessage());
        }
        seleniumProxy.setHttpProxy(hostIp + ":" + proxy.getPort());
        seleniumProxy.setSslProxy(hostIp + ":" + proxy.getPort());
        proxy.enableHarCaptureTypes(CaptureType.RESPONSE_CONTENT, CaptureType.REQUEST_CONTENT);
        return seleniumProxy;
    }
}
