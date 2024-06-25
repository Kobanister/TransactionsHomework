package com.transactions.homework.ui.base.adapter

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseVH<T>(parent: View) : RecyclerView.ViewHolder(parent) {

    abstract fun bind(item: T)

    protected fun ctx(): Context {
        return itemView.context
    }
}
