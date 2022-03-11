package com.example.githubactiondemo

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.githubactiondemo.BR

/**
 * Base activity for all activities.
 */
abstract class BaseAppCompatActivity<Binding : ViewDataBinding, ViewModel : BaseViewModel> :
    AppCompatActivity(), View.OnClickListener {

    protected lateinit var binding: Binding
    protected abstract val viewModel: ViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindViewModel()
        initializeObservers(viewModel)
    }

    override fun onResume() {
        super.onResume()
    }

    /**
     * Initialize observers for [ViewModel].
     *
     * @param viewModel
     */
    open fun initializeObservers(viewModel: ViewModel) {}

    /**
     * This function will be executed when onCreate() is called.
     */
    abstract fun initialize()

    /**
     * Get the layout resource ID for the screen.
     */
    @LayoutRes
    abstract fun getLayoutResId(): Int

    private fun bindViewModel() {
        binding = DataBindingUtil.setContentView(this, getLayoutResId())
        binding.apply {
            lifecycleOwner = this@BaseAppCompatActivity
            setVariable(BR.viewModel, viewModel)
        }
        binding.executePendingBindings()
        initialize()
    }

    override fun onClick(view: View?) {
        view?.isEnabled = false
        Handler(Looper.getMainLooper()).postDelayed({
            view?.isEnabled = true
        }, THOUSAND)
    }


    companion object {
        const val THOUSAND = 500L
    }
}
