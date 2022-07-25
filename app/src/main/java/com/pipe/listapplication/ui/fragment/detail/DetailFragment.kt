package com.pipe.listapplication.ui.fragment.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.pipe.listapplication.databinding.FragmentTextCandidateBinding
import com.pipe.listapplication.di.NavigatorModule
import com.pipe.listapplication.domain.entity.PostEntity
import com.pipe.listapplication.extensions.POST_ENTITY_BUNDLE
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private var _binding: FragmentTextCandidateBinding? = null
    private val binding get() = _binding!!

    private var descriptionCandidate: PostEntity? = null

    @Inject
    lateinit var navigatorModule: NavigatorModule

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTextCandidateBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        descriptionCandidate = requireArguments().getParcelable(POST_ENTITY_BUNDLE)
        binding.post = descriptionCandidate
        binding.back.setOnClickListener { navigatorModule.navigateBack() }
    }

}