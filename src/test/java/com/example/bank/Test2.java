package com.example.bank;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Test2 {



    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String str = "{\"accessControlRoles\":[\"aa72\"],\"accessControl\":\"RESTRICTED\",\"visibility\":\"RESTRICTED\"," +
                "\"endpointConfig\":{\"endpoint_type\":\"http\"," +
                "\"sandbox_endpoints\":{\"url\":\"<<sandbox-URL>>\"," +
                "\"enabled\":false},\"endpoint_security\":{\"production\":{\"password\":\"<<password>>\",\"type\":\"BASIC\"" +
                ",\"enabled\":\"true\",\"username\":\"<<username>>\"}},\"production_endpoints\":{\"url\":\"<<production-URL>>\"}}," +
                "\"name\":\"<<api-name>>\",\"context\":\"<<context>>\",\"tag\":\"<<tag>>\",\"version\":\"<<version>>\"," +
                "\"visibleRoles\":[\"aa72\"]}";


        JsonNode node = mapper.valueToTree(str);
        System.out.println(node.elements());
        getKeysInJsonUsingJsonParser(str);
    }
    public static List<String> getKeysInJsonUsingJsonParser(String json) throws JsonParseException, IOException {

        List<String> keys = new ArrayList<>();
        JsonFactory factory = new JsonFactory();
        JsonParser jsonParser = factory.createParser(json);
        while (!jsonParser.isClosed()) {
            //System.out.println("getCurrentName :"+jsonParser.getCurrentName());
            //System.out.println("currentToken : "+jsonParser.currentToken());
            //System.out.println("CurrentValue : "+jsonParser.getCurrentValue());

            if (jsonParser.nextToken() == JsonToken.FIELD_NAME) {
                keys.add((jsonParser.getCurrentName()));
            }else {
                System.out.println(">>>>"+jsonParser.getCurrentValue());
                System.out.println(jsonParser);
            }
        }

        System.out.println(keys);
        return keys;
    }
}
