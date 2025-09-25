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
package com.finhackbcn.cdm;

import cdm.base.staticdata.party.Account;
import cdm.event.common.TradeState;
import com.finhackbcn.cdm.utils.CDMTestUtils;
import com.finhackbcn.cdm.utils.ResourcesUtils;
import com.google.common.io.Resources;
import com.regnosys.rosetta.common.validation.RosettaTypeValidator;
import com.rosetta.model.lib.validation.ValidationResult;
import org.junit.Before;
import org.junit.Test;

import javax.inject.Inject;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Tradeheader, SL
 *
 */
public class CDMValidationTests extends CDMTestUtils {

    /**
     * Instance for type validation
     */
    @Inject
    private RosettaTypeValidator validator;

    /**
     * Set up the functional bindings (even custom bindings)
     */
    @Before
    public void setUp() {
        super.setUp();
    }

    /**
     * Lists all type validation results for serialized input TradeState sample
     * @throws IOException
     */
    //@Test
    public void shouldValidateIRTradeState() throws IOException {

        TradeState serialized = ResourcesUtils.getObjectAndResolveReferences(TradeState.class, irSwapTradeStatePath);
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(serialized));
        // Recursively run all validators for an object
        List<ValidationResult<?>> results = validator.runProcessStep(TradeState.class, serialized.toBuilder())
                .getValidationResults();

        //print first errors and then successes
        results.stream().sorted(Comparator.comparing(ValidationResult::isSuccess, Boolean::compareTo)).forEach(System.out::println);

        assertNotNull(results);
    }

    @Test
    public void shouldValidateCDSTradeState() throws IOException {

        TradeState serialized = mapper.readValue(Resources.getResource(cdsRMBSTradeStatePath), TradeState.class);
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(serialized));
        // Recursively run all validators for an object
        List<ValidationResult<?>> results = validator.runProcessStep(Account.class, serialized.toBuilder())
                .getValidationResults();

        //print first errors and then successes
        results.stream().sorted(Comparator.comparing(ValidationResult::isSuccess, Boolean::compareTo)).forEach(System.out::println);

        assertNotNull(results);
    }
}
