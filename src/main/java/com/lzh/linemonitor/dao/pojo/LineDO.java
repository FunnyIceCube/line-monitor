package com.lzh.linemonitor.dao.pojo;


import java.util.Date;

public class LineDO {

  private long id;
  private String poiStartName;
  private String poiStartLoc;
  private String poiEndName;
  private String poiEndLoc;
  private long ownerId;
  private String ownerName;
  private Date createTime;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getPoiStartName() {
    return poiStartName;
  }

  public void setPoiStartName(String poiStartName) {
    this.poiStartName = poiStartName;
  }


  public String getPoiStartLoc() {
    return poiStartLoc;
  }

  public void setPoiStartLoc(String poiStartLoc) {
    this.poiStartLoc = poiStartLoc;
  }


  public String getPoiEndName() {
    return poiEndName;
  }

  public void setPoiEndName(String poiEndName) {
    this.poiEndName = poiEndName;
  }


  public String getPoiEndLoc() {
    return poiEndLoc;
  }

  public void setPoiEndLoc(String poiEndLoc) {
    this.poiEndLoc = poiEndLoc;
  }


  public long getOwnerId() {
    return ownerId;
  }

  public void setOwnerId(long ownerId) {
    this.ownerId = ownerId;
  }


  public String getOwnerName() {
    return ownerName;
  }

  public void setOwnerName(String ownerName) {
    this.ownerName = ownerName;
  }


  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

}
