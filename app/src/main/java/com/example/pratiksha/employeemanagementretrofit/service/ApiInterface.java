package com.example.pratiksha.employeemanagementretrofit.service;

import com.example.pratiksha.employeemanagementretrofit.model.BaseResponse;
import com.example.pratiksha.employeemanagementretrofit.model.Employee;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiInterface {

    @POST("/myapp/api/employee/single")
    public Call<BaseResponse> addEmployee(@Body Employee employee);

}
