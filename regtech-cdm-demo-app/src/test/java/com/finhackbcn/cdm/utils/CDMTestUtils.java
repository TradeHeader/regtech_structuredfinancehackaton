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
package com.finhackbcn.cdm.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.regnosys.rosetta.common.serialisation.RosettaObjectMapper;
import org.finos.cdm.CdmRuntimeModule;
import org.junit.Before;

import java.io.IOException;
import java.nio.file.Paths;

/**
 * Created by Tradeheader, SL
 *
 */
public class CDMTestUtils {

    protected static Injector injector;

    @Before
    public void setUp() {
        injector = Guice.createInjector(new CdmRuntimeModule());
        injector.injectMembers(this);
    }

    protected static final String irSwapTradeStatePath = "com/finhackbcn/samples/cdm/ird-ex01-vanilla-swap-tradeState.json";
    protected static final String irCapfloorTradeStatePath = "com/finhackbcn/samples/cdm/ird-ex22-cap-tradeState.json";
    protected static final String cdsRMBSTradeStatePath = "com/finhackbcn/samples/cdm/ird-ex22-cap-tradeState.json";

    protected final ObjectMapper mapper = RosettaObjectMapper.getNewRosettaObjectMapper();
    protected void save (String dir, String filename, Object payload) throws IOException {
        mapper.writerWithDefaultPrettyPrinter().writeValue(Paths.get(dir, filename).toFile(), payload);
    }
}
