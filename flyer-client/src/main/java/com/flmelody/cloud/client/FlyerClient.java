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

import com.flmelody.cloud.common.pojo.FlyerServiceInstance;

import java.util.List;

/**
 * @author flmelody
 */
public interface FlyerClient {
    /**
     * register the flyerServiceInstance. A flyerServiceInstance typically has information about an
     * instance, such as its hostname and port.
     *
     * @param flyerServiceInstance flyerServiceInstance meta data
     */
    void registerService(FlyerServiceInstance flyerServiceInstance);

    /**
     * deregister the flyerServiceInstance.
     *
     * @param flyerServiceInstance flyerServiceInstance meta data
     */
    void deregisterService(FlyerServiceInstance flyerServiceInstance);

    /**
     * gets all ServiceInstances associated with a particular serviceId.
     *
     * @param serviceId The serviceId to query.
     * @return a list of FlyerServiceInstance.
     */
    List<FlyerServiceInstance> queryService(String serviceId);

    /**
     * @return all known service IDs.
     */
    List<String> queryServices();
}
