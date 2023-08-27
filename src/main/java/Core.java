import Objects.ClientProduct;
import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

public class Core {

    private static final Gson gson = new Gson();

    /**
     * Верификация подлинности плагина на серверах TAIGO.ID
     * Рекомендуется инициализировать в методе OnEnable() мода или плагина.
     *
     * @param order_id ID продукта. ID продукта должен загружаться из поля order_id в конфиге плагина.
     * @param Token Ключ плагина. Ключ должен загружаться из поля token в конфиге плагина.
     */
    public static void init(int order_id, String Token) {
        HttpClient httpclient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost("https://id.taigo.xyz/api/validation/plugins/" + order_id);

        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("Token", Token));
        HttpResponse response = Utils.ExecuteResponse(httppost, httpclient, params);
        String content = Utils.ReadBody(response);

        if(response == null || content == null)
            Logger.ConnectError();

        assert response != null;
        int code = response.getStatusLine().getStatusCode();
        if(code == 404)
            Logger.NotFound();

        ClientProduct product = gson.fromJson(content, ClientProduct.class);
        if(product.IsBlocked)
            Logger.Blocked();
        else
            Logger.SuccessLogin(product);
    }



}
