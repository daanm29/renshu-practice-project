package com.example.renshu.practice.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.renshu.R
import com.example.renshu.databinding.FragmentOverviewListBinding
import com.example.renshu.practice.adapter.CustomWordListItemAdapter
import com.example.shudomain.list.model.CustomList
import com.example.shupresentation.practice.list.ListOverviewNavigationAction
import com.example.shupresentation.practice.list.ListOverviewViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class ListOverviewFragment : DaggerFragment(R.layout.fragment_overview_list) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<ListOverviewViewModel> { viewModelFactory }
    private val ui by viewBinding<FragmentOverviewListBinding>()

    private val args by navArgs<ListOverviewFragmentArgs>()
    private val customListWordAdapter = CustomWordListItemAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ui.collapsingToolbar.title = requireContext().getString(R.string.custom_list_title, args.title.capitalize())

        observeViewModel()
        initNavigation()
    }

    private fun observeViewModel() {
        viewModel.navigation.observe(viewLifecycleOwner, ::handleNavigation)
        viewModel.customList.observe(viewLifecycleOwner, ::handleDate)
    }

    private fun handleNavigation(action: ListOverviewNavigationAction) {
        when (action) {
            ListOverviewNavigationAction.OpenPractice -> findNavController().navigate(
                ListOverviewFragmentDirections.actionListOverviewFragmentToPracticeFragment()
            )
        }
    }

    private fun handleDate(customList: CustomList) {
        ui.customListRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = customListWordAdapter
        }

        customListWordAdapter.submitList(customList.characters)

        ui.deleteListButton.setOnClickListener {
            viewModel.deleteList(customList.title)
        }
    }

    private fun initNavigation() {
        ui.toolbar.setNavigationOnClickListener { viewModel.openPractice() }
    }
}
