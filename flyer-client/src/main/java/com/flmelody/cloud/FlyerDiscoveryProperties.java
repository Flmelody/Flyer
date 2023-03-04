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

package com.flmelody.cloud;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author flmelody
 */
@Data
@ConfigurationProperties(value = FlyerDiscoveryProperties.FLYER_DISCOVERY_PREFIX)
public class FlyerDiscoveryProperties implements Serializable {
    @Serial
    private static final long serialVersionUID = -8487697056669551089L;
    public static final String FLYER_DISCOVERY_PREFIX = "spring.cloud.flyer.discovery";
    /**
     * flyers discovery server address.
     */
    private String serverAddr;
    /**
     * flyers authentication username.
     */
    private String username;
    /**
     * flyers authentication password.
     */
    private String password;
    /**
     * if you just want to subscribe, but don't want to register your service, set it to
     * false.
     */
    private boolean registerEnabled = true;
    /**
     * the services id for your service instance
     */
    @Value("${spring.cloud.flyer.discovery.service-id:${spring.application.name:}}")
    private String serviceId;
    /**
     * the ip address that you want to register for your service instance,
     * needn't set if the autodetect ip works well.
     */
    private String serviceIp;
    /**
     * the port that you want to register for your service instance,
     * needn't set if the autodetect port works well.
     */
    private int servicePort = -1;
    /**
     * extra metadata to register.
     */
    private Map<String, String> serviceMetadata = new HashMap<>();

}
