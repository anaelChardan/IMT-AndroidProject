package fr.android.achardan.supportingboundedcontext.ui

import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import fr.android.achardan.supportingboundedcontext.persistence.ItemProcessor
import fr.android.achardan.supportingboundedcontext.ui.handler.ItemListener

class ListFragment<T : Parcelable> : Fragment() {
    private var items: ArrayList<T> = ArrayList()
    private lateinit var itemListener: ItemListener<T>
    private lateinit var adapter: RecyclerAdapter<T>

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        this.itemListener = context as ItemListener<T>
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(arguments.getInt(LIST_LAYOUT_ID_KEY), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val listView: RecyclerView = view.findViewById(arguments.getInt(RECYCLED_VIEW_ID))

        listView.layoutManager = LinearLayoutManager(view.context)
        adapter = RecyclerAdapter(
                LayoutInflater.from(view.context),
                items,
                arguments.getInt(ITEM_LAYOUT_ID),
                itemListener
        )

        listView.adapter = adapter;

        //TODO use dependency injection instead of that
        val itemProcessor: ItemProcessor<T> = arguments.get(ITEM_PROCESSOR_KEY) as ItemProcessor<T>

        if (savedInstanceState != null) {
            refresh(savedInstanceState.getParcelableArrayList(ITEMS_KEY))
            return
        }

        itemProcessor.process(
                { refresh(it) },
                { Toast.makeText(view.context, "Items are not reachable", Toast.LENGTH_LONG).show() }
        )
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList(ITEMS_KEY, items)
    }

    private fun refresh(items: List<T>) {
        this.items.clear()
        this.items.addAll(items)
        adapter.notifyDataSetChanged()
    }

    companion object {
        val LIST_LAYOUT_ID_KEY = "LIST_LAYOUT_ID"
        val RECYCLED_VIEW_ID = "ASSOCIATED_LIST_RECYCLED_VIEW_ID"
        val ITEM_LAYOUT_ID = "ITEM_LAYOUT_ID"
        val ITEMS_KEY = "ITEMS_KEY"
        val ITEM_PROCESSOR_KEY = "ITEM_PROCESSOR"

        fun <T : Parcelable> create(
                layoutListId: Int,
                recycledViewId: Int,
                itemViewId: Int,
                processor: ItemProcessor<T>
        ): ListFragment<T> {
            val listFragment: ListFragment<T> = ListFragment()

            val bundle = Bundle()
            bundle.putInt(LIST_LAYOUT_ID_KEY, layoutListId)
            bundle.putInt(RECYCLED_VIEW_ID, recycledViewId)
            bundle.putParcelable(ITEM_PROCESSOR_KEY, processor)
            bundle.putInt(ITEM_LAYOUT_ID, itemViewId)

            listFragment.arguments = bundle

            return listFragment
        }
    }
}
