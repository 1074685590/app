package com.example.liumeng.quanminfu2.activity12;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.liumeng.quanminfu2.R;

public class WebViewActivity extends AppCompatActivity {

    private WebView mWebview_web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        init();
    }

    private void init() {
        initView();

        mWebview_web.setWebChromeClient(new WebChromeClient());	//处理JavaScript对话框
        mWebview_web.setWebViewClient(new WebViewClient());	//处理各种通知和请求事件，如果不使用该句代码，将使用内置浏览器访问网页
        mWebview_web.loadUrl("http://www.baidu.com ");	//设置默认显示的天气预报信息

        WebView webview=(WebView)findViewById(R.id.webview_web);	//获取布局管理器中添加的WebView组件
        StringBuilder sb=new StringBuilder();	//创建一个字符串构建器，将要显示的HTML内容放置在该构建器中
        sb.append("<ol>");
        sb.append("<li>IC卡电子现金、Apple Pay等NFC支付、其它客户端扫码支付均不能参加本次活动； </li>");
        sb.append("<li>商户优惠券不可与其它优惠叠加使用，不能同时使用2张及以上优惠券，同一张银行卡若同时绑定了商户优惠券、银行特惠及会员卡，商户优惠券或银行特惠将优先享受；</li>");
        sb.append("<li> 若商户设置的当期会员卡优惠额度已用完，当期后续用户将不能再享受会员卡优惠；</li>");
        sb.append("<li>优惠券可用时间解释权归活动运营方所有；</li>");
        sb.append("<li>同一手机号或同一客户端账户视为单个用户，如发现有恶意套利、影响其它用户消费的情况，银联商务及商户有权拒绝；</li>");
        sb.append("<li> 用户如需开票应按刷卡实扣金额（交易金额），优惠部分不开票；/li>");
        sb.append("<li>在法律许可范围内，银联商务与活动运营方拥有活动最终解释权。</li>");
        sb.append("</ol>");
        webview.loadDataWithBaseURL(null, sb.toString(), "text/html", "utf-8", null);	//加载数据
    }

    private void initView() {
        mWebview_web = (WebView) findViewById(R.id.webview_web);
    }
}
