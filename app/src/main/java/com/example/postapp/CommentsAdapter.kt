package com.example.postapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.postapp.databinding.CommentsListItemsBinding

class CommentsAdapter(var commentsList: List<Comments>): RecyclerView.Adapter<CommentsAdapter.CommentsViewHolder>() {    override fun onCreateViewHolder(        parent: ViewGroup,        viewType: Int    ): CommentsViewHolder {
    val binding = CommentsListItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    return CommentsViewHolder(binding)
}

    override fun getItemCount(): Int = commentsList.size

    override fun onBindViewHolder(holder: CommentsViewHolder, position: Int) {
        val comment = commentsList[position]
        holder.binding.tvname.text = comment.name
        holder.binding.tvcomments.text = comment.body
    }

    class CommentsViewHolder(val binding: CommentsListItemsBinding):RecyclerView.ViewHolder(binding.root){

    }



}