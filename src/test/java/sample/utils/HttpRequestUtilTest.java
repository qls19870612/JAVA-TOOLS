package sample.utils;

import com.alibaba.fastjson.JSONObject;
import com.mysql.cj.xdevapi.JsonArray;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 * 创建人  liangsong
 * 创建时间 2020/08/11 16:46
 */
public class HttpRequestUtilTest {
    @Test
    public void name() {
        StringBuilder params = new StringBuilder();
//        $url = "http://localhost:8080/gateway.php";
//        $filed=array(
//                'cmd'=> 'QQ_VIEW_VIDEO',
//                'dest'=> 'qq_platform',
//                'playerId'=> '32',
//                'type'=> '1',
//);
        params.append("cmd=").append("QQ_VIEW_VIDEO").append("&");
        params.append("dest=").append("qq_platform").append("&");
        params.append("playerId=").append("32").append("&");
        params.append("type=").append("1");
        String s = HttpRequestUtil.sendGet("http://localhost:8080/gateway.php", params.toString());
        System.out.println(s);
    }
}