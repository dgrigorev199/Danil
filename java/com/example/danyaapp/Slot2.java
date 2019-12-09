package com.example.danyaapp;

public class Slot2 {

    public Slot2(String korztitle,String korzopisanie,int korzimageView){
        this.korztitle = korztitle;
        this.korzopisanie = korzopisanie;
        this.korzimageView = korzimageView;
    }
    public String getKorztitle() {
        return korztitle;
    }

    public void setKorztitle(String korztitle) {
        this.korztitle = korztitle;
    }

    public String getKorzopisanie() {
        return korzopisanie;
    }

    public void setKorzopisanie(String korzopisanie) {
        this.korzopisanie = korzopisanie;
    }

    public int getKorzimageView() {
        return korzimageView;
    }

    public void setKorzimageView(int korzimageView) {
        this.korzimageView = korzimageView;
    }

    public String korztitle;
    public String korzopisanie;
    public int korzimageView;

}
