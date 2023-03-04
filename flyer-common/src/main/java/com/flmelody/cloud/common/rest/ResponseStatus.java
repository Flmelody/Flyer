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

package com.flmelody.cloud.common.rest;

/**
 * @author flmelody
 */
public enum ResponseStatus {
    /**
     * everything is fine
     */
    SUCCESS(200, "ok"),
    /**
     * forbidden
     */
    FORBIDDEN(401, "forbidden"),
    /**
     * error occurred
     */
    ERROR(500, "error");

    private final Integer code;
    private final String msg;

    Integer getCode() {
        return code;
    }

    String getMsg() {
        return msg;
    }

    ResponseStatus(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
