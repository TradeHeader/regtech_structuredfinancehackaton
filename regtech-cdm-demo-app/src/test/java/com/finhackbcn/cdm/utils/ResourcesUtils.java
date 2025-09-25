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

import com.google.common.io.Resources;
import com.regnosys.rosetta.common.hashing.ReferenceResolverProcessStep;
import com.regnosys.rosetta.common.serialisation.RosettaObjectMapper;
import com.rosetta.model.lib.RosettaModelObject;
import org.isda.cdm.processor.CdmReferenceConfig;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * Created by Tradeheader, SL
 *
 */
public class ResourcesUtils {

    public static String getJson(String resourceName) throws IOException {
        URL url = Resources.getResource(resourceName);
        String json = Resources.toString(url, StandardCharsets.UTF_8);
        return json;
    }

    public static <T extends RosettaModelObject> T getObject(Class<T> clazz, String resourceName) throws IOException {
        String json = getJson(resourceName);
        return RosettaObjectMapper.getNewRosettaObjectMapper().readValue(json, clazz);
    }

    public static <T extends RosettaModelObject> T getObjectAndResolveReferences(Class<T> clazz, String resourceName) throws IOException {
        T object = getObject(clazz, resourceName);
        return resolveReferences(object);
    }
    private static <T extends RosettaModelObject> T resolveReferences(T object) {
        RosettaModelObject builder = object.toBuilder();
        new ReferenceResolverProcessStep(CdmReferenceConfig.get()).runProcessStep(builder.getType(), builder);
        return (T) builder.build();
    }
}

