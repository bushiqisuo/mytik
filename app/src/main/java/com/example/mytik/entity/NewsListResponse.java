package com.example.mytik.entity;

import java.io.Serializable;
import java.util.List;

public class NewsListResponse implements Serializable {
    /**
     * msg : success
     * code : 0
     * page : {"totalCount":8,"pageSize":4,"totalPage":2,"currPage":2,"list":[{"newsId":5,"newsTitle":"《光环：无限》官方Q&A 无充值战利品，画质优化中","authorName":"聚玩社官方","headerUrl":"https://sf6-ttcdn-tos.pstatp.com/img/pgc-image/2bfe5f2e082e415cb92a03cfcfcfead8~120x256.image","commentCount":4,"releaseDate":"2020-08-01 08:22:47","type":2,"thumbEntities":[{"thumbId":7,"thumbUrl":"http://p1-tt.byteimg.com/large/pgc-image/a456c50fff344122b1b20ed99026c3f8?from=pc","newsId":5},{"thumbId":8,"thumbUrl":"http://p3-tt.byteimg.com/large/pgc-image/02973348d57d4dfba2d001f82caa3fcc?from=pc","newsId":5},{"thumbId":9,"thumbUrl":"http://p1-tt.byteimg.com/large/pgc-image/7add3e2a4f754d0baccc607cde132b5f?from=pc","newsId":5}],"imgList":null},{"newsId":6,"newsTitle":"2020小编个人力推的耐玩的养老游戏","authorName":"游戏我看看","headerUrl":"https://sf3-ttcdn-tos.pstatp.com/img/mosaic-legacy/dc0d0001fca5e747f267~120x256.image","commentCount":7,"releaseDate":"2020-07-30 12:48:37","type":1,"thumbEntities":[{"thumbId":10,"thumbUrl":"http://p1-tt.byteimg.com/large/pgc-image/b957bfacdd134aa9a1a7e47d40d1be4b?from=pc","newsId":6}],"imgList":null},{"newsId":7,"newsTitle":"NBA复赛赛况：开拓者加时擒灰熊，太阳胜奇才，魔术\u201c主场\u201d破网","authorName":"头条专题","headerUrl":"https://sf1-ttcdn-tos.pstatp.com/img/mosaic-legacy/ffbc0000ad1e717b76a6~120x256.image","commentCount":23,"releaseDate":"2020-08-01 06:49:44","type":1,"thumbEntities":[{"thumbId":11,"thumbUrl":"https://p3.pstatp.com/list/190x124/pgc-image/2b5f07505b67498e83e2faa32d637e5c","newsId":7}],"imgList":null},{"newsId":8,"newsTitle":"NBA最有含金量总冠军？奥拉朱旺95年4次以下克上！无冠军超过2次","authorName":"网罗篮球","headerUrl":"https://sf6-ttcdn-tos.pstatp.com/img/pgc-image/9f11654ff6184afd8941bcf7ccd3c5dd~120x256.image","commentCount":45,"releaseDate":"2020-05-23 14:08:09","type":2,"thumbEntities":[{"thumbId":12,"thumbUrl":"https://p3.pstatp.com/list/190x124/pgc-image/2b5f07505b67498e83e2faa32d637e5c","newsId":8},{"thumbId":13,"thumbUrl":"https://p3.pstatp.com/list/190x124/pgc-image/2b5f07505b67498e83e2faa32d637e5c","newsId":8},{"thumbId":14,"thumbUrl":"https://p3.pstatp.com/list/190x124/pgc-image/2b5f07505b67498e83e2faa32d637e5c","newsId":8}],"imgList":null}]}
     */

    private String msg;
    private int code;
    private PageBean page;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public PageBean getPage() {
        return page;
    }

    public void setPage(PageBean page) {
        this.page = page;
    }

    public static class PageBean {
        /**
         * totalCount : 8
         * pageSize : 4
         * totalPage : 2
         * currPage : 2
         * list : [{"newsId":5,"newsTitle":"《光环：无限》官方Q&A 无充值战利品，画质优化中","authorName":"聚玩社官方","headerUrl":"https://sf6-ttcdn-tos.pstatp.com/img/pgc-image/2bfe5f2e082e415cb92a03cfcfcfead8~120x256.image","commentCount":4,"releaseDate":"2020-08-01 08:22:47","type":2,"thumbEntities":[{"thumbId":7,"thumbUrl":"http://p1-tt.byteimg.com/large/pgc-image/a456c50fff344122b1b20ed99026c3f8?from=pc","newsId":5},{"thumbId":8,"thumbUrl":"http://p3-tt.byteimg.com/large/pgc-image/02973348d57d4dfba2d001f82caa3fcc?from=pc","newsId":5},{"thumbId":9,"thumbUrl":"http://p1-tt.byteimg.com/large/pgc-image/7add3e2a4f754d0baccc607cde132b5f?from=pc","newsId":5}],"imgList":null},{"newsId":6,"newsTitle":"2020小编个人力推的耐玩的养老游戏","authorName":"游戏我看看","headerUrl":"https://sf3-ttcdn-tos.pstatp.com/img/mosaic-legacy/dc0d0001fca5e747f267~120x256.image","commentCount":7,"releaseDate":"2020-07-30 12:48:37","type":1,"thumbEntities":[{"thumbId":10,"thumbUrl":"http://p1-tt.byteimg.com/large/pgc-image/b957bfacdd134aa9a1a7e47d40d1be4b?from=pc","newsId":6}],"imgList":null},{"newsId":7,"newsTitle":"NBA复赛赛况：开拓者加时擒灰熊，太阳胜奇才，魔术\u201c主场\u201d破网","authorName":"头条专题","headerUrl":"https://sf1-ttcdn-tos.pstatp.com/img/mosaic-legacy/ffbc0000ad1e717b76a6~120x256.image","commentCount":23,"releaseDate":"2020-08-01 06:49:44","type":1,"thumbEntities":[{"thumbId":11,"thumbUrl":"https://p3.pstatp.com/list/190x124/pgc-image/2b5f07505b67498e83e2faa32d637e5c","newsId":7}],"imgList":null},{"newsId":8,"newsTitle":"NBA最有含金量总冠军？奥拉朱旺95年4次以下克上！无冠军超过2次","authorName":"网罗篮球","headerUrl":"https://sf6-ttcdn-tos.pstatp.com/img/pgc-image/9f11654ff6184afd8941bcf7ccd3c5dd~120x256.image","commentCount":45,"releaseDate":"2020-05-23 14:08:09","type":2,"thumbEntities":[{"thumbId":12,"thumbUrl":"https://p3.pstatp.com/list/190x124/pgc-image/2b5f07505b67498e83e2faa32d637e5c","newsId":8},{"thumbId":13,"thumbUrl":"https://p3.pstatp.com/list/190x124/pgc-image/2b5f07505b67498e83e2faa32d637e5c","newsId":8},{"thumbId":14,"thumbUrl":"https://p3.pstatp.com/list/190x124/pgc-image/2b5f07505b67498e83e2faa32d637e5c","newsId":8}],"imgList":null}]
         */

        private int totalCount;
        private int pageSize;
        private int totalPage;
        private int currPage;
        private List<NewsEntity> list;

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }

        public int getCurrPage() {
            return currPage;
        }

        public void setCurrPage(int currPage) {
            this.currPage = currPage;
        }

        public List<NewsEntity> getList() {
            return list;
        }

        public void setList(List<NewsEntity> list) {
            this.list = list;
        }
    }
}
