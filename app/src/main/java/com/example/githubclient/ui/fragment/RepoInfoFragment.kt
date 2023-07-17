package com.example.githubclient.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.githubclient.App
import com.example.githubclient.databinding.FragmentRepoInfoBinding
import com.example.githubclient.mvp.model.entity.GithubRepository
import com.example.githubclient.mvp.presenter.RepoInfoPresenter
import com.example.githubclient.mvp.view.RepoInfoView
import com.example.githubclient.ui.activity.BackButtonListener
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class RepoInfoFragment : MvpAppCompatFragment(), RepoInfoView, BackButtonListener {

    companion object {
        private const val REPO_ARG = "repo"

        fun newInstance(repo: GithubRepository) = RepoInfoFragment().apply {
            arguments = Bundle().apply {
                putParcelable(REPO_ARG, repo)
            }
        }
    }

    private var _binding: FragmentRepoInfoBinding? = null
    private val binding
        get() = _binding!!

    val presenter: RepoInfoPresenter by moxyPresenter {
        val repo = arguments?.getParcelable<GithubRepository>(REPO_ARG) as GithubRepository
        RepoInfoPresenter(repo).apply {
            App.instance.initRepoInfoSubcomponent()?.inject(this)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) =
        FragmentRepoInfoBinding.inflate(inflater, container, false).also {
            _binding = it
        }.root

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun init() {}

    override fun setTitle(text: String) {
        binding.tvTitle.text = text
    }

    override fun setForksCount(text: String) {
        binding.tvForksCount.text = text
    }

    override fun setLanguage(text: String) {
        binding.tvLanguage.text = text
    }

    override fun setDescription(text: String) {
        binding.tvDescription.text = text
    }

    override fun setCreatedAt(text: String) {
        binding.tvCreatedAt.text = text
    }

    override fun setUpdatedAt(text: String) {
        binding.tvUpdatedAt.text = text
    }

    override fun backPressed() = presenter.backPressed()
}