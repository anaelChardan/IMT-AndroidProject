package fr.android.achardan.genericlist.ui

import android.os.Bundle
import android.os.Parcelable
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

abstract class AbstractDetailedFragment<T : Parcelable> : DetailFragment, Fragment() {
    protected var currentItem: T? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(arguments.getInt(DetailFragment.DETAIL_VIEW_ID_KEY), container, false)
        currentItem = arguments.getParcelable(DetailFragment.PARCELABLE_OBJECT_KEY)

        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindObjectToView()
    }
}

