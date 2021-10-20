package com.epam.core.configuration.proxy;

import com.epam.core.configuration.property.ConfigurationManager;
import com.epam.core.utility.service.PortController;
import net.lightbody.bmp.BrowserMobProxyServer;

public class BrowserMobProxyServerStarter {
    private static final int PORT = ConfigurationManager.configuration().proxyPort();

    public BrowserMobProxyServer startAndGetProxyServer() {
        BrowserMobProxyServer browserMobProxyServer = new BrowserMobProxyServer();
        browserMobProxyServer.setTrustAllServers(true);
        PortController.makePortAvailableIfOccupied(PORT);
        browserMobProxyServer.start();
        return browserMobProxyServer;
    }
}
