package aacom.by.rylivesdk

import android.app.Application
import com.ryjoin.livesdk.RyjoinSDK

/**
 * Created by chenshengyu
 *  on 2022/8/1.
 */
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        //初始化
        RyjoinSDK.init(this)

        /**
         * 注册推送用户，用户所在平台唯一id，可以在某个合适的时机。 如用户登录后
         */
        RyjoinSDK.registerPushUser("000000000000")

    }
}
