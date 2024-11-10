package com.example.android_task_rasool.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.android_task_rasool.model.Story
import com.example.android_task_rasool.databinding.ItemStoryBinding
import com.example.android_task_rasool.databinding.MyItemStoryBinding

class StoryAdapter(private val stories: List<Story>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val VIEW_TYPE_MY_STORY = 1
        private const val VIEW_TYPE_OTHER_STORY = 2
    }
  var context:Context?=null
    override fun getItemViewType(position: Int): Int {
        return if (stories[position].isMyStory) VIEW_TYPE_MY_STORY else VIEW_TYPE_OTHER_STORY
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        context=parent.context
        val inflater = LayoutInflater.from(parent.context)
        return if (viewType == VIEW_TYPE_MY_STORY) {
            val binding = MyItemStoryBinding.inflate(inflater, parent, false)
            MyStoryViewHolder(binding)
        } else {
            val binding = ItemStoryBinding.inflate(inflater, parent, false)
            OtherStoryViewHolder(binding)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val story = stories[position]
        if (holder is MyStoryViewHolder) {
            holder.bind(story)
        } else if (holder is OtherStoryViewHolder) {
            holder.bind(story)
        }
    }

    override fun getItemCount(): Int = stories.size

    // ViewHolder for `my_item_story` layout using View Binding
    inner class MyStoryViewHolder(private val binding: MyItemStoryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(story: Story) {
           // binding.addStoryId.setImageDrawable(story.storyImg)
            // Set other data and click listeners if needed
        }
    }

    // ViewHolder for `item_story` layout using View Binding
    inner class OtherStoryViewHolder(private val binding: ItemStoryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(story: Story) {
            binding.storyImg.setImageDrawable(context?.let { ContextCompat.getDrawable(it,story.storyImg) })
            // Set other data and click listeners if needed
        }
    }
}

