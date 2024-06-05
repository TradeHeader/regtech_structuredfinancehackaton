package com.tradeheader;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.finhackbcn.CreditQualifyApplication;

public class CreditQualifyHandler implements RequestHandler<String, String> {

    @Override
    public String handleRequest(String request, Context context) {
        context.getLogger().log("Processing request " + request);
        String taxonomy = "";
        try {
            taxonomy = new CreditQualifyApplication().run(request);
            context.getLogger().log(String.format("Request %s returned %s", context.getAwsRequestId(), taxonomy));
            return taxonomy;
        } catch (JsonProcessingException e) {
            return "[ERROR] - " + e.getMessage();
        }
    }
}
