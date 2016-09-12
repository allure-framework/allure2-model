package io.qameta.allure;

/**
 * @author charlie (Dmitry Baev).
 */
public final class AllureConstants {

    public static final String TEST_CASE_JSON_FILE_SUFFIX = "-testcase.json";

    public static final String TEST_CASE_JSON_FILE_GLOB = "*-testcase.json";

    public static final String TEST_GROUP_JSON_FILE_SUFFIX = "-testgroup.json";

    public static final String TEST_GROUP_JSON_FILE_GLOB = "*-testgroup.json";

    AllureConstants() {
        throw new IllegalStateException("Don't instance AllureUtils");
    }

}
