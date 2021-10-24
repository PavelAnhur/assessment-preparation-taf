package com.epam.core.configuration.proxy;

import com.browserup.bup.BrowserUpProxyServer;
import com.epam.core.configuration.property.ConfigurationManager;
import com.epam.core.utility.service.PortController;

public class BrowserUpProxyServerStarter {
    private static final int PORT = ConfigurationManager.configuration().proxyPort();

    public BrowserUpProxyServer startAndGetProxyServer() {
        BrowserUpProxyServer browserUpProxyServerProxyServer = new BrowserUpProxyServer();
        browserUpProxyServerProxyServer.setTrustAllServers(true);
        PortController.makePortAvailableIfOccupied(PORT);
        browserUpProxyServerProxyServer.start(PORT);
        return browserUpProxyServerProxyServer;
    }
}
