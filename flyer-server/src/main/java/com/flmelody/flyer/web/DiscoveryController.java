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

package com.flmelody.flyer.web;

import com.flmelody.cloud.common.pojo.FlyerServiceInstance;
import com.flmelody.cloud.common.rest.RestResponse;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static com.flmelody.cloud.common.constants.FlyerConstants.*;

/**
 * @author flmelody
 */
@RestController
@RequestMapping(value = DISCOVERY_ROOT_PATH)
public class DiscoveryController {
    private static Map<String, FlyerServiceInstance> flyerServiceInstanceMap = new HashMap<>();

    @PostMapping(value = DISCOVERY_INSTANCE_PATH)
    public RestResponse<FlyerServiceInstance> registerServiceInstance(@RequestBody FlyerServiceInstance flyerServiceInstance) {
        flyerServiceInstanceMap.put("service-provider", flyerServiceInstance);
        return RestResponse.success();
    }

    @DeleteMapping(value = DISCOVERY_INSTANCE_PATH)
    public RestResponse<?> deregisterServiceInstance(@RequestBody FlyerServiceInstance flyerServiceInstance) {
        return RestResponse.success();
    }

    @GetMapping(value = {DISCOVERY_QUERY_PATH + "/{serviceId}", DISCOVERY_QUERY_PATH + "/{serviceId}/{version}"})
    public RestResponse<List<FlyerServiceInstance>> queryServiceInstance(@PathVariable(value = "serviceId") String serviceId,
                                                                         @PathVariable(value = "version") Optional<String> version) {
        return RestResponse.success(Collections.singletonList(flyerServiceInstanceMap.get(serviceId)));
    }

    @GetMapping(value = DISCOVERY_QUERY_PATH)
    public RestResponse<List<FlyerServiceInstance>> queryAllServiceId() {
        return RestResponse.success();
    }
}
