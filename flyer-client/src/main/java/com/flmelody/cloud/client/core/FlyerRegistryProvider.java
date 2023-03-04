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

package com.flmelody.cloud.client.core;

import com.flmelody.cloud.FlyerDiscoveryProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.validator.routines.UrlValidator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author flmelody
 */
@Slf4j
public final class FlyerRegistryProvider {
    private static final String HTTPS = "https:";

    private static final String HTTP = "http:";

    private static final UrlValidator VALIDATOR = new UrlValidator();
    /**
     * Standalone mode
     */
    private String registry;
    /**
     * Cluster mode
     */
    private final List<String> registries = new ArrayList<>();

    public FlyerRegistryProvider(FlyerDiscoveryProperties flyerDiscoveryProperties) {
        String serverAddr = flyerDiscoveryProperties.getServerAddr();
        if (!serverAddr.startsWith(HTTP) && !serverAddr.startsWith(HTTPS)) {
            serverAddr = String.join("//", HTTP, serverAddr);
        }
        if (VALIDATOR.isValid(serverAddr)) {
            this.registry = serverAddr;
        } else {
            log.warn("No Valid Flyer Server Address");
        }

    }

    public String getServer() {
        return registry;
    }
}
