package com.epam.core.configuration.proxy;

import com.browserup.bup.BrowserUpProxyServer;
import com.browserup.bup.client.ClientUtil;
import com.browserup.bup.proxy.CaptureType;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.Proxy;

import java.net.Inet4Address;
import java.net.UnknownHostException;

@Slf4j
@UtilityClass
public final class SeleniumProxyConfigurator {

    public Proxy configureProxy(final BrowserUpProxyServer proxy) {
        Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);
        String hostIp = null;
        try {
            hostIp = Inet4Address.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            log.error(e.getMessage());
        }
        seleniumProxy.setHttpProxy(hostIp + ":" + proxy.getPort());
        seleniumProxy.setSslProxy(hostIp + ":" + proxy.getPort());
        proxy.enableHarCaptureTypes(CaptureType.RESPONSE_CONTENT);
        return seleniumProxy;
    }
}
