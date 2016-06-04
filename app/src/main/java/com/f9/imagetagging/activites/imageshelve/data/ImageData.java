package com.f9.imagetagging.activites.imageshelve.data;

/**
 * Created by Milind Suryawanshi on 5/9/16.
 */
public class ImageData {
    private String imgPath;
    private int imgSmiley;

    public ImageData(String _imgPath, int _imgSmiley){
        this.imgPath = _imgPath;
        this.imgSmiley = _imgSmiley;
    }
    public ImageData(){

    }

    public int getImgSmiley() {
        return imgSmiley;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public void setImgSmiley(int imgSmiley) {
        this.imgSmiley = imgSmiley;
    }
}
