package com.base.websocket.common.mapper;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;

import java.io.IOException;

public class Mapper {
    public static  <R, T> T modelMapping( R reference, T target){
        ModelMapper modelMapper  = new ModelMapper();
        modelMapper.map(reference,target);
        return target;
    }
    public static <T> T jsonMapping(String json, TypeReference<T> type){
        ObjectMapper objectMapper = new ObjectMapper();
        T temp = null;
        try{
            temp = objectMapper.readValue(json, type);
        } catch (IOException e){
            e.printStackTrace();
            return null;
        }
        return temp;
    }
}
