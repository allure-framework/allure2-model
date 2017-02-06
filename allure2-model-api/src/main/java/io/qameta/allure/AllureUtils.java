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
        return UUID.randomUUID().toString() + AllureConstants.TEST_RESULT_FILE_SUFFIX;
    }

    public static String generateTestResultContainerName() {
        return UUID.randomUUID().toString() + AllureConstants.TEST_RESULT_CONTAINER_FILE_SUFFIX;
    }
}
