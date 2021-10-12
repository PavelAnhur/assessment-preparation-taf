package com.epam.steps;

import com.epam.core.config.PropertyDataReader;
import de.sstoehr.harreader.HarReader;
import de.sstoehr.harreader.HarReaderException;
import de.sstoehr.harreader.model.HarEntry;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.core.har.Har;

import java.io.File;
import java.util.List;
import java.util.Objects;

import static com.codeborne.selenide.Selenide.open;

@Slf4j
public class BrowserMobProxySteps {
    private static final String MIME_TYPE = "image/png";
    private final BrowserMobProxyServer proxy;

    public BrowserMobProxySteps(final BrowserMobProxyServer proxyServer) {
        this.proxy = proxyServer;
    }

    public BrowserMobProxySteps openHomePage() {
        open(PropertyDataReader.getPropertyValue("homePage"));
        return this;
    }

    @SneakyThrows
    public void createHarFile(final String harPathname) {
        Har har = proxy.getHar();
        File harFile = new File(harPathname);
        har.writeTo(harFile);
    }

    @SneakyThrows
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
