package vn.ptt.banner;

public class Banner {
    private String title;
    private int imageResId;
    private String imageUrl;
    private int layoutResId;

    public Banner(String title, int imageResId, String imageUrl, int layoutResId) {
        this.title = title;
        this.imageResId = imageResId;
        this.imageUrl = imageUrl;
        this.layoutResId = layoutResId;
    }

    public Banner(String title, int imageResId, int layoutResId) {
        this.title = title;
        this.imageResId = imageResId;
        this.layoutResId = layoutResId;
    }

    public String getTitle() {
        return title;
    }

    public int getImageResId() {
        return imageResId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public int getLayoutResId() {
        return layoutResId;
    }
}
