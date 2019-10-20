package com.maliblo.fincam;

import com.maliblo.fincam.Responses.RespFullLabel;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface UploadAPIs {
    @Multipart
    @Headers({"Connection: keep-alive", "cache-control: no-cache"})
    @POST("OCR/Model/{model_id}/LabelFile/")
    Call<RespFullLabel> uploadImage(@Part MultipartBody.Part image, @Path("model_id") String model_id);

    @GET("OCR/Model/{model_id}")
    Call<ResponseBody> verify(@Path("model_id") String model_id);

    @Multipart
    @Headers("Authorization: application/x-www-form-urlencoded")
    @POST("OCR/Model/{model_id}/UploadFile/")
    Call<RespFullLabel> uploadTrainImage(@Part MultipartBody.Part image, @Path("model_id") String model_id);
}
