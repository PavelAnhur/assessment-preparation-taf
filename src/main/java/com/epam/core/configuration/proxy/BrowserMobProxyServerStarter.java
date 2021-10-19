package com.epam.core.configuration.proxy;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import net.lightbody.bmp.BrowserMobProxyServer;

@Slf4j
@UtilityClass
public class BrowserMobProxyServerStarter {
    private static final int PORT = 8080;

    public BrowserMobProxyServer start() {
        BrowserMobProxyServer browserMobProxyServer = new BrowserMobProxyServer();
        browserMobProxyServer.setTrustAllServers(true);
        browserMobProxyServer.start(PORT);
        return browserMobProxyServer;
    }
}
