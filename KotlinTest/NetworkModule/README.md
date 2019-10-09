#原文链接:https://blog.csdn.net/qq_22090073/article/details/100513698

#gitbug:https://github.com/plumcookingwine/RepoApp/tree/master/NetworkModule


###### 网上找了很多网络请求封装的demo，但是由于种种局限，都没有真正适用于自己项目中的，于是自己实现了一个高度可自由定制的网络请求加载框架
#### <font color=skyblue>什么是自由定制？</font>
比如可自定义是否显示加载框、加载框样式自定义、是否缓存本次请求，缓存条件自定义、自定义缓存方式、可自定义拦截器、自定义BaseUrl等等（以上功能框架中均有默认实现，也可自由定制）
###### github：<a href='https://github.com/plumcookingwine/RepoApp/tree/master/NetworkModule'>https://github.com/plumcookingwine/RepoApp/tree/master/NetworkModule</a>

###### 欢迎大家start！

#### <font color=skyblue>使用方式</font>
为了管理网络请求的生命周期，本案例与MVP相结合，当然大家也可根据自己的实际情况，选择合适的结构。

###### 1.  首先在项目中引入此依赖

```
api 'com.plumcookingwine.network:NetworkModule:1.0.0'
```
###### 2.  在Application中做初始化

```
/**
*  一共有5个参数
* 1.   applicationContext， 用于library中获取上下文对象
* 2.   baseUrl ， 网络请求的基础路径（这里使用通用路径，如果有其他路径，可在请求时自由设置，下文中会讲到）
* 3.   isDebug,   是否开启调试模式，开启调试模式会打印日志
* 4.   List<Interceptor>, 这是一个okhttp的拦截器集合，如果不想添加拦截器，直接传null即可
* 5.   cookieResult， 自定义缓存方式，如果使用默认的（library中使用了ACache作为默认缓存），直接传null即可
*/
 NetworkHelper.init(
            applicationContext,
            baseUrl,
            isDebug,
            mutableListOf<Interceptor>(PublicParamsInterceptor()),
            cookieResult
        )
```
###### 3.  Mvp - BaseView

```
/**
* 再BaseView中实现生命周期 管理， 加载框的显示与隐藏，网络错误处理（如需自定义）
*/
interface BaseView {

    fun getNetLifecycle(): LifecycleProvider<*>

    fun showLoading(loadingText: String)

    fun hideLoading()

    fun onError(text: String)
}
```
###### 4.  Mvp - BasePresenter

```
/**
* ICommonInterface , 用于网络层与View交互，做生命周期管理
*/
abstract class BasePresenter<V : BaseView>(view: V) : ICommonInterface {

    val mView: V = view

    override fun onSubscribe(loadingMsg: String) {
        mView.showLoading(loadingMsg)
    }

    override fun onComplete() {
        mView.hideLoading()
    }
	
	// 生命周期管理，在BaseActivity中实现
    override fun lifecycle(): LifecycleProvider<*> {
        return mView.getNetLifecycle()
    }

	// 是否使用默认的error处理方式（library中，默认请求失败后，会使用系统的toast提示错误信息，
	// 这里返回false后，会使用 BaseActivity中的onError方法，否则使用系统默认）
    override fun isDefaultError(): Boolean {
        return false
    }

    override fun error(msg: ApiErrorModel) {
        return mView.onError(msg.message)
    }
}
```

###### 5.  Mvp - BaseActivity

```
/*
*  注意“ LoadingDialog是自己实现的加载框哦~
*/
abstract class BaseActivity<P : BasePresenter<*>> : RxAppCompatActivity(),
    BaseView {

    lateinit var mPresenter: P

    abstract fun resLayoutId(): Int

    abstract fun init()

    abstract fun logic()

    protected abstract fun initPresenter(): P

    override fun getNetLifecycle(): LifecycleProvider<*> {
        return this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(resLayoutId())
        mPresenter = initPresenter()
        init()
        logic()
    }

    override fun showLoading(loadingText: String) {
        Log.i("TAG", "show")
         LoadingDialog.show(loadingText)
    }

    override fun hideLoading() {
        Log.i("TAG", "hide")
        Toast.makeText(this, "hide", Toast.LENGTH_SHORT).show()
        LoadingDialog.hide()
    }

    override fun onError(text: String) {
        Log.i("TAG", "error hide")
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
}
```
###### 6.  实现- Presenter

