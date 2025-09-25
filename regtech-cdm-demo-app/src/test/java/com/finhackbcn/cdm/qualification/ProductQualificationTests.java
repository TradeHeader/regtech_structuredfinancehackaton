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
package com.finhackbcn.cdm.qualification;

import cdm.event.common.TradeState;
import cdm.product.template.ContractualProduct;
import cdm.product.template.EconomicTerms;
import cdm.product.template.meta.EconomicTermsMeta;
import com.finhackbcn.cdm.utils.CDMTestUtils;
import com.finhackbcn.cdm.utils.ResourcesUtils;
import com.google.inject.Inject;
import com.rosetta.model.lib.qualify.QualifyFunctionFactory;
import com.rosetta.model.lib.qualify.QualifyResult;
import com.rosetta.model.lib.qualify.QualifyResultsExtractor;
import org.isda.cdm.qualify.EconomicTermsQualificationHandler;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.function.Function;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Tradeheader, SL
 *
 */
public class ProductQualificationTests extends CDMTestUtils {

    @Before
    public void setUp() {
        super.setUp();
    }

    @Inject
    private QualifyFunctionFactory.Default qualifyFunctionFactory;

    /**
     * Test demonstrates how to qualify and InterestRate:Swap:FixedFloat product
     * @throws IOException
     */
    @Test
    public void mustQualifyAsIRSwapFixedFloat() throws IOException {
        TradeState beforeTradeState = ResourcesUtils.getObjectAndResolveReferences(TradeState.class, irSwapTradeStatePath);
        assertNotNull("before TradeState must not be null", beforeTradeState);

        // Instance of the COntractualProductBuilder to add the productQualifier
        //
        ContractualProduct.ContractualProductBuilder contractualProductBuilder = beforeTradeState.getTrade().getTradableProduct().getProduct().getContractualProduct().toBuilder();
        System.out.println(String.format("productQualifier before: %s", contractualProductBuilder.getProductTaxonomy()));

        // Extract the list of qualification function applicable to the EconomicTerms object
        //
        List<Function<? super EconomicTerms, QualifyResult>> qualifyFunctions = new EconomicTermsMeta().getQualifyFunctions(qualifyFunctionFactory);

        // Use the QualifyResultsExtractor helper to easily make use of qualification results
        //
        String qualificationResult = new QualifyResultsExtractor<>(qualifyFunctions, contractualProductBuilder.getEconomicTerms())
                .getOnlySuccessResult()
                .map(QualifyResult::getName)
                .orElse("Failed to qualify");

        assertThat(qualificationResult, is("InterestRate_IRSwap_FixedFloat"));

        // Stamp the qualification value in the correct location using the qualification handler
        //
        EconomicTermsQualificationHandler qualificationHandler = new EconomicTermsQualificationHandler();
        qualificationHandler.setQualifier(contractualProductBuilder, qualificationResult);
        assertThat(qualificationHandler.getQualifier(contractualProductBuilder), is("InterestRate_IRSwap_FixedFloat"));

        System.out.println(String.format("productQualifier after: %s", contractualProductBuilder.build().getProductTaxonomy()));
    }

    /**
     * Test demonstrates how to qualify and InterestRate:CapFloor product
     * @throws IOException
     */
    @Test
    public void mustQualifyAsIRCapFloor() throws IOException {
        TradeState beforeTradeState = ResourcesUtils.getObjectAndResolveReferences(TradeState.class, irCapfloorTradeStatePath);
        assertNotNull("before TradeState must not be null", beforeTradeState);

        // Instance of the COntractualProductBuilder to add the productQualifier
        //
        ContractualProduct.ContractualProductBuilder contractualProductBuilder = beforeTradeState.getTrade().getTradableProduct().getProduct().getContractualProduct().toBuilder();
        System.out.println(String.format("productQualifier before: %s", contractualProductBuilder.getProductTaxonomy()));

        // Extract the list of qualification function applicable to the EconomicTerms object
        //
        List<Function<? super EconomicTerms, QualifyResult>> qualifyFunctions = new EconomicTermsMeta().getQualifyFunctions(qualifyFunctionFactory);

        // Use the QualifyResultsExtractor helper to easily make use of qualification results
        //
        String qualificationResult = new QualifyResultsExtractor<>(qualifyFunctions, contractualProductBuilder.getEconomicTerms())
                .getOnlySuccessResult()
                .map(QualifyResult::getName)
                .orElse("Failed to qualify");

        //assertThat(qualificationResult, is("InterestRate_IRSwap_FixedFloat"));
        assertThat(qualificationResult, is("InterestRate_CapFloor"));
    }
}
