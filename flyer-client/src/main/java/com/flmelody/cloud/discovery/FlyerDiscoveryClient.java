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

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;

import java.util.List;

/**
 * @author flmelody
 */
public class FlyerDiscoveryClient implements DiscoveryClient {
    private static final String DESCRIPTION = "Spring Cloud Flyer Discovery Client";
    private final FlyerDiscoveryManager flyerDiscoveryManager;

    public FlyerDiscoveryClient(FlyerDiscoveryManager flyerDiscoveryManager) {
        this.flyerDiscoveryManager = flyerDiscoveryManager;
    }

    @Override
    public String description() {
        return DESCRIPTION;
    }

    @Override
    public List<ServiceInstance> getInstances(String serviceId) {
        return flyerDiscoveryManager.queryServiceInstance(serviceId);
    }

    @Override
    public List<String> getServices() {
        return flyerDiscoveryManager.queryServices();
    }
}
