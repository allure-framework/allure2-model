package io.qameta.allure.model;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.util.stream.Stream;

/**
 * @author charlie (Dmitry Baev).
 */
public class StageDeserializer extends StdDeserializer<Stage> {
    protected StageDeserializer() {
        super(Stage.class);
    }

    @Override
    public Stage deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String value = p.readValueAs(String.class);
        return Stream.of(Stage.values())
                .filter(status -> status.value().equalsIgnoreCase(value))
                .findAny()
                .orElse(null);
    }
}
