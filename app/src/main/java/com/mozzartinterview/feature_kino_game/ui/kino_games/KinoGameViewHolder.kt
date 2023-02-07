package com.mozzartinterview.feature_kino_game.ui.kino_games

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.mozzartinterview.core.data.toDrawEndString
import com.mozzartinterview.core.data.toRemaningTimeString
import com.mozzartinterview.databinding.ItemKinoGameBinding

class KinoGameViewHolder(
    private val itemBinding: ItemKinoGameBinding,
    private val clickListener: ((Int) -> Unit)? = null,
    private val context: Context
) : RecyclerView.ViewHolder(itemBinding.root),
    View.OnClickListener {

    private lateinit var _item: KinoGameItem

    init {
        itemBinding.root.setOnClickListener(this)
    }

    fun bind(
        item: KinoGameItem
    ) {
        _item = item
        itemBinding.endTime.text = _item.drawTime.toDrawEndString()
        itemBinding.leftTime.text = _item.remainingTime.toRemaningTimeString()
    }

    override fun onClick(v: View?) {
        clickListener?.invoke(_item.drawId)
    }
}
