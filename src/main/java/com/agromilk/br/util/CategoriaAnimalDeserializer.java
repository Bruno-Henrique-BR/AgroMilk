package com.agromilk.br.util;

import com.agromilk.br.entity.CategoriaAnimal;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class CategoriaAnimalDeserializer extends JsonDeserializer<CategoriaAnimal> {

    @Override
    public CategoriaAnimal deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String value = p.getText();
        return CategoriaAnimal.fromString(value);
    }
}
