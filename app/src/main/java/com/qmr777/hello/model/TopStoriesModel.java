package com.qmr777.hello.model;

import java.util.List;

/**
 * Created by qmr777 on 16-4-9.
 */
public class TopStoriesModel {

    /**
     * date : 20160409
     * stories : [{"images":["http://pic4.zhimg.com/1309e6126e81f895e91bb69ba5df910f.jpg"],"type":0,"id":8126002,"ga_prefix":"040913","title":"朋友说大家一起凑钱做生意吧，你会回来看这篇"},{"title":"大误 · 说到微信启动画面，啧啧啧，那叫一个不简单","ga_prefix":"040912","images":["http://pic2.zhimg.com/e73278d6ffd5c724323bbb3d32956b65.jpg"],"multipic":true,"type":0,"id":8096837},{"images":["http://pic1.zhimg.com/32b1c6cc5164c86ce1685c5a1910b338.jpg"],"type":0,"id":8129035,"ga_prefix":"040911","title":"学英语的过程中，这本写作书让我醍醐灌顶"},{"images":["http://pic1.zhimg.com/457d8cc4204d3a8164c11db67b95f02c.jpg"],"type":0,"id":8095201,"ga_prefix":"040910","title":"春天出去玩绝对不能亏了嘴"},{"images":["http://pic1.zhimg.com/3a0f23fa00454f3f92c2090742a53368.jpg"],"type":0,"id":8127965,"ga_prefix":"040909","title":"啤酒小白第一次进酒吧，读了这篇就不虚"},{"images":["http://pic3.zhimg.com/ea52c63f94333969ec58f3f6c1c98e1e.jpg"],"type":0,"id":8127849,"ga_prefix":"040908","title":"为什么有的人的丁丁是弯的？"},{"images":["http://pic2.zhimg.com/18ad686407071d7d9765c8d5300ebd39.jpg"],"type":0,"id":8127978,"ga_prefix":"040907","title":"怀孕了，这些美食先别碰了"},{"images":["http://pic4.zhimg.com/bbf64809875504844c4730254773fadf.jpg"],"type":0,"id":8125948,"ga_prefix":"040907","title":"世界上最后一只雌斑鳖接受人工授精，它会灭绝吗？"},{"images":["http://pic3.zhimg.com/8ec80ceb2b6d2a2234a8f34a5dc93c0a.jpg"],"type":0,"id":8128054,"ga_prefix":"040907","title":"周末干什么 · 学几招解决上班久坐后遗症"},{"images":["http://pic1.zhimg.com/5446fb29bc8456dcf53063b9fce5e4cc.jpg"],"type":0,"id":8128166,"ga_prefix":"040907","title":"读读日报 24 小时热门 TOP 5 · 偷窥别人做爱，记下了比性爱更多的故事"},{"images":["http://pic2.zhimg.com/440abfe16961198cc0b959737619a199.jpg"],"type":0,"id":8122339,"ga_prefix":"040906","title":"瞎扯 · 如何正确地吐槽"}]
     * top_stories : [{"image":"http://pic4.zhimg.com/158fdf3ccfbdb674387c29e13b6bc9c7.jpg","type":0,"id":8129035,"ga_prefix":"040911","title":"学英语的过程中，这本写作书让我醍醐灌顶"},{"image":"http://pic3.zhimg.com/2a41f0c7f67a390fa030f98ecffc0472.jpg","type":0,"id":8128166,"ga_prefix":"040907","title":"读读日报 24 小时热门 TOP 5 · 偷窥别人做爱，记下了比性爱更多的故事"},{"image":"http://pic2.zhimg.com/5245a84f64503ad7a782d6a09c15edcd.jpg","type":0,"id":8095201,"ga_prefix":"040910","title":"春天出去玩绝对不能亏了嘴"},{"image":"http://pic4.zhimg.com/40e8b754f6349e2dfc4f870d9f6f2793.jpg","type":0,"id":8127849,"ga_prefix":"040908","title":"为什么有的人的丁丁是弯的？"},{"image":"http://pic4.zhimg.com/87cd68e50f71c839e1914f29cf1d3abb.jpg","type":0,"id":8126505,"ga_prefix":"040821","title":"集结一众男神的经典之作，王家卫的手笔确实神妙"}]
     */

    private String date;
    /**
     * images : ["http://pic4.zhimg.com/1309e6126e81f895e91bb69ba5df910f.jpg"]
     * type : 0
     * id : 8126002
     * ga_prefix : 040913
     * title : 朋友说大家一起凑钱做生意吧，你会回来看这篇
     */

    private List<StoriesBean> stories;
    /**
     * image : http://pic4.zhimg.com/158fdf3ccfbdb674387c29e13b6bc9c7.jpg
     * type : 0
     * id : 8129035
     * ga_prefix : 040911
     * title : 学英语的过程中，这本写作书让我醍醐灌顶
     */

    private List<TopStoriesBean> top_stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<StoriesBean> getStories() {
        return stories;
    }

    public void setStories(List<StoriesBean> stories) {
        this.stories = stories;
    }

    public List<TopStoriesBean> getTop_stories() {
        return top_stories;
    }

    public void setTop_stories(List<TopStoriesBean> top_stories) {
        this.top_stories = top_stories;
    }

    public static class StoriesBean {
        private int type;
        private int id;
        private String ga_prefix;
        private String title;
        private List<String> images;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }

    public static class TopStoriesBean {
        private String image;
        private int type;
        private int id;
        private String ga_prefix;
        private String title;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
