package dev.fb.android.tamboon.ui.adapter

import android.content.Context
import android.omise.charity.domain.model.Charity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.bumptech.glide.Glide
import dev.fb.android.tamboon.R
import kotlin.properties.Delegates

class CharityListAdapter(private val context: Context) : BaseAdapter() {

    private var dataSource: List<Charity> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    private val inflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val view: View
        val holder: CharityViewHolder

        if (convertView == null) {
            view = inflater.inflate(R.layout.view_charity_item, parent, false)

            holder = CharityViewHolder(view)
            holder.tvCharityName.text = dataSource[position].charityName
            Glide.with(context).load(dataSource[position].charityLogoUrl).into(holder.ivCharityLogo)
            view.tag = holder
        } else {
            view = convertView
            holder = convertView.tag as CharityViewHolder
        }

        return view
    }

    fun updateData(data: List<Charity>) {
        this.dataSource = data
    }

    override fun getItem(position: Int): Any {
        return dataSource[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return dataSource.size
    }
}