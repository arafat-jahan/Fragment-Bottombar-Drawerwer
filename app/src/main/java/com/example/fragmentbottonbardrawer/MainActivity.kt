package com.example.fragmentbottonbardrawer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.fragmentbottonbardrawer.R
import com.example.fragmentbottonbardrawer.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    val homeFragment = HomeFragment()
    val personFragment = PersonFragment()
    val settingsFragment = SettingsFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //assign drawerlayout to actionbardrawertoggole
        actionBarDrawerToggle = ActionBarDrawerToggle(this,binding.drawerLayout, R.string.nav_open, R.string.nav_close)

        //add action listener
        binding.drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        //assign fragment to a variable


        //set a fragment as a default
        setCurrentFragment(homeFragment)

        //item click for open new fragment
        binding.navview.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.home ->{
                    setCurrentFragment(homeFragment)
                    //close drawer when we click new item
                    binding.drawerLayout.closeDrawers()
                }

                R.id.person ->{
                    setCurrentFragment(personFragment)
                    binding.drawerLayout.closeDrawers()
                }
                R.id.settings ->{
                    setCurrentFragment(settingsFragment)
                    binding.drawerLayout.closeDrawers()
                }
            }
            true
        }

        //set navigation item selected listenser for get the navigation translation
        binding.bottomBar.setOnNavigationItemSelectedListener {
            //use when to get id by condition
            when(it.itemId){
                R.id.home ->setCurrentFragment(homeFragment)

                R.id.person ->setCurrentFragment(personFragment)
                R.id.settings ->setCurrentFragment(settingsFragment)
            }
            true
        }

    }

    // drawer open close
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            true
        }
        else super.onOptionsItemSelected(item)
    }

    //create a method for set fragment and simplify the code for reassign
    private fun setCurrentFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentLayout, fragment)
            commit()
        }
    }


}