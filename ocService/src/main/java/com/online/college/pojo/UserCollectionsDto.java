package com.online.college.pojo;

public class UserCollectionsDto {

    /**
     *用户id
     **/
    private Integer userId;

    /**
     *用户收藏分类
     **/
    private Integer classify;

    /**
     * 用户收藏id
     */
    private Long objectId;

    /**
     *用户收藏备注
     **/
    private String tips;

    /**
     * 收藏名称
     */
    private String name;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getClassify() {
        return classify;
    }

    public void setClassify(Integer classify) {
        this.classify = classify;
    }

    public Long getObjectId() {
        return objectId;
    }

    public void setObjectId(Long objectId) {
        this.objectId = objectId;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
