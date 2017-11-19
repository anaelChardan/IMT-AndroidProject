package fr.android.achardan.genericlist.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import fr.android.achardan.genericlist.ui.handler.ItemListener

/**
 * Generic RecyclerAdapter
 */
class RecyclerAdapter<T>(
        private val inflater: LayoutInflater,
        private val collection: List<T>,
        private val resource: Int,
        private val listener: ItemListener<T>
) : RecyclerView.Adapter<RecyclerAdapter<T>.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        this.collection.getOrNull(position)?.let {
            val item: T = it

            (holder?.itemView as ItemView<T>)
                    .bind(item)
                    .setOnClickListener { listener.clicked(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(inflater.inflate(this.resource, parent, false))


    override fun getItemCount(): Int = collection.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
