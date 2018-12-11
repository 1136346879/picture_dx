package com.xfs.qrcode_module.recycleview.rx;


import com.google.gson.Gson;
import com.lzy.okgo.convert.Converter;

import java.lang.reflect.Type;

import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 *
 * @author zhangpeiyuan
 * @date 2017/10/18
 */

public class ApiResultConverter<T> implements Converter<ApiResult<T>> {

    static Gson gson = new Gson();

    private Type type;


    public ApiResultConverter(Type type){
        this.type = type;
    }

    @Override
    public ApiResult<T> convertResponse(Response response) throws Throwable {
        Type objectType = new ParameterizedTypeImpl(ApiResult.class, new Type[]{ type});
        ResponseBody body = response.body();
        if (body == null){
            return null;
        }
        return gson.fromJson(body.string(), objectType);
    }
}
