package ren.perry.manage.api;

import ren.perry.manage.bean.PeanutUserEnableBean;
import ren.perry.manage.bean.PeanutUserEndDateBean;
import ren.perry.manage.bean.PeanutUserListBean;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * @author perry
 * @date 2018/1/18
 * WeChat 917351143
 */

public interface ApiService {

    /**
     * 获取花生果用户列表
     *
     * @return PeanutUserListBean
     */
    @POST("/Api/Home/Index/userList")
    Observable<PeanutUserListBean> peanutUserList();

    /**
     * 更新用户状态
     *
     * @param deviceId 设备ID
     * @param state    状态（1=启用，0=停用）
     * @return PeanutUserEnableBean
     */
    @POST("/Api/Home/Index/userEnable")
    @FormUrlEncoded
    Observable<PeanutUserEnableBean> peanutUserEnable(
            @Field("deviceId") String deviceId,
            @Field("state") int state);

    /**
     * 更新用户到期时间
     *
     * @param deviceId 设备ID
     * @param endDate  到期时间(PHP时间戳)
     * @return PeanutUserEndDateBean
     */
    @POST("/Api/Home/Index/userSetEndDate")
    @FormUrlEncoded
    Observable<PeanutUserEndDateBean> peanutUserEndDate(
            @Field("deviceId") String deviceId,
            @Field("endDate") String endDate);
}
