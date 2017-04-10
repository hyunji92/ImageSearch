package dev.jeonghyeonji.imagesearch.presenter

import dev.jeonghyeonji.imagesearch.model.Image
import tech.thdev.support.widget.adapter.model.BaseRecyclerModel

/**
 * Created by jeonghyeonji on 2017. 4. 10..
 */

interface ImageAdapterContract {

    interface View {
        fun reload()
    }

    interface Model : BaseRecyclerModel<Image> {

    }
}