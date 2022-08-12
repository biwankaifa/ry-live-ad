package aacom.by.rylivesdk

import aacom.by.rylivesdk.databinding.ActivityMainBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.ryjoin.livesdk.RyjoinSDK

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val TAG=MainActivity::class.java.simpleName
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

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

}