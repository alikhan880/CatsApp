package kz.kbtu.catsapp;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by abakh on 17-Oct-17.
 */
@Root(name = "response", strict = false)
public class ResponseCats {

    @Element(name = "data")
    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
