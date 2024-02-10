package com.aliyayman.mvvm_news.ui.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.aliyayman.mvvm_news.R
import com.aliyayman.mvvm_news.adapters.NewsAdapter
import com.aliyayman.mvvm_news.databinding.FragmentBreakingNewsBinding
import com.aliyayman.mvvm_news.ui.activty.NewsActivity
import com.aliyayman.mvvm_news.util.Resource
import com.aliyayman.mvvm_news.viewmodels.NewsViewModel


class BreakingNewsFragment : Fragment() {
    private lateinit var binding: FragmentBreakingNewsBinding
    private lateinit var viewModel: NewsViewModel
    lateinit var newsAdapter: NewsAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBreakingNewsBinding.inflate(layoutInflater)
        return  binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as NewsActivity).viewModel
        setupRecyclerView()
        observeBreakingNews()

        newsAdapter.setOnItemclickListener {
          val bundle = Bundle().apply {
                 putSerializable("article",it)
            }
            findNavController().navigate(R.id.action_breakingNewsFragment_to_articleFragment,bundle)
        }
    }

    private fun observeBreakingNews(){
        viewModel.breakingNews.observe(viewLifecycleOwner, Observer { response->
            when(response){
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let {newsResponse ->
                        newsAdapter.differ.submitList(newsResponse.articles)
                    }
                }
                is Resource.Error->{
                    hideProgressBar()
                    response.message?.let {message->
                        Toast.makeText(activity,"An error occured: $message",Toast.LENGTH_LONG).show()
                    }

                }
                is Resource.Loading ->{
                    showProgressBar()
                }
            }

        })
    }

    private fun hideProgressBar(){
        binding.paginationProgressBar.visibility = View.INVISIBLE
    }
    private fun showProgressBar(){
        binding.paginationProgressBar.visibility = View.VISIBLE
    }

    private fun setupRecyclerView(){
        newsAdapter = NewsAdapter()
        binding.rvBreakingNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }

    }

}