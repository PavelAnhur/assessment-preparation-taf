package com.epam.core.configuration.proxy;

import com.epam.core.utility.service.PortController;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import net.lightbody.bmp.BrowserMobProxyServer;

@Slf4j
@UtilityClass
public class BrowserMobProxyServerStarter {
    private static final int PORT = 8080;

    public BrowserMobProxyServer startAndGetProxyServer() {
        BrowserMobProxyServer browserMobProxyServer = new BrowserMobProxyServer();
        browserMobProxyServer.setTrustAllServers(true);
        PortController.makePortAvailableIfOccupied(PORT);
        browserMobProxyServer.start();
        return browserMobProxyServer;
    }
}
