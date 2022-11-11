package teka.mobile.funzav1

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import teka.mobile.funzav1.viewTier.Activities.DownloadActivity3
import teka.mobile.funzav1.viewTier.Activities.NotesActivity


class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener  {
    lateinit var drawerLayout: DrawerLayout
    lateinit var navView:NavigationView
    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // drawer layout instance to toggle the menu icon to open
        // drawer and back button to close drawer
        drawerLayout = findViewById(R.id.my_drawer_layout)
        navView = findViewById(R.id.nav_view)
        actionBarDrawerToggle = ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close)

        // pass the Open and Close toggle for the drawer layout listener
        // to toggle the button
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        // to make the Navigation drawer icon always appear on the action bar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navView.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
       when(item.itemId){
           R.id.nav_home -> {
               Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show()
               true
           }R.id.nav_notes -> {
               Toast.makeText(this, "Notes", Toast.LENGTH_SHORT).show()
               val intent = Intent(this, NotesActivity::class.java)
               startActivity(intent)
               true
           }R.id.nav_library -> {
               Toast.makeText(this, "Library", Toast.LENGTH_SHORT).show()
               true
           }R.id.nav_clubs -> {
               Toast.makeText(this, "Clubs", Toast.LENGTH_SHORT).show()
               true
           }R.id.nav_downloads->{
               //Toast.makeText(this, "Downloads", Toast.LENGTH_SHORT).show()
               val intent = Intent(this, DownloadActivity3::class.java)
               startActivity(intent)
               true
           }else -> {
               false
           }

       }
        //close navigation drawer
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    // override the onSupportNavigateUp() function to launch the Drawer when the hamburger icon is clicked
    override fun onSupportNavigateUp(): Boolean {
        drawerLayout.openDrawer(navView)
        return true
    }

    override fun onBackPressed() {
        if (this.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}