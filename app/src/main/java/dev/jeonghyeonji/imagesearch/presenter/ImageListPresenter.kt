package dev.jeonghyeonji.imagesearch.presenter

import android.util.Log
import dev.jeonghyeonji.imagesearch.model.ImageItem
import dev.jeonghyeonji.imagesearch.model.base.AbstractPresenter
import dev.jeonghyeonji.imagesearch.network.ImageRestClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by jeonghyeonji on 2017. 4. 11..
 */
class ImageListPresenter : AbstractPresenter<ImageListContract.View>(), ImageListContract.Presenter {

    override var listContractView: ImageAdapterContract.View? = null
    override var listContractModel: ImageAdapterContract.Model? = null

    override var imageList: ArrayList<ImageItem>? = null

    val imageRestClient = ImageRestClient()

    override fun loadImageList(searchWord: String) {

        imageRestClient.client.getImage("12b2bc62862e146f32b943d661b9eda2", searchWord)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            Log.d("Test", "Image Add $it")

                        },
                        {
                            Log.d("Test", "onError $it")

                        },
                        {
                            Log.d("Test", "Complete")
                        })
    }
}