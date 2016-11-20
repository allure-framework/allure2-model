package io.qameta.allure.model;

import java.util.List;

/**
 * @author charlie (Dmitry Baev).
 */
public interface WithSteps {

    List<TestStepResult> getSteps();

}
