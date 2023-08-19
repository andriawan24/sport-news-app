package id.andriawan24.favorite

import android.content.Intent
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import id.andriawan24.base.BaseActivity
import id.andriawan24.domain.models.NewsModel
import id.andriawan24.favorite.adapters.FavoriteNewsAdapter
import id.andriawan24.favorite.databinding.ActivityFavoriteBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavoriteActivity : BaseActivity<ActivityFavoriteBinding, FavoriteViewModel>(),
    FavoriteNewsAdapter.NewsListener {

    override val viewModel: FavoriteViewModel by viewModels()
    override val binding: ActivityFavoriteBinding by lazy {
        ActivityFavoriteBinding.inflate(layoutInflater)
    }

    private val newsAdapter = FavoriteNewsAdapter(this)

    override fun onInitViews() {
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