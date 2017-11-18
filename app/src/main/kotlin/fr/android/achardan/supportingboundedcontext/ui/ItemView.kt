package fr.android.achardan.supportingboundedcontext.ui

import android.view.View

interface ItemView<in T> {
    fun bind(t: T): View
}