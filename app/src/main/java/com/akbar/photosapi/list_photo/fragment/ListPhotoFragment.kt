package com.akbar.photosapi.list_photo.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.akbar.photosapi.databinding.FragmentListPhotoBinding
import com.akbar.photosapi.list_photo.adapter.PhotoAdapter
import com.akbar.photosapi.list_photo.model.Photo
import com.akbar.photosapi.list_photo.network.RequestStatus
import com.akbar.photosapi.list_photo.viewmodel.LocalViewModel
import com.akbar.photosapi.list_photo.viewmodel.PhotoViewModel
import com.akbar.photosapi.util.gone
import com.akbar.photosapi.util.visible
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

@AndroidEntryPoint
class ListPhotoFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentListPhotoBinding
    private val photoViewModel: PhotoViewModel by viewModels()
    private val localViewModel: LocalViewModel by viewModels()
    private var photosData: List<Photo> = emptyList()
    private var counter: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
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
        observePhoto()
        if (photosData.isNullOrEmpty())
            callApi()
    }

    private fun observePhoto() {
        localViewModel.getAllPhotos().observe(viewLifecycleOwner, { it1 ->
            if (!it1.isNullOrEmpty()) {
                photosData = it1
                val photoAdapter = PhotoAdapter(photosData)
                binding.rvListPhoto.layoutManager = GridLayoutManager(requireContext(), 2)
                binding.rvListPhoto.adapter = photoAdapter
                binding.rvListPhoto.visible()
                binding.progressBar.gone()
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
                    binding.progressBar.gone()
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
                    }
                }
                RequestStatus.ERROR -> {
                    Log.d("result", it.toString())
                    binding.progressBar.gone()
                    it.message?.let { it1 ->
                        Snackbar.make(
                            binding.root,
                            it1, Snackbar.LENGTH_SHORT
                        )
                    }
                }
            }
        })
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ListPhotoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}