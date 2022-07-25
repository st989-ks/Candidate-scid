package com.pipe.listapplication.ui.fragment.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.pipe.listapplication.R
import com.pipe.listapplication.databinding.FragmentListBinding
import com.pipe.listapplication.di.NavigatorModule
import com.pipe.listapplication.extensions.POST_ENTITY_BUNDLE
import com.pipe.listapplication.extensions.SharedPrefs
import com.pipe.listapplication.presenter.GetListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ListFragment: Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    private var bundle: Bundle? = null

    @Inject
    lateinit var navigatorModule: NavigatorModule

    @Inject
    lateinit var getListViewModel: GetListViewModel

    @Inject
    lateinit var listAdapter: ListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bundle = Bundle()
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecycler()
        observer()
        initButton()
    }

    private fun initRecycler() {
        binding.recycler.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(activity)
            adapter = listAdapter.withLoadStateHeaderAndFooter(
                header = ReposLoadStateAdapter(),
                footer = ReposLoadStateAdapter()
            )
        }
    }

    private fun initButton() {
        listAdapter.setOnPostClickListener { postEntity ->
            bundle?.putParcelable(POST_ENTITY_BUNDLE, postEntity)
            navigatorModule.navigate(R.id.detailFragment, bundle)
        }
    }

    private fun observer() {
        lifecycleScope.launch {
            getListViewModel.getFileDataStream().collectLatest {
                listAdapter.submitData(it)
            }
        }
    }

}