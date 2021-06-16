package com.cnd.zhongkong.qiyeban

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.news_title_frag.*
import java.lang.StringBuilder

/**
 * 新闻展示列表fragment
 */
class NewsTitleFragment:Fragment() {
    private var isTwoPane=false
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.news_title_frag,container,false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        isTwoPane=activity?.findViewById<View>(R.id.newsContentLayout)!=null    //判断单页还是双页模式，newsContentLayout 在双页模式出现
        val layoutManager=LinearLayoutManager(activity)
        newsTitleRecyclerView.layoutManager=layoutManager
        val adapter=NewsAdapter(getNews())
        newsTitleRecyclerView.adapter=adapter
    }

    /**
     * 获取新闻列表50条
     */
    private fun getNews():List<News>{
        val newsList=ArrayList<News>()
        for (i in 1..50){
            val news=News("title $i",getRandomLengthString("news content $i. "))
            newsList.add(news)
        }
        return newsList
    }

    /**
     * 随机news内容长度
     */
    private fun getRandomLengthString(str: String): String {
        val n=(1..20).random()
        val builder=StringBuilder()
        repeat(n){
            builder.append(str)
        }
        return builder.toString()
    }

    //    recyclerview适配器
    inner class NewsAdapter(val newsList:List<News>):RecyclerView.Adapter<NewsAdapter.ViewHolder>(){
        inner class ViewHolder(view:View):RecyclerView.ViewHolder(view){
            val newsTitle:TextView=view.findViewById(R.id.newsTitle)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.news_item,parent,false)
        val holder=ViewHolder(view)
        holder.itemView.setOnClickListener {
            val news=newsList[holder.adapterPosition]
            if (isTwoPane){
//                双页模式
                val fragment=newsContentFrag as NewsContentFragment
                fragment.refresh(news.title,news.content)
                Log.i("xwg","shuangye.........")
            }else{
//                单页模式
                NewsContentActivity.actionStart(parent.context,news.title,news.content)
            }
        }
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val news=newsList[position]
        holder.newsTitle.text=news.title
    }

    override fun getItemCount()=newsList.size
    }//end adpter
}