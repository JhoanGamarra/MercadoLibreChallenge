package com.jhoangamarra.mercadolibrechallenge.presentation.product_list

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.jhoangamarra.mercadolibrechallenge.R
import com.jhoangamarra.mercadolibrechallenge.core.show
import com.jhoangamarra.mercadolibrechallenge.data.api.service.RetrofitClient
import com.jhoangamarra.mercadolibrechallenge.data.repository.SearchRepositoryImpl
import com.jhoangamarra.mercadolibrechallenge.databinding.FragmentProductListBinding
import com.jhoangamarra.mercadolibrechallenge.domain.model.ProductDomainModel
import com.jhoangamarra.mercadolibrechallenge.domain.use_cases.product_list.GetProductsUC
import com.jhoangamarra.mercadolibrechallenge.presentation.product_list.adapter.ProductListAdapter

private val TAG = ProductListFragment::class.java.simpleName

class ProductListFragment : Fragment(R.layout.fragment_product_list) {

    private lateinit var binding: FragmentProductListBinding
    private lateinit var adapter: ProductListAdapter


    private val viewModel by viewModels<ProductListViewModel> {
        ProductListViewModelFactory(GetProductsUC(SearchRepositoryImpl(RetrofitClient.retrofit)))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProductListBinding.bind(view)
        setupViewModel()
        setupUI()
        initSearchView()
    }

    //view model
    private fun setupViewModel() {
        viewModel.products.observe(viewLifecycleOwner, fetchProducts)
        viewModel.isViewLoading.observe(viewLifecycleOwner, isViewLoadingObserver)
        viewModel.isEmptyList.observe(viewLifecycleOwner, emptyListObserver)

    }

    //ui
    private fun setupUI() {
        adapter = ProductListAdapter()
        binding.recyclerView.adapter = adapter
        adapter.onItemClickListener = { product ->
            val action =
                ProductListFragmentDirections.actionProductListFragmentToProductDetailFragment(
                    product.price,
                    product.productImage,
                    product.title,
                    product.availableQuantity,
                    product.availableMercadoPago,
                    product.condition
                )
            findNavController().navigate(action)
        }
    }

    private val fetchProducts = Observer<List<ProductDomainModel>> { result ->
        Log.d(TAG, "Product : $result")

        adapter.submitList(result)
    }

    private val isViewLoadingObserver = Observer<Boolean> {
        Log.d(TAG, "isViewLoading $it")
        val visibility = if (it) View.VISIBLE else View.GONE
        binding.progressBar.visibility = visibility
    }


    private val emptyListObserver = Observer<Boolean> {
        Log.d(TAG, "emptyListObserver $it")
        Glide.with(requireContext()).load(R.drawable.bg_empty_result).centerCrop().into(binding.ivEmptyView)
        val visibility = if (it) View.VISIBLE else View.GONE
        binding.ivEmptyView.visibility = visibility
    }

    private fun initSearchView() {
        binding.searchView.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {
                Log.d("SearchView", "Text changed:$newText")
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                Log.d("SearchView", "Submit:$query")
                viewModel.getProducts(query)
                return false
            }

        })
    }

}