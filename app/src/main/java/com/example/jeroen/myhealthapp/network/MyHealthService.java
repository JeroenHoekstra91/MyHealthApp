package com.example.jeroen.myhealthapp.network;

import com.example.jeroen.myhealthapp.models.BloodPressure;
import com.example.jeroen.myhealthapp.models.ECG;
import com.example.jeroen.myhealthapp.models.Pulse;
import com.example.jeroen.myhealthapp.util.RestCallHelper;

import retrofit.Call;
import retrofit.Callback;

/**
 * Created by Jeroen on 24-11-2015.
 */
public class MyHealthService {
    public static final String BASE_URL = "http://jeroenhoekstra.no-ip.org:5000";
    private MyHealthApi api;
    private Callback<Void> handler;

    public MyHealthService(Callback<Void> handler) {
        api = RestCallHelper.getApi(BASE_URL, MyHealthApi.class);
        this.handler = handler;
    }

    public void pulseAdd(Pulse pulse) {
        Call<Void> call = api.pulseAdd(pulse);
        call.enqueue(handler);
    }

    public void ecgAdd(ECG ecg) {
        Call<Void> call = api.ecgAdd(ecg);
        call.enqueue(handler);
    }

    public void bloodPressureAdd(BloodPressure bloodPressure) {
        Call<Void> call = api.bloodPressureAdd(bloodPressure);
        call.enqueue(handler);
    }
}