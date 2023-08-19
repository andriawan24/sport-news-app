package id.andriawan24.favorites

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.EntryPointAccessors
import id.andriawan24.domain.di.UseCasesModule
import id.andriawan24.domain.models.NewsModel
import id.andriawan24.domain.usecases.GetFavoritesNewsUseCase
import id.andriawan24.favorites.adapters.FavoriteNewsAdapter
import id.andriawan24.favorites.databinding.ActivityFavoriteBinding
import id.andriawan24.favorites.di.DaggerFavoriteComponents
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

//@AndroidEntryPoint
class FavoriteActivity : AppCompatActivity(), FavoriteNewsAdapter.NewsListener {

    @Inject
    lateinit var getFavoritesNewsUseCase: GetFavoritesNewsUseCase

    private lateinit var viewModel: FavoriteViewModel
    private val binding: ActivityFavoriteBinding by lazy {
        ActivityFavoriteBinding.inflate(layoutInflater)
    }

    private val newsAdapter = FavoriteNewsAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerFavoriteComponents.builder()
            .context(this)
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    applicationContext,
                    UseCasesModule::class.java
                )
            )
            .build()
            .inject(this)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel = ViewModelProvider(
            this,
            FavoriteViewModel.factory(getFavoritesNewsUseCase)
        )[FavoriteViewModel::class.java]

        onInitViews()
    }

    private fun onInitViews() {
        setupToolbar()
        setupRecyclerView()
        initObserver()
        viewModel.initData()
    }

    private fun initObserver() {
        lifecycleScope.launch {
            viewModel.news.collectLatest {
                newsAdapter.submitList(it)
                binding.recyclerViewNews.isVisible = it.isNotEmpty()
                binding.emptyView.isVisible = it.isEmpty()
            }
        }
    }

    private fun setupRecyclerView() {
        binding.recyclerViewNews.adapter = newsAdapter
        binding.recyclerViewNews.layoutManager = LinearLayoutManager(this)
    }

    private fun setupToolbar() {
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }
    }

    override fun onNewsClicked(news: NewsModel) {
        val intent = Intent(this, Class.forName("id.andriawan24.detail.DetailActivity"))
        intent.putExtra("news", news)
        startActivity(intent)
    }
}