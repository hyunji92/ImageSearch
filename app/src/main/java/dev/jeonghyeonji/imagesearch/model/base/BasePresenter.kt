package dev.jeonghyeonji.imagesearch.model.base

/**
 * Created by jeonghyeonji on 2017. 4. 11..
 */
interface BasePresenter<in VIEW : BaseView> {

    /**
     * View Attach.
     */
    fun attachView(view: VIEW)

    /**
     * View detach
     */
    fun detachView()
}