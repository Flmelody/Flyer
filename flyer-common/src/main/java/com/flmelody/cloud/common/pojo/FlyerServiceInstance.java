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

package com.flmelody.cloud.common.pojo;

import lombok.*;
import org.springframework.cloud.client.ServiceInstance;

import java.net.URI;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author flmelody
 */
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FlyerServiceInstance implements ServiceInstance {
    /**
     * instances groupName.
     */
    private String groupName;
    /**
     * instances id.
     */
    private String instanceId;
    /**
     * instances serviceId.
     */
    private String serviceId;
    /**
     * instances weight.
     */
    private int weight = 1;
    /**
     * instances version.
     */
    private String version;
    /**
     * instances host.
     */
    private String host;
    /**
     * instances port.
     */
    private int port;
    /**
     * instances is secure or not.
     */
    private boolean secure;
    /**
     * instances health status.
     */
    private boolean healthy = true;
    /**
     * instance is enabled to accept request.
     */
    private boolean enabled = true;
    private Map<String, String> metadata = new LinkedHashMap<>();
    private URI uri;


}
