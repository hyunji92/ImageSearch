package dev.jeonghyeonji.imagesearch

import android.os.Bundle
import android.support.annotation.MainThread
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.SearchView
import android.util.Log
import android.view.Menu
import dev.jeonghyeonji.imagesearch.network.ImageRestClient
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val imageRestClient = ImageRestClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)

        val searchView = menu?.findItem(R.id.search_bar)?.actionView as? SearchView

        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(s: String?): Boolean {
                return false
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                connect()
                return false
            }

        })
        return super.onCreateOptionsMenu(menu)
    }

    //network test
    fun connect() {
        imageRestClient.client.getImage("6d8c45c694062f940835b48689fc6248", "권혁")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.d("AA", "onNext $it")

                }, {

                    Log.d("AA", "onError $it")

                }, {

                    Log.d("AA", "Complete")

                })
    }

}
