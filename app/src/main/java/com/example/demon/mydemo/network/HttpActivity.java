package com.example.demon.mydemo.network;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.demon.mydemo.R;
import com.example.demon.mydemo.util.BaseActivity;
import com.example.demon.mydemo.util.HttpUtil;
import com.example.demon.mydemo.util.LogUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import javax.xml.parsers.SAXParserFactory;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 使用HttpURLConnection获取请求的数据
 */
public class HttpActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "HttpActivity";
    private static String XMLDATAURL = "http://123.206.23.219/Android/XML/get-data.xml";
    private static String JSONDATAURL = "http://123.206.23.219/Android/JSON/get-data.json";
    private EditText editText;
    private TextView textView;
//    private int selectBt;       //选择解析类型按钮

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.network_http_activity);

        editText = findViewById(R.id.http_et);
        textView = findViewById(R.id.http_tv);

        findViewById(R.id.http_connection_bt).setOnClickListener(this);
        findViewById(R.id.ok_http_bt).setOnClickListener(this);

        findViewById(R.id.xml_pull_bt).setOnClickListener(this);
        findViewById(R.id.xml_sax_bt).setOnClickListener(this);

        findViewById(R.id.json_object_bt).setOnClickListener(this);
        findViewById(R.id.gson_bt).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        textView.setText("加载中...\n");
//        selectBt = view.getId();

        switch (view.getId()) {
            case R.id.http_connection_bt:
                String url1 = editText.getText().toString();
                sendRequestWithHttpURLConnection(url1);
                break;
            case R.id.ok_http_bt:
                String url2 = editText.getText().toString();
                sendRequestWithOkHttp(url2);
                break;

            case R.id.xml_pull_bt:
                // 放入需要解析的xml数据地址
//                sendRequestWithOkHttp(XMLDATAURL);
                HttpUtil.sendHttpRequest(XMLDATAURL, new HttpUtil.HttpCallbackListener() {
                    @Override
                    public void onFinish(String response) {
                        parseXMLWithPull(response);
                    }

                    @Override
                    public void onError(Exception e) {

                    }
                });
                break;
            case R.id.xml_sax_bt:
//                sendRequestWithOkHttp(XMLDATAURL);
                HttpUtil.sendOkHttpRequest(XMLDATAURL, new okhttp3.Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        parseXMLWithSAX(response.body().string());
                    }
                });
                break;

            case R.id.json_object_bt:
                // 放入需要解析的json数据地址
//                sendRequestWithOkHttp(JSONDATAURL);
                HttpUtil.sendHttpRequest(JSONDATAURL, new HttpUtil.HttpCallbackListener() {
                    @Override
                    public void onFinish(String response) {
                        parseJSONWithJSONObject(response);
                    }

                    @Override
                    public void onError(Exception e) {

                    }
                });
                break;
            case R.id.gson_bt:
