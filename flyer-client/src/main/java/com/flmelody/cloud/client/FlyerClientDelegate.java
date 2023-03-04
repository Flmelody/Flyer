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

package com.flmelody.cloud.client;

import com.flmelody.cloud.client.core.FlyerRegistryProvider;
import com.flmelody.cloud.common.pojo.FlyerServiceInstance;

import java.util.List;

/**
 * @author flmelody
 */
public class FlyerClientDelegate extends AbstractFlyerClient {
    private final FlyerInnerHttpClient flyerInnerHttpClient;
    private final FlyerOkHttpClient flyerOkHttpClient;

    public FlyerClientDelegate(FlyerRegistryProvider flyerRegistryProvider) {
        super(flyerRegistryProvider);
        this.flyerInnerHttpClient = new FlyerInnerHttpClient(flyerRegistryProvider);
        this.flyerOkHttpClient = new FlyerOkHttpClient(flyerRegistryProvider);
    }

    @Override
    public void registerService(FlyerServiceInstance flyerServiceInstance) {
        getTarget().registerService(flyerServiceInstance);
    }

    @Override
    public void deregisterService(FlyerServiceInstance flyerServiceInstance) {
        getTarget().deregisterService(flyerServiceInstance);
    }

    @Override
    public List<FlyerServiceInstance> queryService(String serviceId) {
        return getTarget().queryService(serviceId);
    }

    @Override
    public List<String> queryServices() {
        return getTarget().queryServices();
    }

    private FlyerClient getTarget() {
        String implementationVersion = Runtime.class.getPackage().getImplementationVersion();
        return flyerOkHttpClient;
    }
}
