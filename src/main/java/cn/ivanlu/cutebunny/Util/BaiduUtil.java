package cn.ivanlu.cutebunny.Util;

import cn.ivanlu.cutebunny.Model.IdCardModel;
import com.baidu.aip.ocr.AipOcr;
import org.json.JSONObject;

import java.util.HashMap;

public class BaiduUtil {
    private static final String APP_ID = "11297963";
    private static final String API_KEY = "C75tnh3CDWMMY3ArxD3LCQd0";
    private static final String SECRET_KEY = "vCtHGKtT4QfCLotXmvPiRvPDiXoY5EnP";

    private static AipOcr client = null;

    private static AipOcr getClient() {
        if (client == null) {
            client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);
        }
        return client;
    }

    public static IdCardModel processIdCard(byte[] img) {
        HashMap<String, String> options = new HashMap<>();
        options.put("detect_direction", "true");
        options.put("detect_risk", "false");
        try {
            JSONObject res = getClient().idcard(img, "front", options);
            IdCardModel model = new IdCardModel();
            model.setId(res.getJSONObject("words_result").getJSONObject("公民身份号码").getString("words"));
            model.setName(res.getJSONObject("words_result").getJSONObject("姓名").getString("words"));
            model.setNation(res.getJSONObject("words_result").getJSONObject("民族").getString("words"));
            model.setSex(res.getJSONObject("words_result").getJSONObject("性别").getString("words"));
            model.setAddress(res.getJSONObject("words_result").getJSONObject("住址").getString("words"));
            return model;
        } catch (Exception e) {
            return null;
        }
    }
}
