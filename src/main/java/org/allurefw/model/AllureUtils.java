package org.allurefw.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationIntrospector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.qatools.properties.PropertyLoader;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

import static org.allurefw.model.AllureConstants.TEST_CASE_JSON_FILE_SUFFIX;
import static org.allurefw.model.AllureConstants.TEST_GROUP_JSON_FILE_SUFFIX;

/**
 * @author Dmitry Baev charlie@yandex-team.ru
 *         Date: 20.01.14
 */
public final class AllureUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(AllureUtils.class);

    /**
     * Do not instance.
     */
    AllureUtils() {
        throw new IllegalStateException("Don't instance AllureUtils");
    }

    public static String generateTestCaseJsonFileName() {
        return String.format("%s%s", UUID.randomUUID().toString(), TEST_CASE_JSON_FILE_SUFFIX);
    }

    public static String generateTestGroupJsonFileName() {
        return String.format("%s%s", UUID.randomUUID().toString(), TEST_GROUP_JSON_FILE_SUFFIX);
    }

    public static void write(TestGroupResult testGroupResult, Path outputDirectory) {
        write(testGroupResult, generateTestGroupJsonFileName(), outputDirectory);
    }

    public static void write(TestCaseResult testCaseResult, Path outputDirectory) {
        write(testCaseResult, generateTestCaseJsonFileName(), outputDirectory);
    }

    private static void write(Object object, String fileName, Path outputDirectory) {
        try {
            Files.createDirectories(outputDirectory);
        } catch (IOException e) {
            LOGGER.error("Could not create output directory: {}", e);
        }

        Path file = outputDirectory.resolve(fileName);
        try (OutputStream outputStream = Files.newOutputStream(file)) {
            createMapper().writeValue(outputStream, object);
        } catch (IOException e) {
            LOGGER.error("Could not write results to {} file: {}", file, e);
        }
    }

    private static ObjectMapper createMapper() {
        ObjectMapper mapper = new ObjectMapper()
                .configure(MapperFeature.USE_WRAPPER_NAME_AS_PROPERTY_NAME, true)
                .setAnnotationIntrospector(new JaxbAnnotationIntrospector(TypeFactory.defaultInstance()))
                .setSerializationInclusion(JsonInclude.Include.NON_NULL);

        AllureConfig config = PropertyLoader.newInstance()
                .populate(AllureConfig.class);
        if (config.isIndentOutput()) {
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
        }
        return mapper;
    }

}