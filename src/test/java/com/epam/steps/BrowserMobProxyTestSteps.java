package com.epam.steps;

import com.epam.core.config.ConfigurationProvider;
import de.sstoehr.harreader.HarReader;
import de.sstoehr.harreader.HarReaderException;
import de.sstoehr.harreader.model.HarEntry;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.core.har.Har;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.util.List;

@Slf4j
public class BrowserMobProxyTestSteps {
    private final WebDriver driver;
    private final BrowserMobProxyServer proxy;

    public BrowserMobProxyTestSteps(WebDriver driver, BrowserMobProxyServer proxy) {
        this.driver = driver;
        this.proxy = proxy;
    }

    public BrowserMobProxyTestSteps openHomePage() {
        driver.get(ConfigurationProvider.getHomePageUrl());
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
                    .filter(entry -> entry.getResponse().getContent().getMimeType().equals("image/png"))
                    .count();

        } catch (HarReaderException e) {
            log.error(e.getMessage());
        }
        log.info("amount of png files: '{}'", pngFilesCount);
        return pngFilesCount;
    }
}
