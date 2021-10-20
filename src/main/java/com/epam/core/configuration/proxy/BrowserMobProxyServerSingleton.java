package com.epam.core.configuration.proxy;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import net.lightbody.bmp.BrowserMobProxyServer;

@Slf4j
@UtilityClass
public class BrowserMobProxyServerSingleton {
    private static final ThreadLocal<BrowserMobProxyServer> PROXY_SERVER = new ThreadLocal<>();

    public BrowserMobProxyServer getInstance() {
        if (null == PROXY_SERVER.get()) {
            PROXY_SERVER.set(new BrowserMobProxyServerStarter().startAndGetProxyServer());
            log.info("********* proxy server has been created ************");
        }
        log.info("*********** get the created proxy server instance *********");
        return PROXY_SERVER.get();
    }

    public void stopProxyServer() {
        if (null != PROXY_SERVER.get()) {
            PROXY_SERVER.get().stop();
        }
    }
}
