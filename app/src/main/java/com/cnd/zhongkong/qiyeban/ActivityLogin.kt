package com.cnd.zhongkong.qiyeban

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Context.*
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cnd.zhongkong.qiyeban.databinding.ActivityLoginBinding

class ActivityLogin : AppCompatActivity() {
    var text: TextView? =null;
    var flagNetworkState:Boolean = false
    private var intentFilter: IntentFilter? = null
    private var networkChangeReceiver: ActivityLogin.NetworkChangeReceiver? = null
    private val BROAD_NETWORK = "android.net.conn.CONNECTIVITY_CHANGE"
    private val btnLogin: Button? = null
    private val tvLoginState: TextView? = null
    val TAG  = "ActivityLogin"
    public val NETWORK_OK = 1
    public val NETWORK_ERROR = 2
    public val LOGIN_OK = 3
    public val LOGIN_ERROR = 4
    val handler: Handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                NETWORK_OK -> text!!.text = "网络正常"
                NETWORK_ERROR -> text!!.text = "网络异常，请检查"
                LOGIN_OK -> startActivity(
                    Intent(
                        this@ActivityLogin,
                        MainActivity::class.java
                    )
                )
                LOGIN_ERROR -> Toast.makeText(
                    applicationContext,
                    "网络异常，请确认网络正常后再扫码登录",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding=ActivityLoginBinding.inflate(layoutInflater);
        setContentView(binding.root)
        text=findViewById(R.id.tvLoginState)
//        注册系统广播
        //        注册系统广播
        intentFilter = IntentFilter()
        intentFilter!!.addAction(BROAD_NETWORK)
        networkChangeReceiver = NetworkChangeReceiver()
        registerReceiver(networkChangeReceiver, intentFilter)
    }

  //广播类
  //    广播接收者
  inner class NetworkChangeReceiver : BroadcastReceiver() {
      override fun onReceive(context: Context, intent: Intent) {
          val message = Message()
          val connectivityManager = context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
          val networkInfo = connectivityManager.activeNetworkInfo
          if (networkInfo != null && networkInfo.isAvailable) {
              Log.i("xwg", "send msg network is ok")
              message.what = 1
              flagNetworkState = true
          } else {
              Log.i("xwg", "send msg network is error")
              message.what = 2
              flagNetworkState = false
          }
          this@ActivityLogin.handler.sendMessage(message)
      }
  }
}
