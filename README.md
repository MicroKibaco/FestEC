# FestEC
## 声明
本项目内容撸自**傅猿猿**实战教程 ,纯练手之作，个人未从中获取任何利益，其所有内容均可在归于Imooc所有。内容可能会侵犯到Imooc的知识产权 ,若被告知需停止共享与使用，本人会立即删除整个项目。
## 简介
项目采用 RxJava2.0 + retrofit2.0 + GreenDao 开发
所有功能已经完成，如果对你有帮助的话不妨star一个o(￣▽￣)ブ

下面我就来总结一下哈哈哈哈
下载地址,召唤术: [传送门](http://www.github.com/MicroKibaco/FestEC/apks/FestEC.apk)

## 应用部分截图

## 封装
![项目目录结构](http://p2je16s75.bkt.clouddn.com/project.png)
### 一.Fragment基类

1) 引用来自[YoKey大牛](https://github.com/YoKeyword/Fragmentation)Fragmentation 开源库的 SwipeBackFragment,成功将[Fragment的各种坑](https://www.zhihu.com/question/39662488)顺利甩锅,构建基础Frgment
2)  强制子Fragment 通过 setLayout 霸权主义 指定布局资源,onBindView 绑定子布局

![BaseDelegate](http://p5do3ypyn.bkt.clouddn.com/BaseDelegate.png)
### 二.Activity基类

1) 继承Fragmentation包下的SupportActivity,并不是每个妞都得泡,但是每个Activity子视图都有一个唯一id,SupportActivity的LoadRootFrgment方法 支持Fragment指定根布局加载
2) 你那么孤单,却总说一个人挺好,每一块Fragment都不能独立存在,强制寻求与Fragment基类有血缘关系的子类,建立联系感(ProxyActivty),并将最终结果告诉Frgment的爸妈 MainActivity
![ProxyActivity](http://p5do3ypyn.bkt.clouddn.com/ProxyActivity.png)
### 三.网络请求
1. 一个基于RxJava + Retrofit 的快速开发基类库 [latte-core](https://github.com/MicroKibaco/FestEC/tree/master/latte-core)
- [配置管理器](https://github.com/MicroKibaco/FestEC/blob/master/latte-core/src/main/java/com/asiainfo/latte/app/Latte.java) 管理配置配置信息
- 自定义多元化 [请求进度条](https://github.com/MicroKibaco/FestEC/blob/master/latte-core/src/main/java/com/asiainfo/latte/ui)
![LatteLoader](http://p5do3ypyn.bkt.clouddn.com/LatteLoader.png)

![LoaderCreator](http://p5do3ypyn.bkt.clouddn.com/LoaderCreator.png)

- [RxJava](https://github.com/MicroKibaco/FestEC/blob/master/latte-core/src/main/java/com/asiainfo/latte/net/rx) 请求方式的封装
![RxRestService](http://p5do3ypyn.bkt.clouddn.com/RxRestService.png)

**Restful构建器**
```JAVA
public class RxRestClientBuilder {

    private static Map<String, Object> PARAMS = RestCreator.getParams();
    private String mUrl = null;

    private RequestBody mBody = null;
    private LoaderStyle mloaderStyle = null;
    private Context mContext = null;
    private File mFile = null;


    RxRestClientBuilder() {

    }

    public final RxRestClientBuilder url(String url) {
        this.mUrl = url;
        return this;
    }

    public final RxRestClientBuilder params(WeakHashMap<String, Object> params) {
        PARAMS.putAll(params);
        return this;
    }

    public final RxRestClientBuilder params(String key, Object value) {
        PARAMS.put(key, value);
        return this;
    }

    public final RxRestClientBuilder file(File file) {
        this.mFile = file;
        return this;
    }

    public final RxRestClientBuilder file(String file) {
        this.mFile = new File(file);
        return this;
    }


    public final RxRestClientBuilder raw(String raw) {
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), raw);
        return this;
    }


    public final RxRestClientBuilder loader(Context context) {

        this.mContext = context;
        this.mloaderStyle = LoaderStyle.BallClipRotatePulseIndicator;
        return this;

    }

    public final RxRestClient build() {

        return new RxRestClient(mUrl, PARAMS, mBody, mloaderStyle, mContext, mFile);

    }


}
```
**Restful请求的具体实现类**
```JAVA
public class RxRestClient {


    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private final String URL;
    private final RequestBody BODY;
    private final LoaderStyle LOADER_STYLE;
    private final Context CONTEXT;
    private final File FILE;


    public RxRestClient(String url,
                        Map<String, Object> params,
                        RequestBody body,
                        LoaderStyle style,
                        Context context,
                        File file) {
        this.URL = url;
        PARAMS.putAll(params);
        this.BODY = body;
        this.LOADER_STYLE = style;
        this.CONTEXT = context;
        this.FILE = file;

    }

    public static RxRestClientBuilder builder() {
        return new RxRestClientBuilder();
    }

    /**
     * request 请求产业园
     */
    private Observable<String> request(HttpMethod method) {

        final RxRestService service = RestCreator.getRxRestService();
        Observable<String> observable = null;


        if (LOADER_STYLE != null) {

            LatteLoader.showLoading(CONTEXT, LOADER_STYLE);

        }


        switch (method) {

            case GET:
                observable = service.get(URL, PARAMS);
                break;
            case POST:
                observable = service.post(URL, PARAMS);
                break;
            case POST_RAW:
                observable = service.postRaw(URL, BODY);
                break;
            case PUT_RAW:
                observable = service.putRaw(URL, BODY);
                break;
            case PUT:
                observable = service.put(URL, PARAMS);
                break;
            case DELETE:
                observable = service.delete(URL, PARAMS);
                break;
            case UPLOAD:
                final RequestBody requestBody =
                        RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()), FILE);
                final MultipartBody.Part body =
                        MultipartBody.Part.createFormData("file", FILE.getName(), requestBody);
                observable = RestCreator.getRxRestService().upload(URL, body);
                break;

            default:
                break;

        }


        return observable;
    }

    public final Observable<String> get() {

        return request(HttpMethod.GET);

    }

    public final Observable<String> post() {

        if (BODY == null) {
            return request(HttpMethod.POST);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be not null");
            }
            return request(HttpMethod.POST_RAW);
        }


    }

    public final Observable<String> put() {

        if (BODY == null) {
            return request(HttpMethod.PUT);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be not null");
            }
            return request(HttpMethod.PUT_RAW);
        }

    }

    public final Observable<String> delete() {

        return request(HttpMethod.DELETE);

    }

    public final Observable<String> upload() {

        return request(HttpMethod.UPLOAD);

    }

    public final Observable<ResponseBody> download() {
        return RestCreator.getRxRestService().download(URL, PARAMS);
    }

}
```
- [Retrofit](https://github.com/MicroKibaco/FestEC/blob/master/latte-core/src/main/java/com/asiainfo/latte/net/rt) 请求方式的封装,核心代码与RxJava类似,这里就啰嗦啦

- [请求拦截器](https://github.com/MicroKibaco/FestEC/blob/master/latte-core/src/main/java/com/asiainfo/latte/net/interceptors/BaseInterceptor.java): BaseInterceptor
```JAVA
public abstract class BaseInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        return null;
    }

    protected LinkedHashMap<String, String> getUrlParameters(Chain chain) {


        final HttpUrl url = chain.request().url();
        int size = url.querySize();
        final LinkedHashMap<String, String> params = new LinkedHashMap<>();
        for (int i = 0; i < size; i++) {
            params.put(url.queryParameterName(i), url.queryParameterValue(i));
        }
        return params;
    }

    protected String getUrlParameters(Chain chain, String key) {
        final Request request = chain.request();
        return request.url().queryParameter(key);
    }

    protected LinkedHashMap<String, String> getBodyParameters(Chain chain) {
        final FormBody formBody = (FormBody) chain.request().body();
        final LinkedHashMap<String, String> params = new LinkedHashMap<>();
        if (formBody != null && formBody.contentLength() != 0) {
            for (int i = 0; i < formBody.size(); i++) {
                params.put(formBody.name(i), formBody.value(i));
            }
        }
        return params;
    }

    protected String getBodyParameters(Chain chain, String key) {
        return getBodyParameters(chain).get(key);

    }

}

```

- [文件下载](https://github.com/MicroKibaco/FestEC/blob/master/latte-core/src/main/java/com/asiainfo/latte/net/download/SaveFileTask.java): SaveFileTask类
### 业务开发

## 第三方服务
- [支付宝]()
- [ShareSDK]()
- [JPush]()
- [二维码]()

## 框架
感谢这些开源框架的大力支持

- [BRAVH](https://github.com/CymChad/BaseRecyclerViewAdapterHelper) ： 功能强大的RecyclerViewAdapter封装库
- [picasso](https://github.com/squareup/picasso) : 图片加载
- [GreenDAO](https://github.com/greenrobot/greendao) : 数据库框架
- [Retrofit](https://github.com/square/retrofit) : 代码简洁，接口解耦
- [OkHttp](https://github.com/square/okhttp) : 网络请求
- [RxJava](https://github.com/ReactiveX/RxJava) : 快捷的线程切换，简洁的代码，清晰的逻辑，和Retrofit配合很爽
- [iconify](https://github.com/joanzapata/iconify) : 字体图标
- [fragmentation](https://github.com/yokeyword/fragmentation) ：协助构建UI框架
- [fastjson](https://github.com/alibaba/fastjson) ： JSON序列化
- [ButterKnife](https://github.com/Tencent/VasSonic) : 黄牛刀,一键findViewById
- [avi](https://github.com/wang/avi/library) :loading 加载动画
- [StatusBarCompat](https://github.com/niorgai/StatusBarCompat) : 沉浸式状态栏
- [ucrop](https://github.com/yalantis/ucrop) : 图片剪裁


## TODO
- bmob管理后台系统
- Tinker 热修复
- Small插件化



