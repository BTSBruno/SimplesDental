package br.com.brunots.simplesdental.beckendtest.configurations;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Configuration
public class JacksonConfiguration {

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
        objectMapper.setDefaultPropertyInclusion(
                JsonInclude.Value.construct(JsonInclude.Include.NON_NULL, JsonInclude.Include.ALWAYS));
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.registerModule(new SimpleModule().addSerializer(LocalDateTime.class, new CustomLocalDateTimeSerializer()));
        objectMapper.registerModule(new SimpleModule().addSerializer(Date.class, new CustomDateSerializer()));
        return objectMapper;
    }

    private static class CustomLocalDateTimeSerializer extends StdSerializer<LocalDateTime> {
        protected CustomLocalDateTimeSerializer() {
            super(LocalDateTime.class);
        }
        @Override
        public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider provider) throws IOException {
            gen.writeString(value.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        }
    }

    private static class CustomDateSerializer extends StdSerializer<Date> {
        protected CustomDateSerializer() {
            super(Date.class);
        }
        @Override
        public void serialize(Date value, JsonGenerator gen, SerializerProvider provider) throws IOException {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            gen.writeString(sdf.format(value));
        }
    }
}
