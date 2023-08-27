import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.util.EntityUtils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

public class Utils {

    public static String ReadBody(HttpResponse response){
        try {
            HttpEntity entity = response.getEntity();
            return EntityUtils.toString(entity);
        }
        catch (Exception ex){
            StringWriter sw = new StringWriter();
            ex.printStackTrace(new PrintWriter(sw));
            Logger.Info("Error sending a request to the server: \n" + sw.toString());
            return null;
        }
    }

    public static HttpResponse ExecuteResponse(HttpPost httppost, HttpClient httpclient, List<NameValuePair> params){
        try {
            httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
            return httpclient.execute(httppost);
        }
        catch (Exception ex){
            StringWriter sw = new StringWriter();
            ex.printStackTrace(new PrintWriter(sw));
            Logger.Info("Error sending a request to the server: \n" + sw.toString());
            return null;
        }
    }


}
