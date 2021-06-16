package com.cnd.zhongkong.qiyeban

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.fragment.app.Fragment
import com.cnd.zhongkong.qiyeban.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.left_fragment.*

//import kotlinx.android.synthetic.main.left_fragment.*

class MainActivity : AppCompatActivity() {
//     lateinit var btn:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val binding=ActivityMainBinding.inflate(layoutInflater);
//        setContentView(binding.root)
        setContentView(R.layout.activity_main)
//        btn=findViewById(R.id.btn_show);
        /*
        实现动态添加fragment
        * (1) 创建待添加Fragment的实例。
            (2) 获取FragmentManager，在Activity中可以直接调⽤getSupportFragmentManager()⽅法获取。
            (3) 开启⼀个事务，通过调⽤beginTransaction()⽅法开启。
            (4) 向容器内添加或替换Fragment，⼀般使⽤replace()⽅法实现，需要传⼊容器的id和待添加的Fragment实例。
            (5) 提交事务，调⽤commit()⽅法来完成。
        * */
            btn_show.setOnClickListener {
            Log.i("XWG","click...")
            replaceFragment(AnotherRightFragment())
        }
        replaceFragment(RightFragment())
    }
    private fun replaceFragment(fragment: Fragment){    //添加限定符layout-large适配不同屏幕，以下代码需要注释
//        val fragmentManager=supportFragmentManager
//        val transaction=fragmentManager.beginTransaction()
//        transaction.replace(R.id.rightLayout,fragment)
//        transaction.addToBackStack(null)    //返回上一个fragment
//        transaction.commit()
    }
}