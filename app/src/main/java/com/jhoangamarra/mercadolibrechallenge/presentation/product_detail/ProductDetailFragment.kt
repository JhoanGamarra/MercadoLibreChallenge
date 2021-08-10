package com.jhoangamarra.mercadolibrechallenge.presentation.product_detail

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.jhoangamarra.mercadolibrechallenge.R
import com.jhoangamarra.mercadolibrechallenge.databinding.FragmentProductDetailBinding


class ProductDetailFragment : Fragment(R.layout.fragment_product_detail) {

    private lateinit var binding : FragmentProductDetailBinding
    private val args by navArgs<ProductDetailFragmentArgs>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProductDetailBinding.bind(view)
        setupView()
        backButtonListener()
    }


    private fun setupView(){
        Glide.with(requireContext()).load(args.image).centerCrop().into(binding.ivProduct)
        binding.tvProductTitle.text = args.title
        binding.tvProductPrice.text = args.price
        binding.tvProductStatus.text = if(args.productStatus == "new") "Estado : Nuevo" else "Estado : Usado"
        binding.tvProductAvailableQuantity.text = args.availableQuantity.toString()
        binding.tvProductAvailableMercadoPago.text = if (args.availableMercadoPago) "Mercado Pago : Si" else "Mercado Pago : No"
    }


    private fun backButtonListener() {
        binding.ibBackButton.setOnClickListener {
            findNavController().navigateUp()
        }
    }





}