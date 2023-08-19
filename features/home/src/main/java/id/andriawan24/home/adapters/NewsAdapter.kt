package id.andriawan24.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import id.andriawan24.base.databinding.ItemHomeNewsBinding
import id.andriawan24.domain.models.NewsModel
import java.text.SimpleDateFormat
import java.util.Locale

class NewsAdapter(private val listener: NewsListener) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    private var news = emptyList<NewsModel>()

    class ViewHolder(
        private val binding: ItemHomeNewsBinding,
        private val listener: NewsListener
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(news: NewsModel) {
            binding.textViewNewsTitle.text = news.title
            binding.textViewAuthorName.text = news.author

            val apiDateFormat = "yyyy-MM-dd'T'hh:mm:ss'Z'"
            val displayDateFormat = "dd MMM yyyy"
            val apiDateFormatter = SimpleDateFormat(apiDateFormat, Locale.getDefault())
            val displayDateFormatter = SimpleDateFormat(displayDateFormat, Locale.getDefault())
            val date = apiDateFormatter.parse(news.publishedAt)
            val displayDate = if (date != null) displayDateFormatter.format(date) else "Invalid!"

            binding.textViewPublishedAt.text = displayDate

            binding.root.setOnClickListener {
                listener.onNewsClicked(news)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            binding = ItemHomeNewsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            listener = listener
        )
    }

    override fun getItemCount(): Int = news.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(news[position])
    }

    fun submitList(newData: List<NewsModel>) {
        val diffUtil = TeamsAdapterDiffer(news, newData)
        val result = DiffUtil.calculateDiff(diffUtil)
        news = newData
        result.dispatchUpdatesTo(this)
    }

    companion object {
        class TeamsAdapterDiffer(
            private val oldData: List<NewsModel>,
            private val newData: List<NewsModel>
        ) : DiffUtil.Callback() {
            override fun getOldListSize(): Int = oldData.size
            override fun getNewListSize(): Int = newData.size

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return oldData[oldItemPosition] == newData[newItemPosition]
            }

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return oldData[oldItemPosition].title == newData[newItemPosition].title
            }
        }
    }

    interface NewsListener {
        fun onNewsClicked(news: NewsModel)
    }
}