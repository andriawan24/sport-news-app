package id.andriawan24.home

import android.content.Intent
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory
import com.google.android.play.core.splitinstall.SplitInstallRequest
import dagger.hilt.android.AndroidEntryPoint
import id.andriawan24.base.BaseActivity
import id.andriawan24.domain.models.CountryModel
import id.andriawan24.domain.models.NewsModel
import id.andriawan24.home.adapters.NewsAdapter
import id.andriawan24.home.databinding.ActivityHomeBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeActivity : BaseActivity<ActivityHomeBinding, HomeViewModel>(), NewsAdapter.NewsListener {

    override val viewModel: HomeViewModel by viewModels()
    override val binding: ActivityHomeBinding by lazy {
        ActivityHomeBinding.inflate(layoutInflater)
    }

    private val newsAdapter = NewsAdapter(this)

    override fun onInitViews() {
        setupToolbar()
        setupRecyclerView()
        setupCountries()
        initObserver()
    }

    private fun setupToolbar() {
        binding.toolbar.setOnMenuItemClickListener {
            if (it.itemId == R.id.menu_about) {
                installAboutFeaturesModule()
            }

            if (it.itemId == R.id.menu_favorite) {
                moveToFavoriteActivity()
            }

            return@setOnMenuItemClickListener true
        }
    }

    private fun moveToFavoriteActivity() {
        val intent = Intent(this, Class.forName("id.andriawan24.favorites.FavoriteActivity"))
        startActivity(intent)
    }

    private fun installAboutFeaturesModule() {
        val splitInstallManager = SplitInstallManagerFactory.create(this)
        val moduleAbout = "about"
        if (splitInstallManager.installedModules.contains(moduleAbout)) {
            moveToAboutActivity()
        } else {
            Toast.makeText(this, "Downloading $moduleAbout module, please wait", Toast.LENGTH_SHORT)
                .show()

            val request = SplitInstallRequest.newBuilder()
                .addModule(moduleAbout)
                .build()

            splitInstallManager.startInstall(request)
                .addOnSuccessListener {
                    moveToAboutActivity()
                }
                .addOnFailureListener {
                    Toast.makeText(
                        this,
                        "Error installing module, please try again! ${it.localizedMessage}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
        }
    }

    private fun moveToAboutActivity() {
        val intent = Intent(this, Class.forName("id.andriawan24.about.AboutActivity"))
        startActivity(intent)
    }

    private fun setupRecyclerView() {
        binding.recyclerViewNews.adapter = newsAdapter
        binding.recyclerViewNews.layoutManager = LinearLayoutManager(this)
    }

    private fun setupCountries() {
        CountryModel.getCountries().forEach {
            val newTab = binding.tabLayoutCountries.newTab()
            newTab.text = it.name
            newTab.tag = it.id
            binding.tabLayoutCountries.addTab(newTab)
        }

        binding.tabLayoutCountries.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                viewModel.initData(tab?.tag.toString())
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                // No-ops
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                // No-ops
            }
        })

        viewModel.initData(binding.tabLayoutCountries.getTabAt(0)?.tag.toString())
    }

    private fun initObserver() {
        lifecycleScope.launch {
            viewModel.showLoading.collectLatest {
                binding.progressBarNews.isVisible = it
            }
        }

        lifecycleScope.launch {
            viewModel.news.collectLatest {
                binding.textViewNewsTotal.text = getString(R.string.total_news_template, it.size)
                newsAdapter.submitList(it)
                binding.recyclerViewNews.isVisible = it.isNotEmpty()
                binding.emptyView.isVisible = it.isEmpty()
            }
        }
    }

    override fun onNewsClicked(news: NewsModel) {
        val intent = Intent(this, Class.forName("id.andriawan24.detail.DetailActivity"))
        intent.putExtra("news", news)
        startActivity(intent)
    }
}