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

package com.flmelody.cloud.registry;

import com.flmelody.cloud.FlyerDiscoveryProperties;
import com.flmelody.cloud.client.core.FlyerRegistryProvider;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.serviceregistry.AutoServiceRegistrationAutoConfiguration;
import org.springframework.cloud.client.serviceregistry.AutoServiceRegistrationConfiguration;
import org.springframework.cloud.client.serviceregistry.AutoServiceRegistrationProperties;
import org.springframework.cloud.commons.util.InetUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author flmelody
 */
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(value = FlyerDiscoveryProperties.class)
@ConditionalOnProperty(value = "spring.cloud.service-registry.auto-registration.enabled",
        matchIfMissing = true)
@AutoConfigureAfter({AutoServiceRegistrationConfiguration.class, AutoServiceRegistrationAutoConfiguration.class})
public class FlyerServiceRegistryAutoConfiguration {

    @Bean
    public FlyerRegistryProvider flyerRegistryProvider(FlyerDiscoveryProperties flyerDiscoveryProperties) {
        return new FlyerRegistryProvider(flyerDiscoveryProperties);
    }

    @Bean
    public FlyerRegistration flyerRegistration(FlyerDiscoveryProperties flyerDiscoveryProperties) {
        return new FlyerRegistration(flyerDiscoveryProperties);
    }

    @Bean
    public FlyerServiceRegistry flyerServiceRegistry(FlyerRegistryProvider flyerRegistryProvider) {
        return new FlyerServiceRegistry(flyerRegistryProvider);
    }

    @Bean
    public FlyerAutoServiceRegistration flyerAutoServiceRegistration(FlyerRegistration flyerRegistration,
                                                                     FlyerServiceRegistry flyerServiceRegistry,
                                                                     AutoServiceRegistrationProperties autoServiceRegistrationProperties,
                                                                     ObjectProvider<InetUtils> inetUtilsObjectProvider) {
        return new FlyerAutoServiceRegistration(flyerRegistration, flyerServiceRegistry, autoServiceRegistrationProperties, inetUtilsObjectProvider.getIfAvailable());
    }
}
