package fr.android.achardan.supportingboundedcontext.controller

import android.content.res.Configuration
import android.os.Bundle
import android.os.Parcelable
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import fr.android.achardan.book.infrastructure.persistence.api.retrofit.RetrofitItemProcessor
import fr.android.achardan.supportingboundedcontext.persistence.ItemProcessor
import fr.android.achardan.supportingboundedcontext.ui.DetailFragment
import fr.android.achardan.supportingboundedcontext.ui.ListFragment

/**
 * Abstract helper to build a list activity
 */
abstract class AbstractListActivity<T : Parcelable> : AppCompatActivity(), ListActivity<T> {
    protected var selectedItem: T? = null

    abstract fun contentViewLayoutId(): Int
    abstract fun containerLayoutFrameId(): Int
    abstract fun listFragmentName(): String
    abstract fun listLayoutId(): Int
    abstract fun recycledViewId(): Int
    abstract fun itemViewListLayoutId(): Int
    abstract fun itemProcessor(): ItemProcessor<T>
    abstract fun detailLayoutFrameId(): Int
    abstract fun detailLayoutId(): Int
    abstract fun detailFragmentName(): String
    abstract fun instantiateDetailFragment(): DetailFragment

    override fun clicked(item: T) {
        selectedItem = item
        displayDetailFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(contentViewLayoutId())

        supportFragmentManager
                .beginTransaction()
                .replace(
                        containerLayoutFrameId(),
                        ListFragment.create(
                                listLayoutId(),
                                recycledViewId(),
                                itemViewListLayoutId(),
                                RetrofitItemProcessor()
                        ),
                        listFragmentName())
                .commit()
    }

    private fun displayDetailFragment() {
        if (Configuration.ORIENTATION_PORTRAIT == resources.configuration.orientation) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(
                            containerLayoutFrameId(),
                            instantiateDetailFragment() as Fragment,
                            detailFragmentName()
                    )
                    .addToBackStack("fromList")
                    .commit()

            return
        }

        supportFragmentManager
                .beginTransaction()
                .replace(
                        detailLayoutFrameId(),
                        instantiateDetailFragment() as Fragment,
                        detailFragmentName()
                )
                .commit()
    }
}