//                sendRequestWithOkHttp(JSONDATAURL);
                HttpUtil.sendOkHttpRequest(JSONDATAURL, new okhttp3.Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        parseJSONWithGSON(response.body().string());
                    }
                });
                break;

        }
    }

    // 调用普通的HttpConnection来获取Http响应报文
    private void sendRequestWithHttpURLConnection(final String requestUrl) {
        // 开启线程来发起网络请求
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                BufferedReader reader = null;
                try {
                    URL url = new URL(requestUrl);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET"); //设置请求类型
                    connection.setConnectTimeout(8000); //链接超时时间
                    connection.setReadTimeout(8000);    //读取超时时间
                    InputStream in = connection.getInputStream();
                    // 下面对获取到的输入流进行读取
                    reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    showResponse(response.toString());     //线程里不能操作UI
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }

    // 使用OkHttp获取响应报文,一般使用这个
    private void sendRequestWithOkHttp(final String requestUrl) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            // 指定访问的服务器地址是电脑本机
                            .url(requestUrl)// "http://10.0.2.2/get_data.json"
                            .build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    showResponse(responseData);

//                    switch (selectBt) {  //选择解析方式
//                        case R.id.ok_http_bt:
//                            showResponse(responseData);
//                            break;
//                        case R.id.xml_pull_bt:
//                            parseXMLWithPull(responseData);
//                            break;
//                        case R.id.xml_sax_bt:
//                            parseXMLWithSAX(responseData);
//                            break;
//                        case R.id.json_object_bt:
//                            parseJSONWithJSONObject(responseData);
//                            break;
//                        case R.id.gson_bt:
//                            parseJSONWithGSON(responseData);
//                            break;
//
//                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    //将线程切换到主线程更新Ui的数据
    private void showResponse(final String response) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // 在这里进行UI操作，将结果显示到界面上
                textView.setText(response);
            }
        });
    }

    //Xml的pull解析
    private void parseXMLWithPull(final String xmlData) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                    XmlPullParser xmlPullParser = factory.newPullParser();
                    xmlPullParser.setInput(new StringReader(xmlData));
                    int eventType = xmlPullParser.getEventType();   // 获取解析事件
                    String id = "";
                    String name = "";
                    String version = "";
                    textView.setText("XMLWithPull\n\n");
                    while (eventType != XmlPullParser.END_DOCUMENT) {
                        String nodeName = xmlPullParser.getName();
                        switch (eventType) {
                            // 开始解析某个结点
                            case XmlPullParser.START_TAG: {
                                switch (nodeName) {
                                    case "id":
                                        id = xmlPullParser.nextText();
                                        break;
                                    case "name":
                                        name = xmlPullParser.nextText();
                                        break;
                                    case "version":
                                        version = xmlPullParser.nextText();
                                        break;
                                }
//                                if ("id".equals(nodeName)) {
//                                    id = xmlPullParser.nextText();
//                                } else if ("name".equals(nodeName)) {
//                                    name = xmlPullParser.nextText();
//                                } else if ("version".equals(nodeName)) {
//                                    version = xmlPullParser.nextText();
//                                }
                                break;
                            }
                            // 完成解析某个结点
                            case XmlPullParser.END_TAG: {
                                if ("app".equals(nodeName)) {
                                    textView.append("id is： " + id + "\n");
                                    textView.append("name is： " + name + "\n");
                                    textView.append("version is： " + version + "\n\n");

                                    LogUtil.d(TAG, "id is： " + id);
                                    LogUtil.d(TAG, "name is： " + name);
                                    LogUtil.d(TAG, "version is： " + version);
                                }
                                break;
                            }
                            default:
                                break;
                        }
                        eventType = xmlPullParser.next();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    //使用SAX解析XML
    private void parseXMLWithSAX(String xmlData) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            XMLReader xmlReader = factory.newSAXParser().getXMLReader();
            ContentHandler handler = new ContentHandler();
            // 将ContentHandler的实例设置到XMLReader中
            xmlReader.setContentHandler(handler);
            // 开始执行解析
            xmlReader.parse(new InputSource(new StringReader(xmlData)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class ContentHandler extends DefaultHandler {
        private String nodeName;
        // 使用到线程时不能用StringBuilder
        private StringBuilder id;
        private StringBuilder name;
        private StringBuilder version;

        // 开始Xml解析
        @Override
        public void startDocument() throws SAXException {
            id = new StringBuilder();
            name = new StringBuilder();
            version = new StringBuilder();
        }

        // 开始解析某个节点
        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            // 记录当前结点名
            nodeName = localName;
        }

        // 获取节点中的内容
        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            // 根据当前的结点名判断将内容添加到哪一个StringBuilder对象中
            switch (nodeName) {
                case "id":
                    id.append(ch, start, length);
                    break;
                case "name":
                    name.append(ch, start, length);
                    break;
                case "version":
                    version.append(ch, start, length);
                    break;
            }

//            if ("id".equals(nodeName)) {
//                id.append(ch, start, length);
//            } else if ("name".equals(nodeName)) {
//                name.append(ch, start, length);
//            } else if ("version".equals(nodeName)) {
//                version.append(ch, start, length);
//            }
        }

        // 完成解析某个节点
        @Override
        public void endElement(String uri, final String localName, String qName) throws SAXException {
            if ("app".equals(localName)) {
                LogUtil.d(TAG, "id is： " + id.toString().trim());
                LogUtil.d(TAG, "name is： " + name.toString().trim());
                LogUtil.d(TAG, "version is： " + version.toString().trim());

                // 最后要将StringBuilder清空掉
//                id.setLength(0);
//                name.setLength(0);
//                version.setLength(0);
            }
        }

        //完成整个Xml解析时
        @Override
        public void endDocument() throws SAXException {
            super.endDocument();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    textView.setText("XMLWithSAX\n\n");
                    textView.append("id is： \n" + id.toString().trim() + "\n");
                    textView.append("name is： \n" + name.toString().trim() + "\n");
                    textView.append("version is： \n" + version.toString().trim() + "\n\n");
                }
            });
        }
    }

    // 使用JsonObject解析json数据
    private void parseJSONWithJSONObject(final String jsonData) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    textView.setText("JsonObject\n\n");
                    JSONArray jsonArray = new JSONArray(jsonData);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        String id = jsonObject.getString("id");
                        String name = jsonObject.getString("name");
                        String version = jsonObject.getString("version");

                        textView.append("id is: " + id + "\n");
                        textView.append("name is: " + name + "\n");
                        textView.append("version is: " + version + "\n\n");

                        LogUtil.d(TAG, "id is: " + id);
                        LogUtil.d(TAG, "name is: " + name);
                        LogUtil.d(TAG, "version is: " + version);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    // Gson解析Json时所映射到的类
    class App {
        private String id;
        private String name;
        private String version;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }
    }

    // Gson解析Json
    private void parseJSONWithGSON(final String jsonData) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textView.setText("Gson\n\n");

                Gson gson = new Gson();
                // 若只解析一条数据App app = gson.fromJson(jsonData, App.class);
                List<App> appList = gson.fromJson(jsonData, new TypeToken<List<App>>() {
                }.getType());

                for (App app : appList) {
                    textView.append("id is: " + app.getId() + "\n");
                    textView.append("name is: " + app.getName() + "\n");
                    textView.append("version is: " + app.getVersion() + "\n\n");

                    LogUtil.d(TAG, "id is: " + app.getId());
                    LogUtil.d(TAG, "name is: " + app.getName());
                    LogUtil.d(TAG, "version is: " + app.getVersion());
                }
            }
        });
    }
}
