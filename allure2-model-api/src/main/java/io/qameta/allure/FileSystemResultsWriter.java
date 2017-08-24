package io.qameta.allure;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.model.Allure2ModelJackson;
import io.qameta.allure.model.TestResult;
import io.qameta.allure.model.TestResultContainer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

import static io.qameta.allure.AllureUtils.generateTestResultContainerName;
import static io.qameta.allure.AllureUtils.generateTestResultName;
import static java.nio.file.StandardOpenOption.CREATE_NEW;

/**
 * @author charlie (Dmitry Baev).
 */
public class FileSystemResultsWriter implements AllureResultsWriter {

    private final Path outputDirectory;

    private final ObjectMapper mapper;

    public FileSystemResultsWriter(Path outputDirectory) {
        this.outputDirectory = outputDirectory;
        this.mapper = Allure2ModelJackson.createMapper();
    }

    // Based on https://superuser.com/a/748264
    private String unsafeCharactersPattern = "[^0-9a-zA-Zа-яА-Я_\\-.]+";

    @Override
    public void write(TestResult testResult) {
        final String testResultName = Objects.isNull(testResult.getUuid())
                ? generateTestResultName()
                : generateTestResultName(testResult.getUuid());

        final String safeTestResultName = testResultName.replaceAll(unsafeCharactersPattern, "_");

        createDirectories(outputDirectory);
        Path file = outputDirectory.resolve(safeTestResultName);
        try (OutputStream os = Files.newOutputStream(file, CREATE_NEW)) {
            mapper.writeValue(os, testResult);
        } catch (IOException e) {
            throw new AllureResultsWriteException("Could not write Allure test result", e);
        }
    }

    @Override
    public void write(TestResultContainer testResultContainer) {
        final String testResultContainerName = Objects.isNull(testResultContainer.getUuid())
                ? generateTestResultContainerName()
                : generateTestResultContainerName(testResultContainer.getUuid());

        final String safeTestResultContainerName = testResultContainerName.replaceAll(unsafeCharactersPattern, "_");

        createDirectories(outputDirectory);
        Path file = outputDirectory.resolve(safeTestResultContainerName);
        try (OutputStream os = Files.newOutputStream(file, CREATE_NEW)) {
            mapper.writeValue(os, testResultContainer);
        } catch (IOException e) {
            throw new AllureResultsWriteException("Could not write Allure test result container", e);
        }
    }

    @Override
    public void write(String source, InputStream attachment) {
        createDirectories(outputDirectory);
        Path file = outputDirectory.resolve(source);
        try (InputStream is = attachment) {
            Files.copy(is, file);
        } catch (IOException e) {
            throw new AllureResultsWriteException("Could not write Allure attachment", e);
        }
    }

    private void createDirectories(Path directory) {
        try {
            Files.createDirectories(directory);
        } catch (IOException e) {
            throw new AllureResultsWriteException("Could not create Allure results directory", e);
        }
    }
}
