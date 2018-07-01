package resource;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by MiaoZhuang on 2016/5/25.
 */
public class URLResource implements Resource {

    private String location = null;

    private URL url = null;

    public URLResource(String location) {
        this.location = location;
        url = this.getClass().getClassLoader().getResource(location);
    }

    @Override
    public InputStream getInputStream() {
        URLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = url.openConnection();
            urlConnection.connect();
            inputStream = urlConnection.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputStream;
    }
}