```
class MainPresenter(view: MainView) : BasePresenter<MainView>(view) {
/**
     * 测试网络请求
     */
    fun request(page: Int) {
        HttpManager.instance.doHttpDeal(
        	/* LoginOpt，实现了AbsOptions，用于进行网络请求配置， 下面会将实现方式*/
            LoginOpt("123", "123", "123", "123"),
            /* 网络请求回调 */
            object : INetworkCallback<String>(this) {
				/* 是否缓存，如果返回false，就会走onSuccss，不会走缓存方法 */
                override fun onCache(json: String): Boolean {
                    // 只有第一次请求才缓存(缓存第一页的数据)
                    val isCache = page == 0
                    if (isCache) {
                        mView.success("cache===$json")
                    }
                    return isCache
                }

				/* 请求成功后回调 */
                override fun onSuccess(obj: String, cookieListener: CookieResultListener) {

                    val mainModel = Gson().fromJson(obj, MainModel::class.java)
                    if (mainModel.code == "2001") {
                        mView.success(obj)
                        // 在这里进行手动缓存
                        // why？ 如果需要缓存,在这里自定义缓存的时机，如果需要缓存，重写onCache()方法，并返回true,缓存的内容会走到onCache回调
                        // 如果不自定义时机的话，有可能请求code码错误了也会缓存，所以在这里判断成功了才去缓存
                        cookieListener.saveCookie()
                        return
                    }
                    // 直接回调错误方法
                    onError(ApiErrorModel((mainModel.code ?: "0").toInt(), mainModel.msg ?: "error"))
                }
                
				// 请求错误回调，如果不想使用用默认的，请重写此方法，并注释掉super.onError()这一行
				override fun onError(err: ApiErrorModel?) {
                    super.onError(err)
                }
            })
    }
}
```
###### 7.  实现- Options

```
/**
 *  基于面向对象思想，因为每个请求的配置方式可能都不一样，所以每一个请求就要创建一个配置对象，
 *  这里参考了wzgiceman大神的demo ，大家又兴趣也可以去看看
 *  https://github.com/wzgiceman/RxjavaRetrofitDemo-master
 * 
 * AbsRequestOptions配置项下面讲
 */
class LoginOpt(
    private val code: String,
    private val loginAccount: String,
    private val password: String,
    private val uid: String
) : AbsRequestOptions<String>() {

	// 在这里进行网络请求的配置
    init {
        isCache = true
        cacheUrl = "login"
    }

	// 在这里获取 service
    override fun createService(retrofit: Retrofit): Observable<String> {
        return retrofit.create(MainService::class.java).login(
            code, loginAccount, password, uid
        )
    }

}
```

###### options配置项：
```
    baseUrl: 基础路径（域名）
    cacheTimeForNetwork: 有网缓存时间，单位是ms，默认30s
    cacheTimeForNoNetwork: 无网缓存时间，单位是ms， 默认是1天
    cacheUrl: 缓存路径（缓存时必填）
    isCache: 是否缓存
    retryCount: 超时重连次数
    retryDelay: 超时重连延时（ms）（失败后多长时间进行重连）
    retryIncreaseDelay: 超时重连叠加延时（ms）（比如第一次100毫秒后重试，第二次就是 100 + retryIncreaseDelay毫秒后重试了。。。）
    timeOut: 设置超时时间（s），默认6s，如果上传图片的话可能会超时，这时可配置大一些
```
这样就ok啦，我们讲onCache返回true，来测试一下：

<img width="200px" src="https://img-blog.csdnimg.cn/2019090312425867.gif" />

图中可以看到第一次走success方法，第二次走了onCache方法！，还有application中初始化的时候自定义缓存方式，需继承AbsCookieResult，在里面实现以下方法就可以啦！
```
/**
 * 抽象数据缓存，用户可自定义缓存实现方法
 */
abstract class AbsCookieResult {

    // 保存数据
    abstract fun saveData(t: CookieDao?)

    // 获取数据
    abstract fun getData(): CookieDao?

    // 更新数据
    abstract fun upData(t: CookieDao?)

    // 删除数据
    abstract fun deleteData(t: CookieDao?)
}
```

### github：<a href='https://github.com/plumcookingwine/RepoApp/tree/master/NetworkModule'>https://github.com/plumcookingwine/RepoApp/tree/master/NetworkModule</a>
### 时间关系，下一篇再讲实现原理 ，如果大家有好的建议和意见可以提出来，我会继续开发和优化！

