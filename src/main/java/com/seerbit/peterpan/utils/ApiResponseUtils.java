package com.seerbit.peterpan.utils;

import com.seerbit.peterpan.apiresponse.ApiDataResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;

public class ApiResponseUtils {
    public static  <T> ResponseEntity<ApiDataResponse<T>> response(HttpStatus status, T data, String message ){
        return ApiResponseUtils.getResponse(status,data,message);
    }
    public static  <T> ResponseEntity<ApiDataResponse<T>> response(HttpStatus status, T data){
        String message=null;
        if(status.equals(HttpStatus.OK)){
            message="Success";
        }
        return ApiResponseUtils.getResponse(status,data,message);
    }
    private static  <T> ResponseEntity<ApiDataResponse<T>> getResponse(HttpStatus status, @Nullable T data, @Nullable String message ){
        ApiDataResponse<T> ar = new ApiDataResponse<>(HttpStatus.OK);
        ar.setData(data);
        ar.setMessage(message);
        return new ResponseEntity<>(ar,status);
    }
    public static  <T> ResponseEntity<ApiDataResponse<T>> response(HttpStatus status, String message ){
        return ApiResponseUtils.getResponse(status,null,message);
    }
}
