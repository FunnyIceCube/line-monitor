package com.lzh.linemonitor.pojo;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author: Lu Zhaohui
 * @description:
 * @date: Created on 2018/6/24
 * @modified By:
 */
public class BangumiVO {

    private String name;

    private String nameCn;

    private int    position;

    private int episode;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getEpisode() {
        return episode;
    }

    public void setEpisode(int episode) {
        this.episode = episode;
    }

    public String getNameCn() {
        return nameCn;
    }

    public void setNameCn(String nameCn) {
        this.nameCn = nameCn;
    }
}
