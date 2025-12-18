package com.epoool.approvalepoool.Utils;

import com.google.gson.Gson;
import com.google.gson.internal.$Gson$Types;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/* loaded from: classes.dex */
public class GsonConverter<T> {
    static Type getSuperclassTypeParameter(Class<?> cls) {
        Type genericSuperclass = cls.getGenericSuperclass();
        if (genericSuperclass instanceof Class) {
            throw new RuntimeException("Missing type parameter.");
        }
        return $Gson$Types.canonicalize(((ParameterizedType) genericSuperclass).getActualTypeArguments()[0]);
    }

    public String toJsonString(Object obj) {
        return new Gson().toJson(obj, new TypeToken<T>() { // from class: com.epoool.approvalepoool.Utils.GsonConverter.1
        }.getType());
    }

    public T toJsonObject(String str) {
        return (T) new Gson().fromJson(str, getSuperclassTypeParameter(getClass()));
    }
}
