package com.mozzartinterview.feature_kino_game.ui.talon

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.mozzartinterview.R
import com.mozzartinterview.databinding.ItemTalonBinding

class KinoTalonItemViewHolder(
    private val itemBinding: ItemTalonBinding,
    private val clickListener: ((Int) -> Unit)? = null,
    private val context: Context
) : RecyclerView.ViewHolder(itemBinding.root),
    View.OnClickListener {

    private lateinit var _item: KinoTalonItem

    init {
        itemBinding.root.setOnClickListener(this)
    }

    fun bind(
        item: KinoTalonItem
    ) {
        _item = item
        itemBinding.numberText.text = _item.number.toString()
        if (_item.isSelected) {
            itemBinding.itemTalonLayout.setBackgroundResource(R.drawable.circle)
        } else {
            itemBinding.itemTalonLayout.setBackgroundResource(0)
        }
    }

    override fun onClick(v: View?) {
        clickListener?.invoke(_item.number)
    }
}