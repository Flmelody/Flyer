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

import com.flmelody.cloud.registry.FlyerServiceRegistryAutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.cloud.client.CommonsClientAutoConfiguration;
import org.springframework.cloud.client.ConditionalOnBlockingDiscoveryEnabled;
import org.springframework.cloud.client.ConditionalOnDiscoveryEnabled;
import org.springframework.cloud.client.discovery.simple.SimpleDiscoveryClientAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author flmelody
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnDiscoveryEnabled
@ConditionalOnBlockingDiscoveryEnabled
@AutoConfigureBefore({ SimpleDiscoveryClientAutoConfiguration.class,
        CommonsClientAutoConfiguration.class })
@AutoConfigureAfter(FlyerServiceRegistryAutoConfiguration.class)
public class FlyerDiscoveryClientAutoConfiguration {
    @Bean
    public FlyerDiscoveryClient flyerDiscoveryClient(FlyerDiscoveryManager flyerDiscoveryManager){
        return new FlyerDiscoveryClient(flyerDiscoveryManager);
    }
}