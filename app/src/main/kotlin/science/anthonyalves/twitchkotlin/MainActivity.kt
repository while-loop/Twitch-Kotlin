package science.anthonyalves.twitchkotlin

import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import science.anthonyalves.twitchkotlin.Fragments.GameFragment
import science.anthonyalves.twitchkotlin.Fragments.StreamFragment

class MainActivity : AppCompatActivity() {

    /**
     * The [android.support.v4.view.PagerAdapter] that will provide
     * fragments for each of the sections. We use a
     * [FragmentPagerAdapter] derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * [android.support.v4.app.FragmentStatePagerAdapter].
     */

    /**
     * The [ViewPager] that will host the section contents.
     */
    private var mViewPager: ViewPager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        // Set up the ViewPager with the sections adapter.
        viewPager!!.adapter = SectionsPagerAdapter(supportFragmentManager)
        tabLayout.setupWithViewPager(viewPager)
        setupColors()

        supportActionBar
    }

    private fun setupColors() {
        val black = 0xFF000000.toInt()
        val white = 0xFFFFFFFF.toInt()
        val regular = 0xFF3D8E33.toInt()
        val dark = 0xFF2A6123.toInt()
        toolbar.setBackgroundColor(regular)
        toolbar.setTitleTextColor(white)
        toolbar.setSubtitleTextColor(white)
        tabLayout.setBackgroundColor(regular)
        tabLayout.setTabTextColors(0xAAFFFFFF.toInt(), white)
        fab.setBackgroundColor(regular)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.navigationBarColor = regular
            window.statusBarColor = dark
        }
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        when (item.itemId) {
            R.id.action_settings -> return true
        }

        return super.onOptionsItemSelected(item)
    }


    /**
     * A [FragmentPagerAdapter] that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    inner class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment = when (position) {
            0 -> StreamFragment()
            1 -> GameFragment()
            else -> StreamFragment()
        }

        override fun getCount(): Int = 2

        override fun getPageTitle(position: Int): CharSequence = when (position) {
            0 -> "Streams"
            1 -> "Games"
            else -> "Streams"
        }
    }
}
