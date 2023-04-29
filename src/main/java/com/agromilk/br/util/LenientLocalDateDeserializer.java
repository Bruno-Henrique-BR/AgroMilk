package com.agromilk.br.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LenientLocalDateDeserializer extends JsonDeserializer<LocalDate> {
    @Override
    public LocalDate deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String dateValue = jsonParser.getText();
        if (dateValue == null || dateValue.trim().isEmpty()) {
            return null; // Retorna null para valores vazios ou nulos
        }

        // Define os padrões de formatação de datas
        DateTimeFormatter[] formatters = {
                DateTimeFormatter.ofPattern("dd/MM/yyyy"),
                DateTimeFormatter.ofPattern("yyyy-MM-dd")
        };

        // Tenta realizar a desserialização utilizando os padrões de formatação
        for (DateTimeFormatter formatter : formatters) {
            try {
                return LocalDate.parse(dateValue, formatter);
            } catch (Exception ignored) {
                // Ignora exceções e tenta o próximo padrão de formatação
            }
        }

        throw new IllegalArgumentException("Formato de data inválido: " + dateValue);
    }
}
