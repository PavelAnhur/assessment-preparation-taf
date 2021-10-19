package com.epam.steps;

import com.epam.core.configuration.property.ConfigurationManager;
import de.sstoehr.harreader.HarReader;
import de.sstoehr.harreader.HarReaderException;
import de.sstoehr.harreader.model.HarEntry;
import lombok.extern.slf4j.Slf4j;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.core.har.Har;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

import static com.codeborne.selenide.Selenide.open;

@Slf4j
public class BrowserMobProxySteps {
    private static final String MIME_TYPE = "image/png";
    private final BrowserMobProxyServer proxy;

    public BrowserMobProxySteps(BrowserMobProxyServer browserMobProxyServer) {
        this.proxy = browserMobProxyServer;
    }

    public BrowserMobProxySteps openHomePage() {
        open(ConfigurationManager.configuration().homePage());
        return this;
    }

    public void createHarFile(final String harPathname) {
        Har har = proxy.getHar();
        try {
            har.writeTo(new File(harPathname));
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    public long getPngFilesCountFromHarFile(final String harPathname) {
        HarReader harReader = new HarReader();
        long pngFilesCount = 0;
        try {
            List<HarEntry> entries = harReader.readFromFile(new File(harPathname))
                    .getLog()
                    .getEntries();

            pngFilesCount = entries.stream()
                    .filter(entry -> Objects.equals(entry.getResponse().getContent().getMimeType(), MIME_TYPE))
                    .count();
        } catch (HarReaderException e) {
            log.error(e.getMessage());
        }
        log.info("amount of png files: '{}'", pngFilesCount);
        return pngFilesCount;
    }
}
