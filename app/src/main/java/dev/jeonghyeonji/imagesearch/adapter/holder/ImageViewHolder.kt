package dev.jeonghyeonji.imagesearch.adapter.holder

import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.View
import dev.jeonghyeonji.imagesearch.model.ImageItem
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.list_item.view.*

/**
 * Created by jeonghyeonji on 2017. 4. 10..
 */
class ImageViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindData(imageItem: ImageItem) {

        Observable.just(imageItem.thumbnail)
                .subscribeOn(Schedulers.newThread())
                .filter { it != null }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    itemView.fre_image_view.setImageURI(Uri.parse(imageItem.image), itemView.context)
                }
    }
}