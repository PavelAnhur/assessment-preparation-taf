package com.epam.core.configuration.proxy;

import com.browserup.bup.BrowserUpProxyServer;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@UtilityClass
public class BrowserUpProxyServerSingleton {
    private static final ThreadLocal<BrowserUpProxyServer> PROXY_SERVER = new ThreadLocal<>();

    public BrowserUpProxyServer getInstance() {
        if (null == PROXY_SERVER.get()) {
            PROXY_SERVER.set(new BrowserUpProxyServerStarter().startAndGetProxyServer());
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
