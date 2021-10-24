package com.epam.steps;

import com.browserup.bup.BrowserUpProxyServer;
import com.browserup.harreader.model.Har;
import com.epam.core.configuration.property.ConfigurationManager;
import de.sstoehr.harreader.HarReader;
import de.sstoehr.harreader.HarReaderException;
import de.sstoehr.harreader.model.HarEntry;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

import static com.codeborne.selenide.Selenide.open;

@Slf4j
public class BrowserUpMobProxySteps {
    private static final String MIME_TYPE = "image/png";
    private final BrowserUpProxyServer proxy;

    public BrowserUpMobProxySteps(final BrowserUpProxyServer browserUpProxyServer) {
        this.proxy = browserUpProxyServer;
    }

    public BrowserUpMobProxySteps openHomePage() {
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
