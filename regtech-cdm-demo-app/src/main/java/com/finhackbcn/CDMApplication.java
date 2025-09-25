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

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.finos.cdm.CdmRuntimeModule;

public abstract class CDMApplication {
    private Injector injector;

    public void run() {
        createInjectorAndInject();
        runApp();
    }

    protected void createInjectorAndInject() {
        injector = Guice.createInjector(new CdmRuntimeModule());
        injector.injectMembers(this);
    }

    public abstract void runApp();
}
