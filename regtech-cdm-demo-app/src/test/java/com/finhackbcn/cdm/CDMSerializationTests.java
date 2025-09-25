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

import cdm.event.common.TradeState;
import com.finhackbcn.cdm.utils.CDMTestUtils;
import com.google.common.io.Resources;
import com.regnosys.rosetta.common.serialisation.RosettaObjectMapper;
import com.regnosys.rosetta.common.util.ClassPathUtils;
import com.rosetta.model.lib.records.Date;
import org.junit.Test;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Tradeheader, SL
 *
 */
public class CDMSerializationTests extends CDMTestUtils {

    @Test
    public void shouldDeserialiseCdmSampleFileWithClassLoader() throws IOException {
        // Get the classLoader from any class in CDM
        ClassLoader classLoader = TradeState.class.getClassLoader();
        Path sampleFilePath = ClassPathUtils
                .loadFromClasspath(irCapfloorTradeStatePath, classLoader)
                .findFirst()
                .orElseThrow();
        assertNotNull(sampleFilePath);

        TradeState deserializedTradeState =
                RosettaObjectMapper.getNewRosettaObjectMapper()
                        .readValue(sampleFilePath.toUri().toURL(), TradeState.class);
        assertNotNull(deserializedTradeState);
        assertEquals(Date.parse("2001-04-29"), deserializedTradeState.getTrade().getTradeDate().getValue());
    }

    @Test
    public void shouldDeserialiseCdmSampleFileWithResources() throws IOException {
        // Get the classLoader from any class in CDM
        URL sampleFilePath = Resources.getResource(irCapfloorTradeStatePath);
        assertNotNull(sampleFilePath);

        TradeState deserializedTradeState =
                RosettaObjectMapper.getNewRosettaObjectMapper().readValue(sampleFilePath, TradeState.class);
        assertNotNull(deserializedTradeState);
        assertEquals(Date.parse("2001-04-29"), deserializedTradeState.getTrade().getTradeDate().getValue());
    }
}
