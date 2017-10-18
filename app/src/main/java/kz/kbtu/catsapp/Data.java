package kz.kbtu.catsapp;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by abakh on 17-Oct-17.
 */

@Root(name = "data", strict = false)
public class Data {

    @Element(name = "images")
    private Images images;

    public Images getImages() {
        return images;
    }

    public void setImages(Images images) {
        this.images = images;
    }
}
