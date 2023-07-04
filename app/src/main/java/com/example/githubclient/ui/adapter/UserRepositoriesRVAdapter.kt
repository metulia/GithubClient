package com.example.githubclient.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.githubclient.databinding.ItemUserRepositoryBinding
import com.example.githubclient.mvp.presenter.list.IUserRepositoryListPresenter
import com.example.githubclient.mvp.view.list.UserRepositoryItemView

class UserRepositoriesRVAdapter(val presenter: IUserRepositoryListPresenter) :
    RecyclerView.Adapter<UserRepositoriesRVAdapter.ViewHolder>() {

    inner class ViewHolder(val vb: ItemUserRepositoryBinding) : RecyclerView.ViewHolder(vb.root),
        UserRepositoryItemView {
        override var pos = -1

        override fun setName(text: String) = with(vb) {
            userRepositoryName.text = text
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            ItemUserRepositoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        ).apply {
            itemView.setOnClickListener {
                presenter.itemClickListener?.invoke(this)
            }
        }

    override fun getItemCount() = presenter.getCount()

    override fun onBindViewHolder(holder: UserRepositoriesRVAdapter.ViewHolder, position: Int) =
        presenter.bindView(holder.apply {
            pos = position
        })
}