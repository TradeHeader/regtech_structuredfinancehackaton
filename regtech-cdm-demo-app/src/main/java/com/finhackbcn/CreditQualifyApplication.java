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
package com.finhackbcn;

import cdm.event.common.TradeState;
import cdm.product.qualification.functions.GetStructuredProductsTaxonomy;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.regnosys.rosetta.common.serialisation.RosettaObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CreditQualifyApplication extends CDMApplication{

    private static final Logger LOGGER = LoggerFactory.getLogger(CreditQualifyApplication.class);
    private static final ObjectMapper mapper = RosettaObjectMapper.getNewRosettaObjectMapper();

    @Inject
    GetStructuredProductsTaxonomy creditQualifierFunc;

    public String run(String body) throws JsonProcessingException {
        TradeState trade = mapper.readValue(body, TradeState.class);
        return qualifyCredit(trade);
    }

    @Override
    public void runApp() {

        Path inputPath = Paths.get("src/test/resources/com/finhackbcn/samples");

        LOGGER.info("Reading tradeStates from " + inputPath.toAbsolutePath());
        LOGGER.info("-------------------------------------------------");

        try {
            Files.walk(inputPath)
                    .filter(Files::isRegularFile)
                    .forEach( path -> {
                        String filename = path.getFileName().toString();
                        try {
                            TradeState trade = mapper.readValue(path.toUri().toURL(), TradeState.class);
                            String taxonomy = qualifyCredit(trade);
                            LOGGER.info(String.format("%s, %s", filename, taxonomy));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        LOGGER.info("-------------------------------------------------");
    }

    private String qualifyCredit (TradeState trade) {
        return creditQualifierFunc.evaluate(trade);
    }

    public static void main (String[] args) {
        new CreditQualifyApplication().run();
    }
}
