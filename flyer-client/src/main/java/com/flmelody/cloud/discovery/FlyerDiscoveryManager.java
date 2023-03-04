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

package com.flmelody.cloud.discovery;

import com.flmelody.cloud.client.FlyerClientDelegate;
import com.flmelody.cloud.client.core.FlyerRegistryProvider;
import com.flmelody.cloud.common.pojo.FlyerServiceInstance;
import org.springframework.cloud.client.ServiceInstance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author flmelody
 */
public class FlyerDiscoveryManager {
    /**
     * Cached instances information
     */
    private final Map<String, List<FlyerServiceInstance>> cachedInstances = new HashMap<>();

    private final FlyerClientDelegate flyerClientDelegate;

    public FlyerDiscoveryManager(FlyerRegistryProvider flyerRegistryProvider) {
        this.flyerClientDelegate = new FlyerClientDelegate(flyerRegistryProvider);
    }

    public void registerService(FlyerServiceInstance flyerServiceInstance) {
        flyerClientDelegate.registerService(flyerServiceInstance);
    }

    public void deregisterService(FlyerServiceInstance flyerServiceInstance) {
        flyerClientDelegate.deregisterService(flyerServiceInstance);
    }

    public List<FlyerServiceInstance> queryService(String serviceId) {
        return flyerClientDelegate.queryService(serviceId);
    }

    public List<String> queryServices() {
        return flyerClientDelegate.queryServices();
    }

    public List<ServiceInstance> queryServiceInstance(String serviceId) {
        List<FlyerServiceInstance> flyerServiceInstances = queryService(serviceId);
        List<ServiceInstance> result = new ArrayList<>(flyerServiceInstances.size());
        result.addAll(flyerServiceInstances);
        return result;
    }
}
