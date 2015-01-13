package com.sourceknowledge.vast.rest;

import java.lang.reflect.Type;

import retrofit.converter.ConversionException;
import retrofit.converter.Converter;
import retrofit.mime.TypedInput;
import retrofit.mime.TypedOutput;

/**
 * Created by omegatai on 15-01-12.
 */
public class MixedConverter implements Converter {
    private Converter mSerializer;
    private Converter mDeserializer;

    public MixedConverter(Converter serializer, Converter deserializer) {
        mSerializer = serializer;
        mDeserializer = deserializer;
    }


    @Override
    public Object fromBody(TypedInput body, Type type) throws ConversionException {
        return mDeserializer.fromBody(body, type);
    }

    @Override
    public TypedOutput toBody(Object object) {
        return mSerializer.toBody(object);
    }
}
