package dev.jeonghyeonji.imagesearch.model.base

/**
 * Created by jeonghyeonji on 2017. 4. 11..
 */
open class AbstractPresenter <VIEW : BaseView> : BasePresenter<VIEW> {

    protected var view: VIEW? = null
        private set

    override fun attachView(view: VIEW) {
        this.view = view
    }

    override fun detachView() {
        view = null
    }
}