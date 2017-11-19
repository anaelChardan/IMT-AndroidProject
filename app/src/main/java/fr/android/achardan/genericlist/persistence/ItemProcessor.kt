package fr.android.achardan.genericlist.persistence

import android.os.Parcelable

/**
 * Generic port for a processor, could have been an invokable class if the language would have allowed it
 */
interface ItemProcessor<T> : Parcelable {
    /**
     * Only function to know what to process
     */
    fun process(processFunction: (List<T>) -> Unit, onError: (Throwable) -> Unit)
}