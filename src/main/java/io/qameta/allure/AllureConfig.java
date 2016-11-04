package io.qameta.allure;

import ru.qatools.properties.DefaultValue;
import ru.qatools.properties.Property;
import ru.qatools.properties.Resource;

import java.nio.file.Path;

/**
 * @author @author charlie (Dmitry Baev baev@qameta.io)
 * @since 1.0-BETA1
 */
@SuppressWarnings("unused")
@Resource.Classpath("allure.properties")
public interface AllureConfig {

    @DefaultValue("target/allure-results")
    @Property("allure.results.directory")
    Path getResultsDirectory();

    @DefaultValue("false")
    @Property("allure.results.indentOutput")
    boolean isIndentOutput();

}