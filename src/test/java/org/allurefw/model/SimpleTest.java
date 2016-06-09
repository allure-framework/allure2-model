package org.allurefw.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationIntrospector;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.function.Supplier;

/**
 * @author charlie (Dmitry Baev).
 */
public class SimpleTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    private Path resultFile = Paths.get("/Users/charlie/projects/allure2-model/target/simple.json");

    @Test
    public void shouldWriteToJson() throws Exception {
        ObjectMapper mapper = new ObjectMapper()
                .configure(MapperFeature.USE_WRAPPER_NAME_AS_PROPERTY_NAME, true)
                .setAnnotationIntrospector(new JaxbAnnotationIntrospector(TypeFactory.defaultInstance()))
                .enable(SerializationFeature.INDENT_OUTPUT)
                .setSerializationInclusion(JsonInclude.Include.NON_NULL);

        try (OutputStream output = Files.newOutputStream(resultFile)) {
            mapper.writeValue(output, randomTestCase());
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
        return randomListOf(SimpleTest::randomStep);
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
        return randomListOf(SimpleTest::randomParameter);
    }

    private static Parameter randomParameter() {
        return new Parameter()
                .withName(randomName())
                .withValue(randomName());
    }

    private static List<Attachment> randomAttachments() {
        return randomListOf(SimpleTest::randomAttachment);
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
