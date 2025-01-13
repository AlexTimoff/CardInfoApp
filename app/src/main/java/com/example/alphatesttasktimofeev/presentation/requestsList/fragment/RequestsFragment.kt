package com.example.alphatesttasktimofeev.presentation.requestsList.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.alphatesttasktimofeev.App
import com.example.alphatesttasktimofeev.R
import com.example.alphatesttasktimofeev.databinding.FragmentRequestsBinding
import com.example.alphatesttasktimofeev.domain.entity.RequestRecord
import com.example.alphatesttasktimofeev.presentation.MainViewModel
import com.example.alphatesttasktimofeev.presentation.MainViewModelFactory
import com.example.alphatesttasktimofeev.presentation.requestsList.adapter.RequestsAdapter
import com.example.alphatesttasktimofeev.presentation.requestsList.state.RequestListState
import kotlinx.coroutines.launch
import javax.inject.Inject


class RequestsFragment : Fragment() {
    private var _binding: FragmentRequestsBinding? = null
    private val binding get() = _binding!!
    private var allRequests: List<RequestRecord> = arrayListOf()

    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory

    private val viewModel: MainViewModel by activityViewModels {
        mainViewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRequestsBinding.inflate(layoutInflater)
        App.daggerAppComponent.injectRequestFragment(this)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerRequests.adapter = RequestsAdapter()
        binding.recyclerRequests.layoutManager =
            GridLayoutManager(
                requireContext(), 1,
                RecyclerView.VERTICAL,
                false
            )
        viewModel.showAllRequests()
        binding.deleteRequests.setOnClickListener {
            viewModel.deleteAllRequests()
        }
        observeListRequestState()
    }

    private fun observeListRequestState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.stateList.collect { state ->
                    when (state) {
                        is RequestListState.Loading -> {
                            with(binding) {
                                loader.visibility = View.VISIBLE
                            }
                        }

                        is RequestListState.AllRequests -> {
                            with(binding) {
                                loader.visibility = View.GONE
                                allRequests = state.allRequests
                                if (allRequests.isEmpty()) {
                                    Toast.makeText(
                                        requireContext(),
                                        getString(R.string.data_is_empty),
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    showRequests(allRequests)
                                } else {
                                    showRequests(allRequests)
                                }
                            }
                        }

                        is RequestListState.Error -> {
                            Toast.makeText(
                                requireContext(),
                                getString(R.string.error),
                                Toast.LENGTH_SHORT
                            )
                                .show()
                        }
                    }
                }
            }
        }
    }

    private fun showRequests(allRequests: List<RequestRecord>) {
        (binding.recyclerRequests.adapter as RequestsAdapter).setData(allRequests)
    }
}