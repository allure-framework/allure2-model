package io.qameta.allure.model;

import java.util.List;

/**
 * @author charlie (Dmitry Baev).
 */
public interface WithBefore {

    List<TestBeforeResult> getBefores();

}