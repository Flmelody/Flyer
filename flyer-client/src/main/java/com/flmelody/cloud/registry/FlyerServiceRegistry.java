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

import com.flmelody.cloud.client.FlyerClientDelegate;
import com.flmelody.cloud.client.core.FlyerRegistryProvider;
import com.flmelody.cloud.common.pojo.FlyerServiceInstance;
import org.springframework.cloud.client.serviceregistry.ServiceRegistry;
import org.springframework.cloud.commons.util.InetUtils;

/**
 * @author flmelody
 */
public class FlyerServiceRegistry implements ServiceRegistry<FlyerRegistration> {
    private final FlyerClientDelegate flyerClientDelegate;

    public FlyerServiceRegistry(FlyerRegistryProvider flyerRegistryProvider) {
        this.flyerClientDelegate = new FlyerClientDelegate(flyerRegistryProvider);
    }

    @Override
    public void register(FlyerRegistration registration) {
        FlyerServiceInstance flyerServiceInstance = FlyerServiceInstance.builder()
                .serviceId(registration.getServiceId())
                .host(registration.getHost())
                .port(registration.getPort())
                .metadata(registration.getMetadata()).build();
        flyerClientDelegate.registerService(flyerServiceInstance);
    }

    @Override
    public void deregister(FlyerRegistration registration) {
        FlyerServiceInstance flyerServiceInstance = FlyerServiceInstance.builder()
                .serviceId(registration.getServiceId())
                .host(registration.getHost())
                .port(registration.getPort()).build();
        flyerClientDelegate.deregisterService(flyerServiceInstance);
    }

    @Override
    public void close() {

    }

    @Override
    public void setStatus(FlyerRegistration registration, String status) {

    }

    @Override
    public Object getStatus(FlyerRegistration registration) {
        return null;
    }
}
