package com.lzh.linemonitor.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lzh.linemonitor.pojo.BangumiVO;
import com.lzh.linemonitor.utils.FileUtil;
import com.lzh.linemonitor.utils.OkHttpUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: Lu Zhaohui
 * @description:
 * @date: Created on 2018/6/22
 * @modified By:
 */
@RestController
@RequestMapping("/bangumi")
public class BangumiController {

    private int ONE_M = 1000*1000/2;

    @RequestMapping("/searchurl")
    public String searchByUrl(@RequestParam("picUrl") String picUrl) {

        String base64Pic = FileUtil.getImageStrFromUrl(picUrl);
        return searchByPic("data:image/jpeg;base64," + base64Pic);
    }

    @RequestMapping("/searchpic")
    public String searchByPic(@RequestParam("data") String base64Pic) {
        if (base64Pic.length() >  ONE_M) {
            return "pic too large";
        }

        Map<String, Object> param = new HashMap<>();
        param.put("image", base64Pic);
        String bangumiResult = OkHttpUtils.postForm("https://whatanime.ga/api/search?token=895a63d8443ddfda5af960b887d34e08369d8edb", param);
        JSONObject bangumiJson = JSON.parseObject(bangumiResult);
        bangumiJson = bangumiJson.getJSONArray("docs").getJSONObject(0);
        BangumiVO bangumiVO = new BangumiVO();
        bangumiVO.setName(bangumiJson.getString("anime"));
        bangumiVO.setNameCn(bangumiJson.getString("title_chinese"));
        bangumiVO.setEpisode(bangumiJson.getIntValue("episode"));
        bangumiVO.setPosition(bangumiJson.getIntValue("at"));
        return JSON.toJSONString(bangumiVO);
    }



    public static void main(String[] args) {
        String picUrl ="http://a1.att.hudong.com/13/99/01300000319642122854998891774.jpg";
        String base64Pic = FileUtil.getImageStrFromUrl(picUrl);
        Map<String, Object> param = new HashMap<>();
        param.put("image", "data:image/jpeg;base64,"+base64Pic);
        String result = OkHttpUtils.postForm("https://whatanime.ga/api/search?token=895a63d8443ddfda5af960b887d34e08369d8edb", param);
        System.out.println(result);
    }

}
