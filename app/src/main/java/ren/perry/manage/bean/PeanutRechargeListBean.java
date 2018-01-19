package ren.perry.manage.bean;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author perry
 * @date 2018/1/19
 * WeChat 917351143
 */

@NoArgsConstructor
@Data
public class PeanutRechargeListBean {

    /**
     * code : 200
     * error : 获取成功
     * data : [{"phone":"18180515080","device_model":"MI 4LTE","endDate":"1516441868","info":"测试机","id":"33","price":"18.00","device_id":"866963028900233","state":"0","createTime":"1516355491"},{"phone":"18244267955","device_model":"TA-1000","endDate":"1516358577","info":"魏培龙","id":"32","price":"18.00","device_id":"355457084536906","state":"1","createTime":"1516354445"},{"phone":"18244267955","device_model":"TA-1000","endDate":"1516358577","info":"魏培龙","id":"31","price":"18.00","device_id":"355457084536906","state":"0","createTime":"1516352223"}]
     */

    private int code;
    private String error;
    private List<DataBean> data;

    @NoArgsConstructor
    @Data
    public static class DataBean {
        /**
         * phone : 18180515080
         * device_model : MI 4LTE
         * endDate : 1516441868
         * info : 测试机
         * id : 33
         * price : 18.00
         * device_id : 866963028900233
         * state : 0
         * createTime : 1516355491
         */

        private String phone;
        private String device_model;
        private String endDate;
        private String info;
        private String id;              //充值id
        private String price;           //充值金额
        private String device_id;
        private String state;
        private String createTime;      //充值订单的创建日期
    }
}
