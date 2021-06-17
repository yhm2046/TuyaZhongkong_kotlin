package com.cnd.zhongkong.qiyeban

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.fragment.app.Fragment
import com.cnd.zhongkong.qiyeban.databinding.ActivityMainBinding

/**
 * 主界面，载入分屏
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding=ActivityMainBinding.inflate(layoutInflater);
        setContentView(binding.root)
        }
}