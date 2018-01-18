package ren.perry.manage.bean;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author perry
 * @date 2018/1/18
 * WeChat 917351143
 */

@NoArgsConstructor
@Data
public class PeanutUserListBean {

    /**
     * code : 1
     * error : 获取成功
     * data : [{"id":"32","state":"1","phone":"18228088221","device_id":"863513036496071","device_model":"vivo X9i","createDate":"1516193420","endDate":"1516279820","info":"哦"},{"id":"31","state":"1","phone":"18244267955","device_id":"355457084536906","device_model":"TA-1000","createDate":"1516193005","endDate":"1517279405","info":""}]
     */

    private int code;
    private String error;
    private List<DataBean> data;

    @NoArgsConstructor
    @Data
    public static class DataBean {
        /**
         * id : 32
         * state : 1
         * phone : 18228088221
         * device_id : 863513036496071
         * device_model : vivo X9i
         * createDate : 1516193420
         * endDate : 1516279820
         * info : 哦
         */

        private String id;
        private String state;
        private String phone;
        private String device_id;
        private String device_model;
        private String createDate;
        private String endDate;
        private String info;
    }
}
