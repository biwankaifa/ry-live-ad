# RyLiveSdk接入说明文档
### 步骤1: 添加SDK到工程中
* 复制外部ryLive.aar包到libs目录下。
* 修改build.gradle 配置文件

1. 项目级build.gradle添加代码仓库地址
```
repositories {
    //添加仓库地址 start
    maven { url 'https://jitpack.io' }
    jcenter()
    //添加仓库地址 end
    google()
    mavenCentral()
}
```
2. 修改dependencies，添加依赖：implementation files('libs/rylive-v1.0.aar')
```
dependencies {`
    ...
    implementation files('libs/rylive-v1.0.aar')
}
```
### 步骤3: 添加view
添加 RyjoinLeftScreenPagerView 到你的xml

```
 <?xml version="1.0" encoding="utf-8"?>
<layout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools">

    <data>

    </data>

    <androidx.constraintlayout.widget.ConstraintLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:background="#f8f8f8"
        tools:context=".MainActivity">
        <androidx.cardview.widget.CardView
            android:id="@+id/cv"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            app:layout_constraintTop_toTopOf="parent"
            app:layout_constraintBottom_toBottomOf="parent"
            app:cardCornerRadius="20dp">
            <!--     负一屏view       -->
            <com.ryjoin.livesdk.liveView.RyjoinLeftScreenPagerView
                android:id="@+id/lspv"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"/>
        </androidx.cardview.widget.CardView>

        <Button
            android:id="@+id/refreshBtn"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_marginHorizontal="20dp"
            app:layout_constraintTop_toBottomOf="@id/cv"
            android:layout_marginTop="20dp"
            android:text="刷新卡片内容"/>

    </androidx.constraintlayout.widget.ConstraintLayout>
</layout>

```
### 步骤4:初始化SDK
请在您的的 Application 类的 onCreate() 回调中调用如下方法初始化 SDK。
```
RyJoin.init(applicationContext, "appId", extras);

    override fun onCreate() {

        super.onCreate()
        //初始化
        RyjoinSDK.init(this)
        /**
         * 注册推送用户，用户所在平台唯一id，可以在某个合适的时机。 如用户登录后
         */
        RyjoinSDK.registerPushUser("000000000000")
    }
```
### 步骤5：获取数据
````
override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //初始化播放器
        binding.lspv.initPlay()

        //获取数据
        binding.lspv.fetchLiveData()

        //重新获取
        binding.refreshBtn.setOnClickListener {
            binding.lspv.fetchLiveData()
        }

        //点击回调
        binding.lspv.setOnScreenPagerViewClickListener({
            Log.d(TAG,"TAB 点击")
        },{
            Log.d(TAG,"live 点击")
        })

        /**
         * 处理推送消息
         * @param context Context
         * @param meta  推送消息中的的meta层消息
         * {
         *     "commodityId":1,
         *     "liveId":1,
         *     "commodityName":"三星",
         *     "deeplink":"",
         *     "openTab":1
         * }
         */
       RyjoinSDK.handPush(this,"")

    }

    override fun onResume() {
        super.onResume()
        //恢复播放
        binding.lspv.resumePlay()
    }

    override fun onPause() {
        super.onPause()
        //暂停播放
        binding.lspv.pausePlay()
    }

    override fun onDestroy() {
        super.onDestroy()
        //释放播放器
        binding.lspv.releasePlay()
    }
```