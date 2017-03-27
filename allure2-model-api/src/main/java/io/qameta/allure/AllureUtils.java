package io.qameta.allure;

import java.util.UUID;

/**
 * @author charlie (Dmitry Baev).
 */
public final class AllureUtils {

    AllureUtils() {
        throw new IllegalStateException("Do not instance");
    }

    public static String generateTestResultName() {
        return generateTestResultName(UUID.randomUUID().toString());
    }

    public static String generateTestResultName(String uuid) {
        return uuid + AllureConstants.TEST_RESULT_FILE_SUFFIX;
    }

    public static String generateTestResultContainerName() {
        return generateTestResultContainerName(UUID.randomUUID().toString());
    }

    public static String generateTestResultContainerName(String uuid) {
        return uuid + AllureConstants.TEST_RESULT_CONTAINER_FILE_SUFFIX;
    }
}
