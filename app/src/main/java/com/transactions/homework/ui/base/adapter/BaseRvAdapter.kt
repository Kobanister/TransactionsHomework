package com.transactions.homework.ui.base.adapter

import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRvAdapter<T, VH : BaseVH<T>> : RecyclerView.Adapter<VH>() {

    var items: ArrayList<T>? = null

    override fun getItemCount(): Int = items?.size ?: 0

    open fun setItems(items: ArrayList<T>, clone: Boolean = true) {
        if (clone) {
            if (this.items != null) {
                this.items?.clear()
            }
            this.items = ArrayList(items)
        } else {
            this.items = items
        }
        notifyDataSetChanged()
    }

    fun getItem(position: Int): T? {
        return if (position in 0 until itemCount)
            items?.get(position)
        else
            null
    }

    fun clearAdapter() {
        items?.clear()
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(@NonNull holder: VH, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }
}
