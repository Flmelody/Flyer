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

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

import static com.flmelody.cloud.common.rest.ResponseStatus.*;

/**
 * @author flmelody
 */
@Setter
@Getter
public final class RestResponse<T> implements Serializable {
    @Serial
    private static final long serialVersionUID = -4426496535406087458L;
    private Integer code;
    private String msg;
    private T data;

    public RestResponse() {
    }

    public RestResponse(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> RestResponse<T> success() {
        return result(SUCCESS);
    }

    public static <T> RestResponse<T> success(T data) {
        return result(SUCCESS, data);
    }

    public static <T> RestResponse<T> forbidden() {
        return result(FORBIDDEN);
    }

    public static <T> RestResponse<T> error(String msg) {
        return result(ERROR.getCode(), msg);
    }

    public static <T> RestResponse<T> result(ResponseStatus responseStatus) {
        return result(responseStatus, null);
    }

    public static <T> RestResponse<T> result(ResponseStatus responseStatus, T data) {
        return result(responseStatus.getCode(), responseStatus.getMsg(), data);
    }

    public static <T> RestResponse<T> result(Integer code, String msg) {
        return result(code, msg, null);
    }

    public static <T> RestResponse<T> result(Integer code, String msg, T data) {
        return new RestResponse<>(code, msg, data);
    }

}
