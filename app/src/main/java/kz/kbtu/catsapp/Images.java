package kz.kbtu.catsapp;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

/**
 * Created by abakh on 17-Oct-17.
 */

@Root(name = "images", strict = false)
public class Images {

    @ElementList(name = "image", inline = true)
    ArrayList<Image> images;

    public ArrayList<Image> getImages() {
        return images;
    }

    public void setImages(ArrayList<Image> images) {
        this.images = images;
    }
}
