package com.ryjoin.livesdk

import android.app.Application
import android.content.Context
import com.startpineapple.kbllivesdk.KBLSDK

/**
 * Created by chenshengyu
 *  on 2022/8/3.
 */
object RyjoinSDK {
    fun init(application: Application){
        KBLSDK.init(application)
    }

    fun registerPushUser(userId:String){
        KBLSDK.instance.registerPushUser(userId)
    }

    fun handPush(context:Context,meta:String?){
        KBLSDK.instance.handlePush(context, meta)
    }
}