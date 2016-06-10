package org.allurefw.model;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.nio.file.Path;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.StringEndsWith.endsWith;
import static ru.yandex.qatools.matchers.nio.PathMatchers.hasFilesCount;

/**
 * @author charlie (Dmitry Baev).
 */
public class AllureModelTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test(expected = IllegalStateException.class)
    public void shouldNotBeAbleToInstanceAllureConstants() throws Exception {
        new AllureConstants();
    }

    @Test(expected = IllegalStateException.class)
    public void shouldNotBeAbleToInstanceAllureUtils() throws Exception {
        new AllureUtils();
    }

    @Test
    public void shouldGenerateTestCaseJsonFileWithNameTheRightSuffix() throws Exception {
        String fileName = AllureUtils.generateTestCaseJsonFileName();
        assertThat(fileName, endsWith(AllureConstants.TEST_CASE_JSON_FILE_SUFFIX));
    }

    @Test
    public void shouldGenerateTestGroupJsonFileNameWithTheRightSuffix() throws Exception {
        String fileName = AllureUtils.generateTestGroupJsonFileName();
        assertThat(fileName, endsWith(AllureConstants.TEST_GROUP_JSON_FILE_SUFFIX));
    }

    @Test
    public void shouldWriteTestCaseResult() throws Exception {
        Path outputDir = folder.newFolder().toPath();
        TestCaseResult testCaseResult = new TestCaseResult();
        AllureUtils.write(testCaseResult, outputDir);
        assertThat(outputDir, hasFilesCount(1, AllureConstants.TEST_CASE_JSON_FILE_GLOB));
    }

    @Test
    public void shouldWriteTestGroupResult() throws Exception {
        Path outputDir = folder.newFolder().toPath();
        TestGroupResult testGroupResult = new TestGroupResult();
        AllureUtils.write(testGroupResult, outputDir);
        assertThat(outputDir, hasFilesCount(1, AllureConstants.TEST_GROUP_JSON_FILE_GLOB));
    }

    @Test
    public void shouldNotFailIfNoSuchOutputDirectory() throws Exception {
        Path outputDir = folder.newFolder().toPath().resolve("unknown");
        TestCaseResult testCaseResult = new TestCaseResult();
        AllureUtils.write(testCaseResult, outputDir);
        assertThat(outputDir, hasFilesCount(1, AllureConstants.TEST_CASE_JSON_FILE_GLOB));
    }
}