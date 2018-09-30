package com.example.pratiksha.employeemanagementretrofit.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.pratiksha.employeemanagementretrofit.R;
import com.example.pratiksha.employeemanagementretrofit.model.BaseResponse;
import com.example.pratiksha.employeemanagementretrofit.model.Employee;
import com.example.pratiksha.employeemanagementretrofit.service.ApiClient;
import com.example.pratiksha.employeemanagementretrofit.service.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddFragment extends Fragment {

    EditText edtName,edtAddress,edtPhone,edtSalary,edtDesignation;
    Button btnSave;
    ProgressBar progress;

    public AddFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add, container, false);

        edtName = view.findViewById(R.id.edtName);
        edtAddress = view.findViewById(R.id.edtAddress);
        edtPhone = view.findViewById(R.id.edtPhone);
        edtSalary = view.findViewById(R.id.edtSalary);
        edtDesignation = view.findViewById(R.id.edtDesignation);
        progress= view.findViewById(R.id.progress);
        btnSave = view.findViewById(R.id.btnSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name =edtName.getText().toString().trim();
                String address =edtAddress.getText().toString().trim();
                String phone =edtPhone.getText().toString().trim();
                String salaryString =edtSalary.getText().toString().trim();
                String designation =edtDesignation.getText().toString().trim();

                long salary = Long.parseLong(salaryString);

                Employee employee = new Employee(name,address,phone,salary,designation);

                ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
                progress.setVisibility(View.VISIBLE);
                Call<BaseResponse> callResponse = apiInterface.addEmployee(employee);

                callResponse.enqueue(new Callback<BaseResponse>() {
                    @Override
                    public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                        progress.setVisibility(View.GONE);

                        BaseResponse baseResponse = response.body();
//                        Toast.makeText(getActivity(), baseResponse.getMessage(), Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onFailure(Call<BaseResponse> call, Throwable t) {
                        Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
                        progress.setVisibility(View.GONE);
                    }
                });



            }
        });

        // Inflate the layout for this fragment
        return view;
    }

}
