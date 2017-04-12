package dev.jeonghyeonji.imagesearch.view.main

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.util.Log
import android.view.Menu
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.facebook.drawee.backends.pipeline.Fresco
import dev.jeonghyeonji.imagesearch.R
import dev.jeonghyeonji.imagesearch.adapter.ImageAdapter
import dev.jeonghyeonji.imagesearch.network.ImageRestClient
import dev.jeonghyeonji.imagesearch.presenter.ImageListPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity() {

    val imageRestClient = ImageRestClient()
    val subject: PublishSubject<String> = PublishSubject.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Fresco init
        Fresco.initialize(applicationContext) //baseContext

        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        // 검색어를 쓰고 1 초가 지나면 검색을 진행하도록 한다
        subject
                .debounce(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { connect(it) }
                .apply { dispose() }

        // RecyclerView setting
        recyclerView.apply {
            setHasFixedSize(true)
            val linearLayout = LinearLayoutManager(context)
            layoutManager = linearLayout
            clearOnScrollListeners()
        }
        // Adapter setting
        initAdapter()

    }

    private fun initAdapter() {
        if (recyclerView.adapter == null) {
            recyclerView.adapter = ImageAdapter(this)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)

        val searchView = menu?.findItem(R.id.search_bar)?.actionView as? SearchView

        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(s: String?): Boolean {

                subject.onNext(s)
                return false
            }

            override fun onQueryTextSubmit(query: String?): Boolean {

                subject.onNext(query)
                return false
            }

        })
        return super.onCreateOptionsMenu(menu)
    }

    //network test
    fun connect(searchWord: String) {

        if (searchWord.isNullOrBlank()) {

            Toast.makeText(baseContext, "검색된 이미지가 없습니다", Toast.LENGTH_SHORT).show()
            (recyclerView.adapter as ImageAdapter).imageList.clear()
            (recyclerView.adapter as ImageAdapter).notifyDataSetChanged()

        } else {

            imageRestClient.client.getImage("12b2bc62862e146f32b943d661b9eda2", searchWord)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            {
                                Log.d("Test", "Image Add $it")

                                (recyclerView.adapter as ImageAdapter).imageList.clear()
                                (recyclerView.adapter as ImageAdapter).imageList.addAll(it.channel.item)
                                (recyclerView.adapter as ImageAdapter).notifyDataSetChanged()

                            },
                            {
                                Log.d("Test", "onError $it")
                                Toast.makeText(baseContext, "이미지를 불러오는데 문제가 발생했습니다. 다시 시도해 주세요.", Toast.LENGTH_SHORT).show()
                                (recyclerView.adapter as ImageAdapter).imageList.clear()
                                (recyclerView.adapter as ImageAdapter).notifyDataSetChanged()

                            },
                            {
                                Log.d("Test", "Complete")
                                val view = this.getCurrentFocus()
                                if (view != null) {
                                    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0)
                                }
                            })

        }

    }


}
