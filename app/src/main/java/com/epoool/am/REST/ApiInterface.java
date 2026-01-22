package com.epoool.am.REST;

import androidx.core.app.NotificationCompat;
import com.epoool.am.Models.AlasanModel;
import com.epoool.am.Models.DeliveryOrderModel;
import com.epoool.am.Models.DeliveryRequestRes;
import com.epoool.am.Models.InsertUpdateModel;
import com.epoool.am.Models.PengalihanModel;
import com.epoool.am.Models.ReceiverModel;
import com.epoool.am.Models.SalesOrderRes;
import com.epoool.am.Models.SearchModel;
import com.epoool.am.Models.UserLoginModel;
import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/* loaded from: classes.dex */
public interface ApiInterface {
    @FormUrlEncoded
    @POST("mobile/pengalihan/api_pengalihan/get_alasan")
    Observable<AlasanModel> getAlasan(@Field("token_fcm") String str, @Field("id_originator") String str2, @Field("tipe_user") String str3);

    @FormUrlEncoded
    @POST("mobile/pengalihan/api_pengalihan/get_do_main_v2")
    Observable<DeliveryOrderModel> getDObyResi(@Field("id_username") String str, @Field("token_fcm") String str2, @Field("no_spj") String str3);

    @FormUrlEncoded
    @POST("mobile/pengalihan/api_pengalihan/get_list_pengalihan_v2")
    Observable<PengalihanModel> getPengalihan(@Field("id_username") String str, @Field("token_fcm") String str2, @Field(NotificationCompat.CATEGORY_STATUS) String str3, @Field("no_spj") String str4, @Field("tipe_sub_user") String str5);

    @FormUrlEncoded
    @POST("mobile/pengalihan/api_pengalihan/get_receiver_v2")
    Observable<ReceiverModel> getReceiver(@Field("id_username") String str, @Field("token_fcm") String str2, @Field("kode_receiver") String str3);

    @FormUrlEncoded
    @POST("mobile/pengalihan/api_pengalihan/cek_receiver_v2")
    Observable<SearchModel> getSearchReceiver(@Field("id_username") String str, @Field("token_fcm") String str2, @Field("id_originator") String str3, @Field("no_referensi") String str4);

    @FormUrlEncoded
    @POST("mobile/pengalihan/api_pengalihan/cek_spj_v2")
    Observable<SearchModel> getSearchSPJ(@Field("id_username") String str, @Field("token_fcm") String str2, @Field("id_originator") String str3, @Field("no_spj") String str4);

    @FormUrlEncoded
    @POST("mobile/pengalihan/api_pengalihan/insert_pengalihan_v2")
    Observable<InsertUpdateModel> insertPengalihan(@Field("id_username") String str, @Field("token_fcm") String str2, @Field("no_spj") String str3, @Field("receiver") String str4, @Field("alasan") String str5);

    @FormUrlEncoded
//    @POST("mobile/pengalihan/Api_pengalihan/login_originator_as_am")
    @POST("mobile/api_android_driver/login_by_username")
    Observable<UserLoginModel> login(@Field("username") String str,
                                     @Field("password") String str2,
                                     @Field("versi") String str3,
                                     @Field("os") String str4,
                                     @Field("sn") String str5,
                                     @Field("key") String str6);

    @FormUrlEncoded
    @POST("mobile/pengalihan/api_pengalihan/update_pengalihan_v2")
    Observable<InsertUpdateModel> updateStatusPengalihan(@Field("id_username") String str, @Field("token_fcm") String str2, @Field("id_pengalihan") String str3, @Field(NotificationCompat.CATEGORY_STATUS) String str4);

    @FormUrlEncoded
    @POST("mobile/originator/Delivery_request/get_so")
    Observable<SalesOrderRes> getSalesOrder(@Field("token") String str1, @Field("kd_plant") String str2, @Field("date_from") String str3, @Field("date_to") String str4);

    @FormUrlEncoded
    @POST("mobile/originator/Delivery_request/get_delivery_request")
    Observable<DeliveryRequestRes> getDeliveryRequest(@Field("token") String str1, @Field("no_so") String str2, @Field("line_so") String str3);

    @FormUrlEncoded
    @POST("mobile/originator/Delivery_request/store")
    Observable<InsertUpdateModel> saveDeliveryRequest(@Field("token") String str,
                                                      @Field("no_so") String str2,
                                                      @Field("line_so") String str3,
                                                      @Field("qty") String str4,
                                                      @Field("tanggal_kirim") String str5,
                                                      @Field("note") String str6);

    @FormUrlEncoded
    @POST("mobile/originator/Delivery_request/delete")
    Observable<InsertUpdateModel> deleteDeliveryRequest(@Field("token") String str, @Field("id_delivery_request") String str2);

}
