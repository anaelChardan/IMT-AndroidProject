package fr.android.achardan.library.domain.model

import android.os.Parcelable
import fr.android.achardan.genericlist.persistence.ItemProcessor

/**
 * Port to domain processor in Book
 */
interface BookProcessor : ItemProcessor<Book>, Parcelable