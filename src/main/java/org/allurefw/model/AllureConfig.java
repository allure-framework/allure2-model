package org.allurefw.model;

import ru.qatools.properties.DefaultValue;
import ru.qatools.properties.Property;
import ru.qatools.properties.Resource;

import java.nio.file.Path;

/**
 * @author Artem Eroshenko eroshenkoam@yandex-team.ru
 *         Date: 12/13/13
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