package io.egen.api.serializer;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class AuDateSerializer extends JsonSerializer<Date> {
    
	@Override
    public void serialize(Date value, JsonGenerator gen, SerializerProvider provider) throws IOException, JsonProcessingException {
      DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
      gen.writeString(formatter.format(value));
    }

	
  }