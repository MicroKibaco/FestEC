# 小购屋
## 声明
本项目内容撸自 **傅令杰** 实战教程 ,纯项目练手之作，个人未从中获取任何利益，其所有内容均可在归于 IMOOC 所有。内容可能会侵犯到 IMOOC 的知识产权 ,若被告知需停止共享与使用，本人会立即删除整个项目。
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
### 三.业务开发
#### 1. 计时器
android中创建定时器有三种方式,分别是: Timer、CountDownTimer 和 handler.postDelayed,这里我们使用的是Timer
```JAVA
public class BaseTimerTask extends TimerTask {

    private ITimerListener mListener = null;

    public BaseTimerTask(ITimerListener listener) {
        this.mListener = listener;
    }

    @Override
    public void run() {
        if (mListener != null) {
            mListener.onTimer();
        }
    }
}
```
使用接口回调的方式,订阅执行定时任务
```JAVA
public interface ITimerListener {
   void onTimer();
}
```
Timer 这个API 是线程安全的, 直接通过该对象的 schedule 方法延迟执行任务,
值得一提的是:该计时器创建的是子线程,不能做耗时操作,所以要更新ui,必须借助handler处理.
```JAVA
public class LauncherDelegate extends LatteDelegate implements ITimerListener {
    @BindView(R2.id.tv_launcher_timer)
    AppCompatTextView mTvTimer;

    private Timer mTimer = null;
    private int mCount = 5;

    @Override
    public void onTimer() {
        getProxyActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mTvTimer != null) {
                    mTvTimer.setText(MessageFormat.format("跳过\n{0}s", mCount));
                    mCount--;
                    if (mCount < 0) {
                        if (mTimer != null) {
                            mTimer.cancel();
                            mTimer = null;
                            checkIsShowScroll();
                        }
                    }
                }
            }
        });
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_launcher;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initTimer();
    }

    private void initTimer() {

        mTimer = new Timer();
        final BaseTimerTask task = new BaseTimerTask(this);
        mTimer.schedule(task, 0, 1000);

    }


    // 判断是否展示欢迎页
    private void checkIsShowScroll() {

        if (!LattePreference.getAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name())) {
            getSupportDelegate().start(new LauncherScrollDelegate(), SINGLETASK);
        } else {
            // 检查用户是否已经登录App

        }

    }


    @OnClick(R2.id.tv_launcher_timer)
    public void onClickTimerView() {

        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
            checkIsShowScroll();
        }

    }
}

```
1.用户触发跳过,或计时器任务执行完毕,进入欢迎页.
2.用户滑动到最后一张轮播图,记录当时的状态,作为下次启动程序是否开启欢迎页面的依据
### 2. 闪屏页
再次感谢[saiwu-bigkoo的Android-ConvenientBanner](https://github.com/saiwu-bigkoo/Android-ConvenientBanner),自定义你的Holder，实现更多复杂的界面，不一定是图片翻页，其他任何控件翻页亦可
```java
public class LauncherHolder implements Holder<Integer> {

    private AppCompatImageView mImageView = null;

    @Override
    public View createView(Context context) {
        mImageView = new AppCompatImageView(context);
        mImageView.setScaleType(AppCompatImageView.ScaleType.FIT_XY);
        return mImageView;
    }

    @Override
    public void UpdateUI(Context context, int position, Integer data) {
        mImageView.setBackgroundResource(data);
    }


}
```
采用代码分离技巧,构建Holder构建器,典型的MVC设计思想
```java
public class LauncherHolderCreator implements CBViewHolderCreator<LauncherHolder> {
    @Override
    public LauncherHolder createHolder() {
        return new LauncherHolder();
    }
}
```
#### [ConvenientBanner 的简单使用: ](https://github.com/MicroKibaco/FestEC/issues/8)

通过 **ConvenientBanner** 的 **setPageIndicator** 方法设置两个点图片作为翻页指示器，
不设置则没有指示器，可以根据自己需求自行配合自己的指示器,
不需要圆点指示器可用不设,**setPageIndicatorAlign** 方法 设置 指示器的方向,
当然我们也需要 **setOnItemClickListener** 每一张轮播图的被点击的监听事件,如果点击的是最后一个,
检查用户是否已经登录

### 3. 登录
登录分为普通用户登录和微信登录两种方式,接下来我们就先讲一下普通用户登录的方式
### **① 普通用户登录**
#### 一. 校验登录的合法性
```java
 private boolean checkForm() {
        final String email = mEmail.getText().toString();
        final String password = mPassword.getText().toString();

        boolean isPass = true;
        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmail.setError("错误的邮箱格式");
            isPass = false;
        } else {
            mEmail.setError(null);
        }

        if (password.isEmpty() || password.length() < 6) {
            mPassword.setError("请填写至少六位数密码");
            isPass = false;
        } else {
            mPassword.setError(null);
        }

        return isPass;
    }
```
#### 二. 在登录请求校验成功后,执行登录成功的回调
```java
 RestClient.builder()
                    .url("http://localhost:8080/RestDataServer/api/user_profile.php")
                    .params("email", mEmail.getText().toString())
                    .params("password", mPassword.getText().toString())
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            Lattelogger.json("USER_PROFILE", response);
                            SignHandler.onSignIn(response, mISignListener);
                            mISignListener.onSignInSuccess();
                        }
                    })
                    .build()
                    .post();
```
下面我们就深入内部打探一下: [AccountManager 到底是如何 对登录的数据进行解析的哈](https://github.com/MicroKibaco/FestEC/issues/7)
不得不提的是,在登录数据流管理过程中我们用到了 一款开源的面向Android 的轻便、快捷的ORM 框架 [GreenDao](http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2017/0703/8144.html)

### **② 微信登录**
引入微信登录依赖
```groovy
compile 'com.tencent.mm.opensdk:wechat-sdk-android-with-mta:+'
```
- #### [编写自己的元注解和annotationProcessor](https://github.com/MicroKibaco/FestEC/issues/3)
- #### [通过注解生成指定模板的代码](https://github.com/MicroKibaco/FestEC/issues/4)
- #### 通过代码生成器，生成微信登录代码，绕过微信包名限制

### 4. 注册
[注册成功以后,同样也需要对同一张数据表结构进行字段插入](https://github.com/MicroKibaco/FestEC/issues/6)
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



