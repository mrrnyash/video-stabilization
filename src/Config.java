public class Config {
    private String videoFile;
    private int colormap;
    private int algorithm;
    private int shift;

    public void setVideoFile(String path) {
        this.videoFile = path;
    }

    public void setColormap(int option) {
        this.colormap = option;
    }

    public void setAlgorithm(int option) {
        this.algorithm = option;
    }

    public void setShift(int option) {
        this.shift = option;
    }

    public String getVideoFile() {
        return videoFile;
    }

    public int getColormap() {
        return colormap;
    }

    public int getAlgorithm() {
        return algorithm;
    }

    public int getShift() {
        return shift;
    }

}
