/*
Copyright 2024 TradeHeader SL

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/
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
