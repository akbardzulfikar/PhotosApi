package com.akbar.photosapi.list_photo.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.akbar.photosapi.R
import com.akbar.photosapi.databinding.FragmentListPhotoBinding
import com.akbar.photosapi.list_photo.adapter.PhotoAdapter
import com.akbar.photosapi.list_photo.model.Photo
import com.akbar.photosapi.list_photo.network.RequestStatus
import com.akbar.photosapi.list_photo.viewmodel.LocalViewModel
import com.akbar.photosapi.list_photo.viewmodel.PhotoViewModel
import com.akbar.photosapi.util.GeneralSnackbar
import com.akbar.photosapi.util.gone
import com.akbar.photosapi.util.visible
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ListPhotoFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentListPhotoBinding
    private val photoViewModel: PhotoViewModel by viewModels()
    private val localViewModel: LocalViewModel by viewModels()
    private var photosData: List<Photo> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentListPhotoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.genericToolbar.genericToolbarTitle.text = getString(R.string.list_photos_app)
        binding.swipeRefresh.setOnRefreshListener {
            callApi()
        }
        binding.searcUser.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    if (query.isNotEmpty()) {
                        localViewModel.searchTitle(query).let { photos ->
                            val photoAdapter = photos?.let { PhotoAdapter(it) }
                            binding.rvListPhoto.layoutManager =
                                GridLayoutManager(requireContext(), 2)
                            binding.rvListPhoto.adapter = photoAdapter
                        }
                    }
                }
                return false
            }
        })
        observePhoto()
    }

    private fun observePhoto() {
        localViewModel.getAllPhotos().observe(viewLifecycleOwner, { it1 ->
            binding.progressBar.visible()
            if (!it1.isNullOrEmpty()) {
                photosData = it1
                val photoAdapter = PhotoAdapter(photosData)
                binding.rvListPhoto.layoutManager = GridLayoutManager(requireContext(), 2)
                binding.rvListPhoto.adapter = photoAdapter
                binding.rvListPhoto.visible()
                binding.progressBar.gone()
            }
            else {
                callApi()
            }
        })
    }

    private fun callApi(){
        photoViewModel.getPhotos().observe(viewLifecycleOwner, {
            when (it.requestStatus) {
                RequestStatus.LOADING -> {
                    Log.d("result", it.toString())
                    binding.progressBar.visible()
                    binding.rvListPhoto.gone()
                }
                RequestStatus.SUCCESS -> {
                    Log.d("result", it.toString())
                    it.data?.let { list ->
                        val photoAdapter = PhotoAdapter(list)
                        binding.rvListPhoto.layoutManager =
                            GridLayoutManager(requireContext(), 2)
                        binding.rvListPhoto.adapter = photoAdapter
                        binding.rvListPhoto.visible()
                        list.forEach { photo ->
                            localViewModel.insertPhoto(photo).observe(viewLifecycleOwner, { it2 ->
                                when (it2.requestStatus) {
                                    RequestStatus.SUCCESS -> {
                                    }
                                }
                            })
                        }
                        binding.progressBar.gone()
                        stopSwipeRefresh()
                    }
                }
                RequestStatus.ERROR -> {
                    Log.d("result", it.toString())
                    binding.progressBar.gone()
                    GeneralSnackbar.showErrorSnackBar(
                        binding.root,
                        it?.message ?: getString(R.string.error_server),
                        binding.root.context
                    )
                    stopSwipeRefresh()
                }
            }
        })
    }

    private fun stopSwipeRefresh() {
        if (binding.swipeRefresh != null)
            if (binding.swipeRefresh.isRefreshing) binding.swipeRefresh.isRefreshing = false
    }
}