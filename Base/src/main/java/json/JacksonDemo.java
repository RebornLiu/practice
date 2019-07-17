package json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;

public class JacksonDemo {

    public static void main(String[] args) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        //null字段不输出
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        //忽略多余的字段
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);


        Message message = new Message();
        message.setId("ididid");

        String json = mapper.writeValueAsString(message);
        System.out.println(json);

        String orginJson = "{\"id\":\"ididid\", \"age\" : 12}";

        Message message1 = mapper.readValue(orginJson, Message.class);
        System.out.println(message1);

    }
}
