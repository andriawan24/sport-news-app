package id.andriawan24.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VB: ViewBinding, VM: ViewModel> : AppCompatActivity() {

    protected abstract val binding: VB
    protected abstract val viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        onInitViews()
    }

    protected abstract fun onInitViews()
}