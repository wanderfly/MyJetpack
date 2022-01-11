package com.kevin.retrofit;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import androidx.annotation.NonNull;

import com.kevin.retrofit.sample2.SampleBuilder;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.TestOnly;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.Okio;
import okio.Source;

/**
 * @author Kevin  2020/8/26
 */
public final class OkHttpClientUtil {
    private static final boolean DEBUG = true;
    private static final String TAG = OkHttpClientUtil.class.getSimpleName();
    public static final MediaType JSON_MEDIA_TYPE = MediaType.parse("application/json; charset=utf-8");

    private OkHttpClientUtil() {
    }

    /**
     * 通过ping判断服务器是否可用
     * Note:网络正常响应很快，但是服务器不可用时，会等待相当长的时间
     * 所以应该放到非UI线程中去执行
     */
    public static boolean ping(String serverIp) {
        try {
            Process p = Runtime.getRuntime().exec("ping -c 1 -w 100 " + serverIp);
            InputStream input = p.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(input));
            StringBuilder stringBuilder = new StringBuilder();
            String content;
            while ((content = in.readLine()) != null) {
                stringBuilder.append(content);
            }
            int status = p.waitFor();
            Log.e(TAG, "ping: " + stringBuilder.toString());
            if (status == 0) {
                return true;
            }
        } catch (IOException | InterruptedException e) {
            Log.e(TAG, "ping: " + e.toString());
        }
        return false;
    }

    public static boolean pingDefault() {
        return ping("192.168.2.55");
    }


    /**
     * 判断网络是否连通
     */
    public static boolean isNetworkConnected(Context context) {
        try {
            if (context != null) {
                ConnectivityManager cm = (ConnectivityManager) context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo info = cm.getActiveNetworkInfo();
                return info != null && info.isConnected();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean isWifiConnected(Context context) {
        if (context != null) {
            ConnectivityManager cm = (ConnectivityManager) context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info = cm.getActiveNetworkInfo();
            return info != null && (info.getType() == ConnectivityManager.TYPE_WIFI);
        } else {
            return false;
        }
    }

    public static String parseFailure(Exception e) {
        if (e != null) {
            String info = e.toString();
            if (info.contains("SocketTimeoutException")) {
                return "连接服务器超时";
            } else if (info.contains("ConnectException")) {
                return "连接服务器出错";
            }
            return "网络异常";
        }
        return "异常不能为null";
    }

    public static OkHttpClient generateOkHttpClient(@NonNull String url) {

        boolean isHttps = url.startsWith("https:");
        if (isHttps) {
            return new OkHttpClient
                    .Builder()
                    .sslSocketFactory(createSSLSocketFactory(), new TrustAllCerts())
                    .hostnameVerifier(new TrustAllHostnameVerifier())
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .build();
        } else {
            return new OkHttpClient
                    .Builder()
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .build();
        }
    }

    public static void OkHttpClientStart(String url, Request request, Callback callback) {
        generateOkHttpClient(url).newCall(request).enqueue(callback);
    }


    /**
     * 异步请求(get方式)
     *
     * @param url      请求地址
     * @param callback 请求回调接口
     */
    public static void okHttpGet(String url, Callback callback) {
        if (DEBUG) Log.e(TAG, "okHttpGet: " + url);
        Request request = new Request
                .Builder()
                .url(url)
                .build();
        OkHttpClientStart(url, request, callback);
    }


    /**
     * 异步请求
     * 向服务器进行Post请求 (Json 以Json作为请求体)
     *
     * @param url        请求地址
     * @param jsonString 向服务器提交的json对象对应的字符串
     * @param callback   请求状态回调
     */
    public static void okHttpPostJson(String url, String jsonString, Callback callback) {
        if (DEBUG) {
            Log.e(TAG, "okHttpPostJson: " + url);
            Log.e(TAG, "okHttpPostJson: " + jsonString);
        }

        RequestBody body = RequestBody.create(jsonString, JSON_MEDIA_TYPE);
        Request request = new Request.Builder()
                .post(body)
                .url(url)
                .build();

        OkHttpClientStart(url, request, callback);
    }

    /**
     * Test
     * 测试账户登录
     */
    @TestOnly
    public static void okHttpPostFormAccount(String url, String verification, String captchaToken, Callback callback) {
        FormBody formBody = new FormBody.Builder()
                .add("password", "111111")
                .add("tenantCode", "10001")
                .add("code", verification)
                .add("captchaToken", captchaToken)
                .add("username", "admin")
                .build();

        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();

        OkHttpClientStart(url, request, callback);
        //okHttpClient.newCall(request).execute();
    }

    /**
     * 异步请求
     * 向服务器进行Post请求 (Form 以表单方式作为请求体)
     *
     * @param url      请求地址
     * @param formBody 请求体(该请求体可以通过Bean支持该请求的对象获取)
     * @param callback 请求回调
     */
    public static void okHttpPostForm(String url, FormBody formBody, Callback callback) {
        if (DEBUG) Log.e(TAG, "okHttpPostForm: " + url);
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();

        OkHttpClientStart(url, request, callback);
    }

    public static final String mobile_url = SampleBuilder.BaseUrl +"auth/three/login";
    public static final String image_code_url = SampleBuilder.BaseUrl +"/auth/oauth/code/captcha";

    /**
     * Test
     * 测试手机号登录
     */
    @TestOnly
    public static void okHttpPostFormMobile(String url, String phoneNumber, String mobileVerificationCode, Callback callback) {
        FormBody formBody = new FormBody.Builder()
                .add("type", "mobile")
                .add("clientType","app") //商家手机号登录
                //.add("clientType","app_customer") //用户号手机登录
                .add("mobile", phoneNumber)
                .add("code", mobileVerificationCode)
                .build();

        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();

        OkHttpClientStart(url, request, callback);

    }


    /**
     * Test
     * 获取账户登录时json数据
     */
    @TestOnly
    public static String getLoginAccountPostJsonString(String verification, String captchaToken) {
        Map<String, String> map = new HashMap<>();
        map.put("password", "111111");
        map.put("tenantCode", "10001");
        //map.put("code", "YRES");
        //map.put("captchaToken", "e5ce1982-5bb7-4b8e-b4d5-c497ba523d01");

        map.put("code", verification);
        map.put("captchaToken", captchaToken);
        map.put("username", "admin");
        String account = new JSONObject(map).toString();
        Log.d(TAG, "getLoginAccountPostJsonString: " + account);
        return account;
    }


    /**
     * 通过okHttp下载文件
     *
     * @param url          下载的服务器地址
     * @param saveFileDir  在设备上存储的目录
     * @param saveFileName 在设备上存储的名字
     * @param listener     下载状态监听接口
     */
    public static void okHttpDownload(String url, final String saveFileDir, final String saveFileName, final OnHttpDownloadListener listener) {
        if (DEBUG) Log.e(TAG, "okHttpDownload: url:" + url);
        Request request = new Request.Builder()
                .url(url)
                .build();

        OkHttpClientStart(url, request, new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                if (listener != null)
                    listener.onDownloadFailed(e);
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) {
                InputStream inputStream = null;
                FileOutputStream fos = null;
                byte[] buf = new byte[3048];
                int length;

                //储存文件下载的目录
                File dir = new File(saveFileDir);
                if (!dir.exists()) {
                    boolean isSuccess = dir.mkdirs();
                    Log.e(TAG, "okHttpDownload: 指定的文件存储目录不存在，创建下载目录:" + (isSuccess ? "成功" : "失败"));
                } else {
                    Log.d(TAG, "okHttpDownload: 指定的文件存储目录存在，不需要创建新的目录");
                }
                try {
                    File file = new File(dir, saveFileName);
                    ResponseBody body = response.body();
                    if (body != null) {
                        Log.e(TAG, "okHttpDownload: body!=null------");
                        inputStream = body.byteStream();
                        long total = body.contentLength();
                        if (total == -1) {
                            listener.onDownloadFailed(new IOException("从服务器获取到的内容大小为 -1 (未知状态)"));
                            inputStream.close();
                            return;
                        } else {
                            Log.e(TAG, "okHttpDownload: 需要下载的文件大为:" + total + " 字节  " + ((double) total / 1024 / 1024) + " M");
                        }
                        fos = new FileOutputStream(file);
                        long sum = 0;
                        int progressCache = -1;  // Todo  过滤相同进度值
                        long cacheTime = 0;      // Todo  过滤掉不同进度值之间时间间隔太短的部分:
                        long timeInterval = 100; // Todo  时间间隔值
                        while ((length = inputStream.read(buf)) != -1) {
                            fos.write(buf, 0, length);
                            sum += length;
                            int progress = (int) (sum * 1.0f / total * 100);
                            //如果onDownloading方法调用太频繁,双重过滤
                            if (progress != progressCache) {
                                if (progress == 0 || progress == 100 || (System.currentTimeMillis() - cacheTime > timeInterval)) {
                                    Log.e(TAG, "okHttpDownload:文件大小:" + total + " 进度值:" + progress + " 时间间隔:" + (System.currentTimeMillis() - cacheTime));
                                    cacheTime = System.currentTimeMillis();
                                    progressCache = progress;
                                    listener.onDownloading(total, progress);
                                }
                            }
                        }
                        fos.flush();
                        listener.onDownloadSuccess(file);
                    } else {
                        Log.e(TAG, "okHttpDownload: 服务器回复的内容体为null");
                    }
                } catch (Exception e) {
                    listener.onDownloadFailed(e);
                    Log.e(TAG, "okHttpDownload: " + e.toString());
                } finally {
                    try {
                        if (inputStream != null)
                            inputStream.close();
                        if (fos != null)
                            fos.close();
                    } catch (Exception e) {
                        Log.e(TAG, "okHttpDownload: " + e.toString());
                    }
                }
            }
        });
    }

    private static RequestBody createCustomRequestBody(final MediaType contentType, final File file, final OnHttpUploadListener listener) {
        return new RequestBody() {
            @Override
            public MediaType contentType() {
                return contentType;
            }

            @Override
            public long contentLength() {
                return file.length();
            }

            @Override
            public void writeTo(@NotNull BufferedSink sink) {
                Source source;
                try {
                    Log.e(TAG, "writeTo: --------上传开始---------");
                    source = Okio.source(file);
                    //sink.writeAll(source);
                    Buffer buf = new Buffer();
                    long fileSize = contentLength();
                    long count = 0;
                    long progressCache = 0;
                    if (fileSize == 0) {
                        if (listener != null)
                            listener.onUploadFailed(file, new IOException("上传的文件大小为0"));
                        return;
                    }
                    if (listener != null) {
                        listener.onUploading(0);
                    }
                    for (long readCount; (readCount = source.read(buf, 3048)) != -1; ) {
                        sink.write(buf, readCount);
                        count += readCount;
                        int progress = (int) ((float) count / fileSize * 100);
                        if (listener != null) {
                            if (progress == 100) {
                                listener.onUploadSuccess(file);
                            } else if (progress != 0 && progress != progressCache) {
                                progressCache = progress;
                                listener.onUploading(progress);
                            }
                        }
                    }
                    Log.e(TAG, "writeTo: -------上传结束----------");
                } catch (Exception e) {
                    if (listener != null)
                        listener.onUploadFailed(file, e);
                    e.printStackTrace();
                }
            }
        };
    }


    public interface OnHttpUploadListener {
        void onUploadSuccess(File file);

        void onUploading(int progress);

        void onUploadFailed(File file, Exception e);
    }

    public interface OnHttpDownloadListener {
        void onDownloadSuccess(File file);

        void onDownloading(long fileSize, int progress);

        void onDownloadFailed(Exception e);
    }

    private static class TrustAllCerts implements X509TrustManager {
        public void checkClientTrusted(X509Certificate[] chain, String authType) {
        }

        public void checkServerTrusted(X509Certificate[] chain, String authType) {
        }

        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }

    private static class TrustAllHostnameVerifier implements HostnameVerifier {
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }

    private static SSLSocketFactory createSSLSocketFactory() {
        SSLSocketFactory ssfFactory = null;
        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            //SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, new TrustManager[]{new TrustAllCerts()}, new SecureRandom());
            ssfFactory = sc.getSocketFactory();
        } catch (Exception e) {
        }

        return ssfFactory;
    }

}
