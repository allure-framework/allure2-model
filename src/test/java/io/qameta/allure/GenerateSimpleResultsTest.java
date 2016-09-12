package io.qameta.allure;

import io.qameta.allure.model.Attachment;
import io.qameta.allure.model.Label;
import io.qameta.allure.model.Parameter;
import io.qameta.allure.model.TestCaseResult;
import io.qameta.allure.model.TestStepResult;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import ru.qatools.properties.PropertyLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.function.Supplier;

/**
 * @author charlie (Dmitry Baev).
 */
public class GenerateSimpleResultsTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void shouldWriteToJson() throws Exception {
        AllureConfig config = PropertyLoader.newInstance()
                .populate(AllureConfig.class);
        for (int i = 0; i < 10; i++) {
            AllureUtils.write(randomTestCase(), config.getResultsDirectory());
        }
    }

    private static TestCaseResult randomTestCase() {
        return new TestCaseResult()
                .withName(randomName())
                .withId(UUID.randomUUID().toString())
                .withTestGroupId(UUID.randomUUID().toString())
                .withDescription(randomDescription())
                .withSteps(randomSteps())
                .withAttachments(randomAttachments())
                .withParameters(randomParameters())
                .withStart((long) randomPositive(0, 100))
                .withStop((long) randomPositive(100, 1000));
    }

    private static List<TestStepResult> randomSteps() {
        return randomListOf(GenerateSimpleResultsTest::randomStep);
    }

    private static TestStepResult randomStep() {
        return new TestStepResult()
                .withName(randomName())
                .withDescription(randomDescription())
                .withSteps(randomSteps())
                .withAttachments(randomAttachments())
                .withParameters(randomParameters())
                .withStart((long) randomPositive(0, 100))
                .withStop((long) randomPositive(100, 1000));
    }

    private static List<Parameter> randomParameters() {
        return randomListOf(GenerateSimpleResultsTest::randomParameter);
    }

    private static Parameter randomParameter() {
        return new Parameter()
                .withName(randomName())
                .withValue(randomName());
    }

    private static List<Attachment> randomAttachments() {
        return randomListOf(GenerateSimpleResultsTest::randomAttachment);
    }

    private static Attachment randomAttachment() {
        return new Attachment()
                .withName(randomName())
                .withSource(randomName())
                .withSource(randomName());
    }

    private static Label randomLabel() {
        return new Label()
                .withName(randomName())
                .withValue(randomName());
    }

    private static <T> List<T> randomListOf(Supplier<T> supplier) {
        List<T> results = new ArrayList<>();
        int count = randomPositive(0, 10) < 3 ? 0 : randomPositive(0, 3);
        for (int i = 0; i < count; i++) {
            results.add(supplier.get());
        }
        return results;
    }

    private static String randomName() {
        return RandomStringUtils.randomAlphabetic(randomPositive(4, 10));
    }

    private static String randomDescription() {
        return RandomStringUtils.randomAlphabetic(randomPositive(10, 100));
    }

    private static int randomPositive(int from, int to) {
        return new Random().nextInt(to) + from;
    }

    private static boolean randomBoolean() {
        return new Random().nextBoolean();
    }
}