package io.qameta.allure;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

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

    public static final String ATTACHMENT_FILE_SUFFIX = "-attachment";

    public static final Charset ATTACHMENT_ENCODING = StandardCharsets.UTF_8;

    AllureConstants() {
        throw new IllegalStateException("Don't instance AllureUtils");
    }

}
