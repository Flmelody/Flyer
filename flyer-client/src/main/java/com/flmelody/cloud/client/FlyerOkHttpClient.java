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
import com.flmelody.cloud.util.JacksonUtils;
import okhttp3.*;

import java.io.IOException;
import java.util.List;

import static com.flmelody.cloud.common.constants.FlyerConstants.*;

/**
 * @author flmelody
 */
public class FlyerOkHttpClient extends AbstractFlyerClient {
    private final OkHttpClient okHttpClient = new OkHttpClient();
    public static final MediaType JSON = MediaType.parse("application/json;charset=utf-8");

    protected FlyerOkHttpClient(FlyerRegistryProvider flyerRegistryProvider) {
        super(flyerRegistryProvider);
    }

    @Override
    public void registerService(FlyerServiceInstance flyerServiceInstance) {
        RequestBody body = RequestBody.create(GsonUtils.toJson(flyerServiceInstance), JSON);
        Request request = new Request.Builder()
                .url(flyerRegistryProvider.getServer() + DISCOVERY_CONTEXT_PATH + DISCOVERY_ROOT_PATH + DISCOVERY_INSTANCE_PATH)
                .post(body).build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            assert response.body() != null;
            RestResponse<?> restResponse = GsonUtils.toObj(response.body().string(), RestResponse.class);
            response.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deregisterService(FlyerServiceInstance flyerServiceInstance) {
    }

    @Override
    public List<FlyerServiceInstance> queryService(String serviceId) {
        Request request = new Request.Builder()
                .url(flyerRegistryProvider.getServer() + DISCOVERY_CONTEXT_PATH + DISCOVERY_ROOT_PATH + DISCOVERY_QUERY_PATH+"/"+serviceId)
                .build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            assert response.body() != null;
            RestResponse<List<FlyerServiceInstance>> restResponse = JacksonUtils.toObj(response.body().string(), RestResponse.class);
            response.close();
            return restResponse.getData();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<String> queryServices() {
        return null;
    }
}
