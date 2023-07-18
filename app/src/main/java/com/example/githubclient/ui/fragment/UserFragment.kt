package com.example.githubclient.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubclient.App
import com.example.githubclient.databinding.FragmentUserBinding
import com.example.githubclient.di.repository.RepositorySubcomponent
import com.example.githubclient.mvp.model.entity.GithubUser
import com.example.githubclient.mvp.presenter.UserPresenter
import com.example.githubclient.mvp.view.UserView
import com.example.githubclient.mvp.view.list.IImageLoader
import com.example.githubclient.ui.activity.BackButtonListener
import com.example.githubclient.ui.adapter.UserRepositoriesRVAdapter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class UserFragment : MvpAppCompatFragment(), UserView, BackButtonListener {
    private var _binding: FragmentUserBinding? = null
    private val binding
        get() = _binding!!

    var repositorySubcomponent: RepositorySubcomponent? = null
    var adapter: UserRepositoriesRVAdapter? = null

    @Inject
    lateinit var imageLoader: IImageLoader<ImageView>

    val presenter: UserPresenter by moxyPresenter {
        val user = arguments?.getParcelable<GithubUser>(USER_ARG) as GithubUser
        UserPresenter(user).apply {
            repositorySubcomponent = App.instance.initRepositorySubcomponent()
            repositorySubcomponent?.inject(this)
        }
    }

    companion object {
        private const val USER_ARG = "user"

        fun newInstance(user: GithubUser) = UserFragment().apply {
            arguments = Bundle().apply {
                putParcelable(USER_ARG, user)
            }
            App.instance.appComponent.inject(this)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) =
        FragmentUserBinding.inflate(inflater, container, false).also {
            _binding = it
        }.root

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun backPressed() = presenter.backPressed()
    override fun init() {
        binding.userRepositories.layoutManager = LinearLayoutManager(context)
        adapter = UserRepositoriesRVAdapter(presenter.userRepositoriesListPresenter).apply {
            repositorySubcomponent?.inject(this)
        }
        binding.userRepositories.adapter = adapter
    }

    override fun setUserLogin(login: String) {
        binding.userLogin.text = login
    }

    override fun setUserAvatar(url: String) {
        imageLoader.loadInto(url, binding.userPhoto)
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }
}