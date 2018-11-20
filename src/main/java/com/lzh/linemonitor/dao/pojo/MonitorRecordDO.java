package com.lzh.linemonitor.dao.pojo;


import java.util.Date;

public class MonitorRecordDO {

  private long id;
  private long lineId;
  private long duration;
  private Date createTime;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getLineId() {
    return lineId;
  }

  public void setLineId(long lineId) {
    this.lineId = lineId;
  }


  public long getDuration() {
    return duration;
  }

  public void setDuration(long duration) {
    this.duration = duration;
  }


  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

}
