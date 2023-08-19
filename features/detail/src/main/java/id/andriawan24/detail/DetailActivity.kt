package id.andriawan24.detail

import android.annotation.SuppressLint
import android.os.Build
import android.webkit.WebViewClient
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import dagger.hilt.android.AndroidEntryPoint
import id.andriawan24.base.BaseActivity
import id.andriawan24.detail.databinding.ActivityDetailBinding
import id.andriawan24.domain.models.NewsModel

@AndroidEntryPoint
class DetailActivity : BaseActivity<ActivityDetailBinding, DetailViewModel>() {

    override val viewModel: DetailViewModel by viewModels()
    override val binding: ActivityDetailBinding by lazy {
        ActivityDetailBinding.inflate(layoutInflater)
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onInitViews() {
        initObserver()
        initToolbar()
        initNews()
    }

    private fun initObserver() {
        viewModel.favoriteNews.observe(this) {
            binding.toolbar.menu.findItem(R.id.menu_favorite).setIcon(
                ContextCompat.getDrawable(
                    this,
                    if (it != null) id.andriawan24.base.R.drawable.ic_favorite_active else id.andriawan24.base.R.drawable.ic_favorite
                )
            )
        }
    }

    private fun initToolbar() {
        binding.toolbar.setNavigationOnClickListener { finish() }
    }

    private fun initNews() {
        val news = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("news", NewsModel::class.java)
        } else {
            intent.getParcelableExtra("news") as? NewsModel
        }

        if (news != null) {
            binding.webViewNews.apply {
                webViewClient = WebViewClient()

                settings.javaScriptEnabled = true

                loadUrl(news.url)
            }

            viewModel.checkFavoriteNews(news.title)

            binding.toolbar.setOnMenuItemClickListener {
                if (it.itemId == R.id.menu_favorite) {
                    viewModel.addOrRemoveFromFavorite(news)
                }
                return@setOnMenuItemClickListener true
            }
        }
    }
}