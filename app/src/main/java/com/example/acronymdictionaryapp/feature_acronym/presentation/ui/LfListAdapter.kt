package com.example.acronymdictionaryapp.feature_acronym.presentation.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.acronymdictionaryapp.databinding.TextItemViewBinding

/**
 * Adapter class for Recyclerview used to present abbreviations of given acronym or initialism.
 * It creates Views within RecyclerView and binds each View with data received from view holder
 */
class LfListAdapter : RecyclerView.Adapter<MainViewHolder>() {

    private var largeFormList = mutableListOf<String>()

    @SuppressLint("NotifyDataSetChanged")
    fun setLfList(lfs: List<String>) {
        this.largeFormList = lfs.toMutableList()
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return largeFormList.size
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val largeForm = largeFormList[position]
        holder.binding.lfTv.text = largeForm
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = TextItemViewBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }
}

class MainViewHolder(val binding: TextItemViewBinding) : RecyclerView.ViewHolder(binding.root)