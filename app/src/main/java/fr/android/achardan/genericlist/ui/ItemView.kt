package fr.android.achardan.genericlist.ui

import android.view.View

interface ItemView<in T> {
    fun bind(t: T): View
}