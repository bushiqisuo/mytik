package com.example.mytik.entity;

import java.io.Serializable;
import java.util.List;

public class NewsEntity implements Serializable{
    /**
     * newsId : 5
     * newsTitle : 《光环：无限》官方Q&A 无充值战利品，画质优化中
     * authorName : 聚玩社官方
     * headerUrl : https://sf6-ttcdn-tos.pstatp.com/img/pgc-image/2bfe5f2e082e415cb92a03cfcfcfead8~120x256.image
     * commentCount : 4
     * releaseDate : 2020-08-01 08:22:47
     * type : 2
     * thumbEntities : [{"thumbId":7,"thumbUrl":"http://p1-tt.byteimg.com/large/pgc-image/a456c50fff344122b1b20ed99026c3f8?from=pc","newsId":5},{"thumbId":8,"thumbUrl":"http://p3-tt.byteimg.com/large/pgc-image/02973348d57d4dfba2d001f82caa3fcc?from=pc","newsId":5},{"thumbId":9,"thumbUrl":"http://p1-tt.byteimg.com/large/pgc-image/7add3e2a4f754d0baccc607cde132b5f?from=pc","newsId":5}]
     * imgList : null
     */

    private int newsId;
    private String newsTitle;
    private String authorName;
    private String headerUrl;
    private int commentCount;
    private String releaseDate;
    private int type;
    private Object imgList;
    private List<ThumbEntitiesBean> thumbEntities;

    public int getNewsId() {
        return newsId;
    }

    public void setNewsId(int newsId) {
        this.newsId = newsId;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getHeaderUrl() {
        return headerUrl;
    }

    public void setHeaderUrl(String headerUrl) {
        this.headerUrl = headerUrl;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Object getImgList() {
        return imgList;
    }

    public void setImgList(Object imgList) {
        this.imgList = imgList;
    }

    public List<ThumbEntitiesBean> getThumbEntities() {
        return thumbEntities;
    }

    public void setThumbEntities(List<ThumbEntitiesBean> thumbEntities) {
        this.thumbEntities = thumbEntities;
    }

    public static class ThumbEntitiesBean {
        /**
         * thumbId : 7
         * thumbUrl : http://p1-tt.byteimg.com/large/pgc-image/a456c50fff344122b1b20ed99026c3f8?from=pc
         * newsId : 5
         */

        private int thumbId;
        private String thumbUrl;
        private int newsId;

        public int getThumbId() {
            return thumbId;
        }

        public void setThumbId(int thumbId) {
            this.thumbId = thumbId;
        }

        public String getThumbUrl() {
            return thumbUrl;
        }

        public void setThumbUrl(String thumbUrl) {
            this.thumbUrl = thumbUrl;
        }

        public int getNewsId() {
            return newsId;
        }

        public void setNewsId(int newsId) {
            this.newsId = newsId;
        }
    }
}
