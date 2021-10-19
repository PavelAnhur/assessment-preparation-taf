package com.epam.core.utility.service;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.ServerSocket;

@Slf4j
@UtilityClass
public class PortController {
    private static final String KILL_NODE_COMMAND = "taskkill /F /IM node.exe";

    public void makePortAvailableIfOccupied(final int port) {
        if (!isPortFree(port)) {
            try {
                Runtime.getRuntime().exec(KILL_NODE_COMMAND);
            } catch (IOException e) {
                log.warn("Couldn't execute runtime command, message: {}", e.getMessage());
            }
        }
    }

    private boolean isPortFree(final int port) {
        boolean isFree = true;
        try (ServerSocket ignored = new ServerSocket(port)) {
            log.info("Specified port - {} is available and ready to use", port);
        } catch (Exception e) {
            isFree = false;
            log.warn("Specified port - {} is occupied by some process, process will be terminated", port);
        }
        return isFree;
    }
}
