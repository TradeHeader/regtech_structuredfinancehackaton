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
