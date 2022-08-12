package com.ryjoin.livesdk

import android.text.TextUtils
import android.util.Log
import com.google.gson.Gson
import com.ryjoin.livesdk.data.BaseRsp
import kotlinx.coroutines.*
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.Executors


/**
 * Created by chenshengyu
 *  on 2022/8/9.
 */
object NetClient {
    private val TAG=NetClient::class.java.simpleName
    const val defaultUrl="https://api.adx.ryjoin.com/sc?app_id=14R5F65WA14DFD5G4FSG"

    private const val CONST_CHARSET_UTF8="UTF-8"
    interface L {
        fun r(conn: HttpURLConnection?, body: String?)
    }

    fun getRequest(url:String,copy:(String?)->Unit){
        CoroutineScope(Dispatchers.IO).launch {
            try {
                g(url,object :L{
                    override fun r(conn: HttpURLConnection?, body: String?) {
                        try {
                            if(conn?.responseCode==200){
                                Log.d(TAG,"--:$body")
                                CoroutineScope(Dispatchers.Main).launch{
                                    val br=Gson().fromJson(body,BaseRsp::class.java)
                                    copy.invoke(br.data)
                                }
                            }else{
                                Log.d(TAG,"--d:${conn?.responseCode}")
                            }
                        }catch (e:Exception){
                            Log.e(TAG,"--r:$e--:${conn?.responseCode}")
                        }
                    }
                })
            }catch (e:Exception){
                Log.e(TAG,"--r:$e")
            }
        }
    }

    /**
     * get request
     */
    @Throws(Exception::class)
    private  fun g(u: String?, l: L?): String {
        val localURL = URL(u)
        val conn = localURL.openConnection() as HttpURLConnection
        if (conn.responseCode >= 300) {
            throw Exception("HTTP Request is not success, Response code is " + conn.responseCode)
        }
        val o: String = stream2string(conn.inputStream, conn.contentEncoding)
        l?.r(conn, o)
        conn.disconnect()
        return o
    }

    private fun stream2string(`is`: InputStream?, encode: String?): String {
        var encode = encode
        if (TextUtils.isEmpty(encode)) encode = CONST_CHARSET_UTF8
        val result = StringBuffer()
        var isr: InputStreamReader? = null
        try {
            isr = InputStreamReader(`is`, encode)
            val bufferSize = 1024
            val buffer = CharArray(bufferSize)
            while (true) {
                val rsz: Int = isr.read(buffer, 0, buffer.size)
                if (rsz < 0) break
                result.append(buffer, 0, rsz)
            }
        } catch (e: java.lang.Exception) {
            try {
                isr?.close()
                `is`?.close()
            } catch (e1: java.lang.Exception) {
            }
        }
        return result.toString()
    }
}