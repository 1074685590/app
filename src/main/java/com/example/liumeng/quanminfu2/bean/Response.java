package com.example.liumeng.quanminfu2.bean;

import java.util.List;

/**
 * Created by liumeng on 2017/1/17 on 9:40
 */

public class Response {


    /**
     * jPushMessageList : [{"alias":"000080197160","appName":"ANDROID,IOS","createTime":{"date":2,"day":1,"hours":19,"minutes":39,"month":0,"nanos":224000000,"seconds":56,"time":1483357196224,"timezoneOffset":-480,"year":117},"createUser":"","extras":"","id":"9625c49fb26643dd87f4d1b6fa093304","message":"【银联商务】充值手机号13585933773，[100]元交易成功。如长时间未到账，请联系95534。感谢您对全民付的支持。","tag":"","updateTime":{"date":2,"day":1,"hours":19,"minutes":39,"month":0,"nanos":224000000,"seconds":56,"time":1483357196224,"timezoneOffset":-480,"year":117},"updateUser":""},{"alias":"000080197160","appName":"ANDROID,IOS","createTime":{"date":2,"day":1,"hours":19,"minutes":39,"month":0,"nanos":164000000,"seconds":9,"time":1483357149164,"timezoneOffset":-480,"year":117},"createUser":"","extras":"","id":"2cb0749eea3b4225933a20deff442061","message":"【银联商务】充值手机号13916851222，[50]元交易成功。如长时间未到账，请联系95534。感谢您对全民付的支持。","tag":"","updateTime":{"date":2,"day":1,"hours":19,"minutes":39,"month":0,"nanos":164000000,"seconds":9,"time":1483357149164,"timezoneOffset":-480,"year":117},"updateUser":""}]
     * memo : 消息推送查询
     * errInfo : 请求成功
     * errCode : 0000
     */

    public String memo;
    public String errInfo;
    public String errCode;
    /**
     * alias : 000080197160
     * appName : ANDROID,IOS
     * createTime : {"date":2,"day":1,"hours":19,"minutes":39,"month":0,"nanos":224000000,"seconds":56,"time":1483357196224,"timezoneOffset":-480,"year":117}
     * createUser : 
     * extras : 
     * id : 9625c49fb26643dd87f4d1b6fa093304
     * message : 【银联商务】充值手机号13585933773，[100]元交易成功。如长时间未到账，请联系95534。感谢您对全民付的支持。
     * tag : 
     * updateTime : {"date":2,"day":1,"hours":19,"minutes":39,"month":0,"nanos":224000000,"seconds":56,"time":1483357196224,"timezoneOffset":-480,"year":117}
     * updateUser : 
     */

    public List<JPushMessageListBean> jPushMessageList;


    public static class JPushMessageListBean {
        public String alias;
        public String appName;
        /**
         * date : 2
         * day : 1
         * hours : 19
         * minutes : 39
         * month : 0
         * nanos : 224000000
         * seconds : 56
         * time : 1483357196224
         * timezoneOffset : -480
         * year : 117
         */

        public CreateTimeBean createTime;
        public String createUser;
        public String extras;
        public String id;
        public String message;
        public String tag;
        /**
         * date : 2
         * day : 1
         * hours : 19
         * minutes : 39
         * month : 0
         * nanos : 224000000
         * seconds : 56
         * time : 1483357196224
         * timezoneOffset : -480
         * year : 117
         */

        public UpdateTimeBean updateTime;
        public String updateUser;

      

        public static class CreateTimeBean {
            public int  date;
            public int  day;
            public int  hours;
            public int  minutes;
            public int  month;
            public int  nanos;
            public int  seconds;
            public long time;
            public int  timezoneOffset;
            public int  year;

        }

        public static class UpdateTimeBean {
            public int  date;
            public int  day;
            public int  hours;
            public int  minutes;
            public int  month;
            public int  nanos;
            public int  seconds;
            public long time;
            public int  timezoneOffset;
            public int  year;
        }
    }
}
