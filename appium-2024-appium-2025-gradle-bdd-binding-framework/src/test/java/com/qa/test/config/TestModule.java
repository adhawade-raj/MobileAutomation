package com.qa.test.config;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.qa.factory.DriverFactory;
import com.qa.test.implementation.Registration;
import com.qa.utils.AndroidUtils;
import io.appium.java_client.android.AndroidDriver;

import javax.inject.Singleton;

public class TestModule extends AbstractModule {
    @Override
    protected void configure() {

        bind(AndroidDriver.class).toProvider(DriverFactory.class); // Bind using provider
//        bind(Registration.class);
//        bind(AndroidUtils.class);
    }
}
