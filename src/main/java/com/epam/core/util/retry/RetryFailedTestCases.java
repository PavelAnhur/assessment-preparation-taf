package com.epam.core.util.retry;

import lombok.extern.slf4j.Slf4j;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

@Slf4j
public class RetryFailedTestCases implements IRetryAnalyzer {
    private static final int MAX_RETRY_COUNT = 2;
    private int retryCount = 0;

    @Override
    public boolean retry(ITestResult iTestResult) {
        if (retryCount < MAX_RETRY_COUNT) {
            retryCount++;
            log.info("Retrying {} and the count is {}", iTestResult.getName(), retryCount);
            return true;
        }
        return false;
    }
}
