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
