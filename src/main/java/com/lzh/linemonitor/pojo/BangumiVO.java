package com.lzh.linemonitor.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @author: Lu Zhaohui
 * @description:
 * @date: Created on 2018/6/24
 * @modified By:
 */
@Data
public class BangumiVO {

    private String name;

    private String nameCn;

    private int    position;

    private int episode;

    private int similar;

    private String bilibiliUrl;

}
