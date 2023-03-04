/*
 * Copyright 2023 Flmelody
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.flmelody.cloud.registry;

import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.client.serviceregistry.*;
import org.springframework.cloud.commons.util.InetUtils;


/**
 * @author flmelody
 */
public class FlyerAutoServiceRegistration extends AbstractAutoServiceRegistration<FlyerRegistration> {
    private final FlyerRegistration flyerRegistration;
    private final InetUtils inetUtils;

    public FlyerAutoServiceRegistration(FlyerRegistration flyerRegistration, ServiceRegistry<FlyerRegistration> serviceRegistry, AutoServiceRegistrationProperties properties, InetUtils inetUtils) {
        super(serviceRegistry, properties);
        this.flyerRegistration = flyerRegistration;
        this.inetUtils = inetUtils;
    }

    @SuppressWarnings("deprecation")
    @Override
    protected void register() {
        String host = this.flyerRegistration.getHost();
        if (StringUtils.isBlank(host)) {
            this.flyerRegistration.getFlyerDiscoveryProperties().setServiceIp(inetUtils.findFirstNonLoopbackHostInfo().getIpAddress());
        }
        int port = this.flyerRegistration.getPort();
        if (port < 0) {
            this.flyerRegistration.getFlyerDiscoveryProperties().setServicePort(getPort().get());
        }
        super.register();
    }

    @Override
    protected Object getConfiguration() {
        return null;
    }

    @Override
    protected boolean isEnabled() {
        return flyerRegistration.getFlyerDiscoveryProperties().isRegisterEnabled();
    }

    @Override
    protected FlyerRegistration getRegistration() {
        return this.flyerRegistration;
    }

    @Override
    protected FlyerRegistration getManagementRegistration() {
        return null;
    }
}
