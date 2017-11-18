package fr.android.achardan.supportingboundedcontext.ui

import android.os.Bundle

interface DetailFragment {
    fun bindObjectToView()

    fun setArguments(args: Bundle)

    companion object {
        val PARCELABLE_OBJECT_KEY = "ITEM"
        val DETAIL_VIEW_ID_KEY = "DETAIL_VIEW_ID_KEY"
    }
}