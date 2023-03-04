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
import com.flmelody.cloud.common.rest.RestResponse;
import com.flmelody.cloud.util.GsonUtils;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

/**
 * @author flmelody
 */
public class FlyerInnerHttpClient extends AbstractFlyerClient {
    private final HttpClient httpClient = HttpClient.newBuilder().build();

    protected FlyerInnerHttpClient(FlyerRegistryProvider flyerRegistryProvider) {
        super(flyerRegistryProvider);
    }

    @Override
    public void registerService(FlyerServiceInstance flyerServiceInstance) {
        String serverAddr = flyerRegistryProvider.getServer();
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(null)
                .POST(HttpRequest.BodyPublishers.ofString(GsonUtils.toJson(flyerServiceInstance))).build();
        try {
            String body = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString()).body();
            RestResponse<?> restResponse = GsonUtils.toObj(body, RestResponse.class);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deregisterService(FlyerServiceInstance flyerServiceInstance) {

    }

    @Override
    public List<FlyerServiceInstance> queryService(String serviceId) {
        return null;
    }

    @Override
    public List<String> queryServices() {
        return null;
    }
}
