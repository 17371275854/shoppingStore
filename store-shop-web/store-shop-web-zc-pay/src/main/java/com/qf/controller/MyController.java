package com.qf.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.google.gson.Gson;
import com.qf.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Controller
@Slf4j
public class MyController {
    @RequestMapping("success")
    public String getSuccess(HttpServletRequest request, Model model) {
        String total_amount = request.getParameter("total_amount");
        model.addAttribute("total_amount",total_amount);
        return "success";
    }

    @RequestMapping("pay")
    public String showPay() {
        return "pay";
    }

    /**
     * ServerUrl:支付网关
     * APP_ID: 应用ID
     * APP_PRIVATE_KEY:商户应用私钥
     * FORMAT:返回数据类型
     * CHARSET:字符集
     * ALIPAY_PUBLIC_KEY:支付宝公钥
     * SIGN_TYPE:密钥生成方式
     */
    @RequestMapping(value = "/doPay",method = RequestMethod.GET)
    public void doPost(HttpServletRequest httpRequest,
                       HttpServletResponse httpResponse, String oid) throws ServletException, IOException, IOException {
        Order order = (Order) httpRequest.getAttribute("order");
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipaydev.com/gateway.do",
                "2016101800718886",
                "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQD5Cgsq5aV/YVPDC7PuQtihz6vEaY0VZZnsGHweXQ90qyIBWiY+ueaMNgwKQAAS71HyAYAZS9TMgzEQ/IP4dgFKR102ESkXk8B+Z7hvKOv1nGEjCCb7KvYVMFzfbiuFhJzEoPk2NbWXTop8J+BPwaIklr9W7LNYARuwXR0PzVvTCCyDznAvm4qvhBcBrEWugsrqtHSYswkc/E4ryYLAuFmO2xsgqRdfBZ3KYBaTnk8BovXB+aCAhkYWu3IJQB/CjQN3qBxtrMehZnawMU0Vb8dTNQ9CSHGXXcjTyM7HBFcvRVSxscYOuxMxuV2rfEXEaEYA7mbcmXTIPx6d+meOfojRAgMBAAECggEBANFYOwkFYLiSZSoZhVhtQtPK0dZa8Ckjbl+EOZoAmoR1czF1yZ6DASWuLTLUl/tSyCzKTBd3oluEkNN5oapT8EOzd8yCYmuaXX8ky2pOGQQvP4yGRtZuR8yKChgb8WPclTfJJZUHUy3s7QbvMmV5HUbqnc+BTEVXMSJxM4PcGitrFBOBxbZrNznpTmhXOH1eMQaRqijoUsslLO0lMStA/ikH/wk5WCxP/hFUr7zoelH/T7F7y7smk7yCNdmnQ1Jbj3v0a4p/GbGzH9eKAo92UOIKh8gp69j2fBz90181umt3OP8bPFKIdgy8nHf/yqKnzOD5Qx3mepRLXDKUNphwE60CgYEA/nUU95RQod95UZKQakruF3xHNET6Leh1LPajZrCxEsO9b4/6jaiKClzVZtVMASh+YcQ7Rzw19NRw6F2BUIcFR3LMQWrcGiKWqg+aPBK81UN6+t0CarT60D0MfMkbLKBKy9U0ryTX+mkN//0p2hdiOZeWxvi70tsbTOoOUfih/78CgYEA+oyNg/ZvuAUjpPVyUANYjzgx2g08f5vbAoJM0jVCH1Y7l0o3D42eE7iyTibr1v98XjIKqbyZ0TlEaCrsthXkJuFfB4J629IgRWVim4y8Bh7JEQ0g8eZPyKiunEDK5Mj5HEuUeHKUfhWdFv/Zj+yGinfD9gJxprpDAt8agW+Ym28CgYAzpqJ96u4/HXSr2TtVSCZyP/jYJFRAM1ptqRbcBmu7Kl5uoeQzEw4KMnPi7tMBvS2CPOnneNJfAyx0Dl7scKvip+vML5vD9lds58PgA9Gu3Ia/G1OdQ2VSFcJOJihM87CbFYkfMDRjESYVW1c+fNakaB/j/aNqlq1A/JWxj+2aUwKBgQDxh9Fcp5MnaPzlpx/d9nvHBTs5o4zwz4SftJDuDB2ELBMiU48k1TbQeDqbkHXGO2DfRYp1+rSlt3k0mjy7g/r/aD+985EQnE4z1/rpTRBtLfxTDb72szmyjLpXMzbkusE6/7lPK9XuflODun03e6Jvlek5HT4GpoS3HfBlWvlYzQKBgQD8Ty5IVYk+12pA9ZYhYB5W4bVqQcuM64gUploYlRvHfzwuQ/osViGkhIfPuErttPeOXf65V+bvZe58P925rPpXpbWY8+sxfWVYqFCf8ePTB8WYoHkbEy2bKaTp4p+XFwQRy/SoB2KSTMo9VcK8iF3c5lKpA7d505NxzyNpZeaCjQ==",
                "json",
                "utf-8",
                "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAljb6rHchsCb/9L6cX9Hpiyt5XE4NCJ23s+3HQFnazrx/RKF7AnytESBNGr6Omvz+PJZXjolAGq6U3Fcm9JkRxnkpmIZ1PmZm4Nm1g2jhn2JCcBkK0sH7HN2MJO37zTf85NJZi0ZW+Rd/Ue3gCD3Q804ijAD1T/P84XBNyo7Dd4ozYITVg4LBvCtm7qJ5w3mXfruMW2K9wTkYpJJB2z86mgEJASUpDsyO1XvkMWi5my685XZuHSl0Dvk0dgur/u5FLWI3v7cI0Kus5WUZsI5bXALRWTfnjfpXHr+0cJi77CiZyLtKdYziEVvL2ifSYCbF/pQ/C2g8+ctNqgMA+QJ4RQIDAQAB",
                "RSA2"); //获得初始化的AlipayClient
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();//创建API对应的request
        //暂时使用的natapp，往后直接使用阿里云
        alipayRequest.setReturnUrl("http://m925k6.natappfree.cc/success");
        alipayRequest.setNotifyUrl("http://m925k6.natappfree.cc/notifyUrl");//在公共参数中设置回跳和通知地址
        alipayRequest.setBizContent("{" +
                "    \"out_trade_no\":\"" + order.getId() + "\"," +
                "    \"product_code\":\"FAST_INSTANT_TRADE_PAY\"," +
                "    \"total_amount\":"+order.getAccount()+"," +
                "    \"subject\":\""+order.getRemark()+"\"," +
                "    \"body\":\""+order.getRemark()+"\"," +
                "    \"passback_params\":\"merchantBizType%3d3C%26merchantBizNo%3d2016010101111\"," +
                "    \"extend_params\":{" +
                "    \"sys_service_provider_id\":\"2088511833207846\"" +
                "    }" +
                "  }");//填充业务参数
        String form = "";
        try {
            form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        httpResponse.setContentType("text/html;charset="+"UTF-8");
        httpResponse.getWriter().write(form);//直接将完整的表单html输出到页面
        httpResponse.getWriter().flush();
        httpResponse.getWriter().close();
    }

