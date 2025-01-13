package com.example.alphatesttasktimofeev.presentation.cardInfo.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.alphatesttasktimofeev.App
import com.example.alphatesttasktimofeev.R
import com.example.alphatesttasktimofeev.databinding.FragmentInformationBinding
import com.example.alphatesttasktimofeev.domain.entity.CardInfo
import com.example.alphatesttasktimofeev.presentation.MainViewModel
import com.example.alphatesttasktimofeev.presentation.MainViewModelFactory
import com.example.alphatesttasktimofeev.presentation.cardInfo.state.State
import kotlinx.coroutines.launch
import javax.inject.Inject

class InformationFragment : Fragment() {
    private var _binding: FragmentInformationBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory

    private val viewModel: MainViewModel by activityViewModels {
        mainViewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInformationBinding.inflate(layoutInflater)
        App.daggerAppComponent.injectInformationFragment(this)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.checkInput(false)
        binding.searchButton.setOnClickListener {
            val inputText = binding.searchString.text.toString()
            viewModel.onSearchButtonClick(inputText)
        }
        binding.searchString.addTextChangedListener { text ->
            if (text != null) {
                viewModel.checkInput(text.length in 6..8)
            }
        }
        binding.btnSeeRequests.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentInformation_to_fragmentRequests)
        }
        observeViewState()
        observeButtonState()
    }

    private fun observeButtonState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.buttonState.collect { buttonState ->
                    binding.searchButton.isEnabled = buttonState
                }
            }
        }
    }

    private fun observeViewState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { state ->
                    setStateValues(state)
                }
            }
        }
    }

    private fun showCardInfo(cardInfo: CardInfo) {
        with(binding) {
            nameOfCountry.text = cardInfo.country?.name
            latitude.text = cardInfo.country?.latitude.toString()
            longitude.text = cardInfo.country?.longitude.toString()
            typeOfCard.text = cardInfo.scheme
            bank.text = cardInfo.bank?.name
            url.text = cardInfo.bank?.url
            phone.text = cardInfo.bank?.phone
            city.text = cardInfo.bank?.city
        }
    }

    private fun setStateValues(state: State) {
        binding.progress.visibility = if (state is State.Loading) View.VISIBLE else View.INVISIBLE
        binding.searchString.isEnabled = state !is State.Loading
        binding.textMessage.visibility =
            if (state is State.Loading || state is State.Success) View.INVISIBLE else View.VISIBLE
        binding.cardInfo.visibility = if (state is State.Success) View.VISIBLE else View.INVISIBLE

        if (state is State.Success) {
            showCardInfo(state.cardInfo)
        }
        if (state is State.RequestNotCompleted) {
            binding.textMessage.text = getString(R.string.request_not_completed)
        }
        if (state is State.Error) {
            binding.textMessage.text = state.error
        }
    }
}