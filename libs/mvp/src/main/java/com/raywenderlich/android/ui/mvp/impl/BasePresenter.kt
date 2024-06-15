package com.raywenderlich.android.ui.mvp.impl

import androidx.annotation.CallSuper
import com.raywenderlich.android.ui.mvp.Presenter
import com.raywenderlich.android.ui.mvp.ViewBinder

/**
 * Base class for Presenters¬
 */
abstract class BasePresenter<V, VB : ViewBinder<V>> : Presenter<V, VB> {

  /**
   * The View
   */
  private var viewBinder: VB? = null

  @CallSuper
  override fun bind(viewBinder: VB) {
    this.viewBinder = viewBinder
  }

  @CallSuper
  override fun unbind() {
    viewBinder = null
  }

  protected fun useViewBinder(consumer: VB.() -> Unit) {
    viewBinder?.run {
      consumer.invoke(this)
    }
  }
}