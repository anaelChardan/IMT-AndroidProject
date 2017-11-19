package fr.android.achardan.genericlist.controller

import android.os.Parcelable
import fr.android.achardan.genericlist.ui.handler.ItemListener

/**
 * Public contract for ListActivity
 */
interface ListActivity<T : Parcelable> : ItemListener<T>