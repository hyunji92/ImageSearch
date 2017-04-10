package dev.jeonghyeonji.imagesearch.adapter

import android.content.Context
import android.content.res.Resources
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.drawable.ProgressBarDrawable
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder
import dev.jeonghyeonji.imagesearch.R
import dev.jeonghyeonji.imagesearch.model.ImageItem
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.list_item.view.*


/**
* Created by jeonghyeonji on 2017. 4. 7..
*/
class ImageAdapter(var c: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val imageList = ArrayList<ImageItem>()


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        (holder as? ViewHolder)?.bindData(imageList[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        val v = LayoutInflater.from(c).inflate(R.layout.list_item, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int = imageList.size

    open class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindData(imageItem: ImageItem) {

            Observable.just(imageItem.image)
                    .subscribeOn(Schedulers.newThread())
                    .filter { it != null }
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        initViewsAndEvent(Uri.parse(imageItem.image))
                    }
        }

        fun initViewsAndEvent(image: Uri) {

            val builder = GenericDraweeHierarchyBuilder(Resources.getSystem())
            val hierarchy = builder
                    .setFadeDuration(300)
                    .setProgressBarImage(ProgressBarDrawable())
                    .build()
            val controller = Fresco.newDraweeControllerBuilder()
                    .setUri(image)
                    .setAutoPlayAnimations(true)
                    .build()
            itemView.fre_image_view.hierarchy = hierarchy
            itemView.fre_image_view.controller = controller
        }

    }

}