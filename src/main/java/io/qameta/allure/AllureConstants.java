package io.qameta.allure;

/**
 * @author @author charlie (Dmitry Baev baev@qameta.io)
 * @since 1.0-BETA1
 */
public final class AllureConstants {

    public static final String TEST_CASE_JSON_FILE_SUFFIX = "-testcase.json";

    public static final String TEST_CASE_JSON_FILE_GLOB = "*-testcase.json";

    public static final String TEST_GROUP_JSON_FILE_SUFFIX = "-testgroup.json";

    public static final String TEST_GROUP_JSON_FILE_GLOB = "*-testgroup.json";

    public static final String TEST_RUN_JSON_FILE_SUFFIX = "-testrun.json";

    public static final String TEST_RUN_JSON_FILE_GLOB = "*-testrun.json";

    AllureConstants() {
        throw new IllegalStateException("Don't instance AllureUtils");
    }

}
