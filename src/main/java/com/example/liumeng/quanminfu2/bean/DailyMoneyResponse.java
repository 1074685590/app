package com.example.liumeng.quanminfu2.bean;

import java.util.List;

/**
 * Created by liumeng on 2016/12/5 on 13:37
 */
public class DailyMoneyResponse {

    /**
     * acctStatus : 1
     * bankCode : 0105
     * bankName : 建设银行
     * bindcardId : 786287
     * bindcardNo : 6217001210067682870
     * canDraw : 0
     * createTime : 20160913214451
     * dayProfit : 0
     * depositFund : 0
     * errCode : 0000
     * errInfo : 交易成功
     * fundAcctStatus : 2
     * fundCode : 000677
     * fundName : 工银现金货币
     * fundUserNo : 00999000002521131
     * memo : 天天富账户信息查询
     * netValue : 1
     * notTransferProfit : 0
     * payChannel : 01
     * profitPerTenThousand : 0.6659
     * profitPerTenThousands : [["10-06","0.6719"],["10-07","0.6719"],["10-08","0.6718"],["10-09","0.6718"],["10-10","0.6785"],["10-11","0.6599"],["10-12","0.6652"],["10-13","0.6872"],["10-14","0.6811"],["10-15","0.6793"],["10-16","0.6792"],["10-17","0.6788"],["10-18","0.6641"],["10-19","0.6658"],["10-20","0.6516"],["10-21","1.2611"],["10-22","0.6437"],["10-23","0.6436"],["10-24","1.0834"],["10-25","0.653"],["10-26","0.6567"],["10-27","0.6513"],["10-28","0.6503"],["10-29","0.6501"],["10-30","0.6501"],["10-31","0.652"],["11-01","0.6526"],["11-02","0.6507"],["11-03","0.6465"],["11-04","0.9357"],["11-05","0.6499"],["11-06","0.6498"],["11-07","0.6588"],["11-08","0.6515"],["11-09","0.6526"],["11-10","0.6451"],["11-11","0.6717"],["11-12","0.6428"],["11-13","0.6427"],["11-14","0.6428"],["11-15","0.7225"],["11-16","0.7227"],["11-17","0.6395"],["11-18","0.8112"],["11-19","0.6433"],["11-20","0.6433"],["11-21","1.2631"],["11-22","0.6454"],["11-23","0.8272"],["11-24","0.6462"],["11-25","0.8763"],["11-26","0.6448"],["11-27","0.6418"],["11-28","0.9054"],["11-29","0.6483"],["11-30","0.8145"],["12-01","0.6505"],["12-02","0.6713"],["12-03","0.666"],["12-04","0.6659"]]
     * redeemFund : 0
     * sevenDayYearRate : 2.65300
     * sevenDayYearRates : [["10-06","0.02482"],["10-07","0.02483"],["10-08","0.02483"],["10-09","0.02483"],["10-10","0.02486"],["10-11","0.0248"],["10-12","0.02476"],["10-13","0.02484"],["10-14","0.02489"],["10-15","0.02493"],["10-16","0.02497"],["10-17","0.02497"],["10-18","0.025"],["10-19","0.025"],["10-20","0.02481"],["10-21","0.02791"],["10-22","0.02772"],["10-23","0.02753"],["10-24","0.0297"],["10-25","0.02964"],["10-26","0.02959"],["10-27","0.02959"],["10-28","0.02632"],["10-29","0.02635"],["10-30","0.02639"],["10-31","0.02408"],["11-01","0.02408"],["11-02","0.02405"],["11-03","0.02402"],["11-04","0.02555"],["11-05","0.02554"],["11-06","0.02554"],["11-07","0.02558"],["11-08","0.02557"],["11-09","0.02558"],["11-10","0.02558"],["11-11","0.02416"],["11-12","0.02413"],["11-13","0.02409"],["11-14","0.024"],["11-15","0.02438"],["11-16","0.02476"],["11-17","0.02473"],["11-18","0.02547"],["11-19","0.02548"],["11-20","0.02548"],["11-21","0.0288"],["11-22","0.02839"],["11-23","0.02895"],["11-24","0.02898"],["11-25","0.02933"],["11-26","0.02934"],["11-27","0.02933"],["11-28","0.02742"],["11-29","0.02743"],["11-30","0.02736"],["12-01","0.02739"],["12-02","0.02629"],["12-03","0.0264"],["12-04","0.02653"]]
     * shares : 0
     * srcReqDate : 20161205
     * srcReqId : 180094067
     * srcReqSettleDate : 20161205
     * srcReqTime : 112652
     * status : 1
     * totalFund : 0
     * totalProfit : 0
     * transCode : 201314
     * transDate : 20161204
     * transReqId :
     * transSettleDate :
     * transTime :
     * transitFund : 0
     */

    private String acctStatus;
    private String             bankCode;
    private String             bankName;
    private String             bindcardId;
    private String             bindcardNo;
    private String             canDraw;
    private String             createTime;
    private String             dayProfit;
    private String             depositFund;
    private String             errCode;
    private String             errInfo;
    private String             fundAcctStatus;
    private String             fundCode;
    private String             fundName;
    private String             fundUserNo;
    private String             memo;
    private String             netValue;
    private String             notTransferProfit;
    private String             payChannel;
    private String             profitPerTenThousand;
    private String             redeemFund;
    private String             sevenDayYearRate;
    private String             shares;
    private String             srcReqDate;
    private String             srcReqId;
    private String             srcReqSettleDate;
    private String             srcReqTime;
    private String             status;
    private String             totalFund;
    private String             totalProfit;
    private String             transCode;
    private String             transDate;
    private String             transReqId;
    private String             transSettleDate;
    private String             transTime;
    private String             transitFund;
    private List<List<String>> profitPerTenThousands;
    private List<List<String>> sevenDayYearRates;
}
