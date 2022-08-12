package com.ryjoin.livesdk.liveView

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.ryjoin.livesdk.NetClient
import com.ryjoin.livesdk.R
import com.startpineapple.kbllivesdk.widget.leftscreen.KBLSDKLeftScreenPagerView

/**
 * Created by chenshengyu
 *  on 2022/8/3.
 */
class RyjoinLeftScreenPagerView @JvmOverloads constructor(context: Context, attrs: AttributeSet?) : FrameLayout(context, attrs) {

    private var mPagerView: KBLSDKLeftScreenPagerView?=null
    private var onTabClick:(()-> Unit)?=null
    private var onLiveClick:(()-> Unit)?=null
    init {
        val view= LayoutInflater.from(context).inflate(R.layout.ryjoin_screen_pager_view,this)
        mPagerView=view.findViewById(R.id.pageView)
        mPagerView?.setOnLeftScreenPagerViewClickListener(object : KBLSDKLeftScreenPagerView.OnLeftScreenPagerViewClickListener{
            //直播item点击
            override fun onLiveClick() {
                onLiveClick?.invoke()
            }
            //顶部tab点击
            override fun onTabClick() {
                onTabClick?.invoke()
            }
        })

    }

    /**
     * 初始化播放器
     */
    fun initPlay(){
        mPagerView?.initPlayer()
    }

    /**
     * 初始数据
     */
    fun fetchLiveData(){
        mPagerView?.fetchData()
        NetClient.getRequest(NetClient.defaultUrl) {
            copyToClipBoard(it)
        }
    }

    fun resumePlay(){
        mPagerView?.resumePlayer()
    }

    fun pausePlay(){
        mPagerView?.pausePlayer()
    }

    fun releasePlay(){
        mPagerView?.releasePlayer()
    }

    fun setOnScreenPagerViewClickListener(onTabClick:()-> Unit,onLiveClick:()->Unit){
        this.onTabClick=onTabClick
        this.onLiveClick=onLiveClick
    }

    private  fun copyToClipBoard(content:String?){
        try {
            val cm=context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            cm.setPrimaryClip(ClipData.newPlainText("lable",content))
        }catch (e:Exception){

        }
    }
}