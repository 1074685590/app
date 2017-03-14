package com.example.liumeng.quanminfu2.bean;

import java.util.List;

/**
 * Created by liumeng on 2017/1/19 on 15:23
 */

public class response2 {

    /**
     * errCode : 0000
     * errInfo : 请求成功
     * jPushMessageList : [{"alias":"000074512236","appName":"ANDROID,IOS","createTime":{"date":5,"day":4,"hours":1,"minutes":28,"month":0,"nanos":866000000,"seconds":30,"time":1483550910866,"timezoneOffset":-480,"year":117},"createUser":"","extras":"","id":"2249238eabf1468ba9404747eb39be3b","message":"您的 3.50 元支付成功，感谢您对全民付POS通业务的支持。如有疑问请致电客服：95534。【银联商务】","tag":"","updateTime":{"date":5,"day":4,"hours":1,"minutes":28,"month":0,"nanos":866000000,"seconds":30,"time":1483550910866,"timezoneOffset":-480,"year":117},"updateUser":""},{"alias":"000074512236","appName":"ANDROID,IOS","createTime":{"date":3,"day":2,"hours":17,"minutes":32,"month":0,"nanos":746000000,"seconds":37,"time":1483435957746,"timezoneOffset":-480,"year":117},"createUser":"","extras":"","id":"4dd2b65e836045b394d6e03883648242","message":"【银联商务】充值手机号13671549554，[20]元交易成功。如长时间未到账，请联系95534。感谢您对全民付的支持。","tag":"","updateTime":{"date":3,"day":2,"hours":17,"minutes":32,"month":0,"nanos":746000000,"seconds":37,"time":1483435957746,"timezoneOffset":-480,"year":117},"updateUser":""}]
     * memo : 消息推送查询
     */

    private String errCode;
    private String errInfo;
    private String memo;
    /**
     * alias : 000074512236
     * appName : ANDROID,IOS
     * createTime : {"date":5,"day":4,"hours":1,"minutes":28,"month":0,"nanos":866000000,"seconds":30,"time":1483550910866,"timezoneOffset":-480,"year":117}
     * createUser :
     * extras :
     * id : 2249238eabf1468ba9404747eb39be3b
     * message : 您的 3.50 元支付成功，感谢您对全民付POS通业务的支持。如有疑问请致电客服：95534。【银联商务】
     * tag :
     * updateTime : {"date":5,"day":4,"hours":1,"minutes":28,"month":0,"nanos":866000000,"seconds":30,"time":1483550910866,"timezoneOffset":-480,"year":117}
     * updateUser :
     */

    private List<JPushMessageListBean> jPushMessageList;

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrInfo() {
        return errInfo;
    }

    public void setErrInfo(String errInfo) {
        this.errInfo = errInfo;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public List<JPushMessageListBean> getJPushMessageList() {
        return jPushMessageList;
    }

    public void setJPushMessageList(List<JPushMessageListBean> jPushMessageList) {
        this.jPushMessageList = jPushMessageList;
    }

    public static class JPushMessageListBean {
        private String alias;
        private String appName;
        /**
         * date : 5
         * day : 4
         * hours : 1
         * minutes : 28
         * month : 0
         * nanos : 866000000
         * seconds : 30
         * time : 1483550910866
         * timezoneOffset : -480
         * year : 117
         */

        private CreateTimeBean createTime;
        private String createUser;
        private String extras;
        private String id;
        private String message;
        private String tag;
        /**
         * date : 5
         * day : 4
         * hours : 1
         * minutes : 28
         * month : 0
         * nanos : 866000000
         * seconds : 30
         * time : 1483550910866
         * timezoneOffset : -480
         * year : 117
         */

        private UpdateTimeBean updateTime;
        private String updateUser;

        public String getAlias() {
            return alias;
        }

        public void setAlias(String alias) {
            this.alias = alias;
        }

        public String getAppName() {
            return appName;
        }

        public void setAppName(String appName) {
            this.appName = appName;
        }

        public CreateTimeBean getCreateTime() {
            return createTime;
        }

        public void setCreateTime(CreateTimeBean createTime) {
            this.createTime = createTime;
        }

        public String getCreateUser() {
            return createUser;
        }

        public void setCreateUser(String createUser) {
            this.createUser = createUser;
        }

        public String getExtras() {
            return extras;
        }

        public void setExtras(String extras) {
            this.extras = extras;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public UpdateTimeBean getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(UpdateTimeBean updateTime) {
            this.updateTime = updateTime;
        }

        public String getUpdateUser() {
            return updateUser;
        }

        public void setUpdateUser(String updateUser) {
            this.updateUser = updateUser;
        }

        public static class CreateTimeBean {
            private int  date;
            private int  day;
            private int  hours;
            private int  minutes;
            private int  month;
            private int  nanos;
            private int  seconds;
            private long time;
            private int  timezoneOffset;
            private int  year;

            public int getDate() {
                return date;
            }

            public void setDate(int date) {
                this.date = date;
            }

            public int getDay() {
                return day;
            }

            public void setDay(int day) {
                this.day = day;
            }

            public int getHours() {
                return hours;
            }

            public void setHours(int hours) {
                this.hours = hours;
            }

            public int getMinutes() {
                return minutes;
            }

            public void setMinutes(int minutes) {
                this.minutes = minutes;
            }

            public int getMonth() {
                return month;
            }

            public void setMonth(int month) {
                this.month = month;
            }

            public int getNanos() {
                return nanos;
            }

            public void setNanos(int nanos) {
                this.nanos = nanos;
            }

            public int getSeconds() {
                return seconds;
            }

            public void setSeconds(int seconds) {
                this.seconds = seconds;
            }

            public long getTime() {
                return time;
            }

            public void setTime(long time) {
                this.time = time;
            }

            public int getTimezoneOffset() {
                return timezoneOffset;
            }

            public void setTimezoneOffset(int timezoneOffset) {
                this.timezoneOffset = timezoneOffset;
            }

            public int getYear() {
                return year;
            }

            public void setYear(int year) {
                this.year = year;
            }
        }

        public static class UpdateTimeBean {
            private int  date;
            private int  day;
            private int  hours;
            private int  minutes;
            private int  month;
            private int  nanos;
            private int  seconds;
            private long time;
            private int  timezoneOffset;
            private int  year;

            public int getDate() {
                return date;
            }

            public void setDate(int date) {
                this.date = date;
            }

            public int getDay() {
                return day;
            }

            public void setDay(int day) {
                this.day = day;
            }

            public int getHours() {
                return hours;
            }

            public void setHours(int hours) {
                this.hours = hours;
            }

            public int getMinutes() {
                return minutes;
            }

            public void setMinutes(int minutes) {
                this.minutes = minutes;
            }

            public int getMonth() {
                return month;
            }

            public void setMonth(int month) {
                this.month = month;
            }

            public int getNanos() {
                return nanos;
            }

            public void setNanos(int nanos) {
                this.nanos = nanos;
            }

            public int getSeconds() {
                return seconds;
            }

            public void setSeconds(int seconds) {
                this.seconds = seconds;
            }

            public long getTime() {
                return time;
            }

            public void setTime(long time) {
                this.time = time;
            }

            public int getTimezoneOffset() {
                return timezoneOffset;
            }

            public void setTimezoneOffset(int timezoneOffset) {
                this.timezoneOffset = timezoneOffset;
            }

            public int getYear() {
                return year;
            }

            public void setYear(int year) {
                this.year = year;
            }
        }
    }
}
