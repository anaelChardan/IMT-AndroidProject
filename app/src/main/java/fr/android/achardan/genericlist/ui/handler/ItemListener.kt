package fr.android.achardan.genericlist.ui.handler

interface ItemListener<T> {
    fun clicked(item: T)
}