    //验签
    @RequestMapping("notifyUrl")
    @ResponseBody
    public void notifyUrl(HttpServletRequest request, HttpServletResponse response) throws AlipayApiException, IOException {
        //获取到请求中所有的键值对
        Map<String, String[]> parameterMapap = request.getParameterMap();
        //需要将map中的String[]==>String
        Map<String, String> paramsMap = new HashMap<>(); //将异步通知中收到的所有参数都存放到 map 中
        Set<Map.Entry<String, String[]>> entries = parameterMapap.entrySet();
        for (Map.Entry<String, String[]> entry : entries) {
            String[] values = entry.getValue();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < values.length - 1; i++) {
                sb.append(values[i] + ",");
            }
            //单独添加最后一个值，防止多个“，”号
            sb.append(values[values.length - 1]);
            //将key和value放入paramsMap中
            paramsMap.put(entry.getKey(), sb.toString());
        }

        boolean signVerified = AlipaySignature.rsaCheckV1(paramsMap,
                "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAljb6rHchsCb/9L6cX9Hpiyt5XE4NCJ23s+3HQFnazrx/RKF7AnytESBNGr6Omvz+PJZXjolAGq6U3Fcm9JkRxnkpmIZ1PmZm4Nm1g2jhn2JCcBkK0sH7HN2MJO37zTf85NJZi0ZW+Rd/Ue3gCD3Q804ijAD1T/P84XBNyo7Dd4ozYITVg4LBvCtm7qJ5w3mXfruMW2K9wTkYpJJB2z86mgEJASUpDsyO1XvkMWi5my685XZuHSl0Dvk0dgur/u5FLWI3v7cI0Kus5WUZsI5bXALRWTfnjfpXHr+0cJi77CiZyLtKdYziEVvL2ifSYCbF/pQ/C2g8+ctNqgMA+QJ4RQIDAQAB",
                "utf-8",
                "RSA2"); //调用SDK验证签名
        if (signVerified) {
            // 验签成功后，按照支付结果异步通知中的描述，对支付结果中的业务内容进行二次校验，校验成功后在response中返回success并继续商户自身业务处理，校验失败返回failure
            Gson gson = new Gson();
            HashMap<String, Object> jsonMap = new HashMap<>();
            HashMap<String, Object> map = new HashMap<>();
            map.put("code","10000");
            map.put("msg","Success");
            map.put("trade_no", paramsMap.get("trade_no"));
            map.put("out_trade_no",paramsMap.get("out_trade_no"));
            map.put("seller_id",paramsMap.get("seller_id"));
            map.put("total_amount",paramsMap.get("total_amount"));
            map.put("merchant_order_no",paramsMap.get("merchant_order_no"));
            jsonMap.put("alipay_trade_page_pay_response",map);
            jsonMap.put("sign","MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAljb6rHchsCb");
            Order order = (Order) request.getAttribute("order");
            //验签成功
            if (paramsMap.get("out_trade_no").equals("20200313105750009") &&
                    paramsMap.get("total_amount").equals("999999")) {
                System.out.println("金额正确，验签成功");//要去数据库中改变订单状态
                response.getWriter().write(gson.toJson(jsonMap));
            }
            //验签失败
            map.put("msg","failure");
            jsonMap.put("alipay_trade_page_pay_response",map);
            response.getWriter().write(gson.toJson(jsonMap));
        } else {
            // 验签失败则记录异常日志，并在response中返回failure.
            log.error("【验签失败】-->支付结果异常");
            //响应json
            Map<String, Object> jsonMap = new HashMap<>();
            Map<String, Object> map = new HashMap<>();
            Gson gson = new Gson();
            map.put("code","20000");
            map.put("msg","Service Currently Unavailable");
            map.put("sub_code","isp.unknow-error");
            map.put("sub_msg","系统繁忙");
            jsonMap.put("alipay_trade_page_pay_response",map);
            jsonMap.put("sign","MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAljb6rHchsCb");
            response.getWriter().write(gson.toJson(jsonMap));
        }

    }

}
