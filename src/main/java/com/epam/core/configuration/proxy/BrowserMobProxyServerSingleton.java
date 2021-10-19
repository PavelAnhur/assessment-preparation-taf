package com.epam.core.configuration.proxy;

import lombok.experimental.UtilityClass;
import net.lightbody.bmp.BrowserMobProxyServer;

@UtilityClass
public class BrowserMobProxyServerSingleton {
    private static final ThreadLocal<BrowserMobProxyServer> PROXY_SERVER = new ThreadLocal<>();

    public BrowserMobProxyServer getInstance() {
        if (null == PROXY_SERVER.get()) {
            PROXY_SERVER.set(BrowserMobProxyServerStarter.startAndGetProxyServer());
        }
        return PROXY_SERVER.get();
    }

    public void stopProxyServer() {
        if (null != PROXY_SERVER.get()) {
            PROXY_SERVER.get().stop();
        }
    }
}
