package dev.fb.android.tamboon.ui.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import dev.fb.android.tamboon.R

class CharityViewHolder(view: View) {
    var clCharityItem: ConstraintLayout = view.findViewById(R.id.cl_charity_item)
    var tvCharityName: TextView = view.findViewById(R.id.tv_charity_name)
    var ivCharityLogo: ImageView = view.findViewById(R.id.iv_charity_logo)
}