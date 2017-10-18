package kz.kbtu.catsapp;

import android.os.Parcel;
import android.os.Parcelable;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by abakh on 17-Oct-17.
 */

@Root(name = "image", strict = false)
public class Image implements Parcelable {
    @Element(name = "url", required = false)
    private String url;

    @Element(name = "id", required = false)
    private String id;

    @Element(name = "source_url", required = false)
    private String sourceUrl;


    public Image() {
    }

    protected Image(Parcel in) {
        url = in.readString();
        id = in.readString();
        sourceUrl = in.readString();
    }

    public static final Creator<Image> CREATOR = new Creator<Image>() {
        @Override
        public Image createFromParcel(Parcel in) {
            return new Image(in);
        }

        @Override
        public Image[] newArray(int size) {
            return new Image[size];
        }
    };

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(url);
        dest.writeString(id);
        dest.writeString(sourceUrl);
    }
}
