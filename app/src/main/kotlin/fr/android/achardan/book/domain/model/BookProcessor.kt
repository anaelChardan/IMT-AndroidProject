package fr.android.achardan.book.domain.model

import android.os.Parcelable
import fr.android.achardan.supportingboundedcontext.persistence.ItemProcessor

/**
 * Port to domain processor in Book bc
 */
interface BookProcessor : ItemProcessor<Book>, Parcelable