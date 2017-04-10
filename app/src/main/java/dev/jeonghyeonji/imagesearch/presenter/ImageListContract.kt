package dev.jeonghyeonji.imagesearch.presenter

import dev.jeonghyeonji.imagesearch.model.ImageItem
import dev.jeonghyeonji.imagesearch.model.base.BasePresenter
import dev.jeonghyeonji.imagesearch.model.base.BaseView

/**
 * Created by jeonghyeonji on 2017. 4. 11..
 */
class ImageListContract {

    interface View : BaseView {

        fun loadSuccess()

        fun loadFail()
    }

    interface Presenter : BasePresenter<View> {

        var listContractView: ImageAdapterContract.View?
        var listContractModel: ImageAdapterContract.Model?

        var imageList:  ArrayList<ImageItem>?

        fun loadImageList(searchWord: String)
    }
}