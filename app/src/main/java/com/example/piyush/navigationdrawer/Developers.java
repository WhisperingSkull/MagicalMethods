package com.example.piyush.navigationdrawer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.ExpandableDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

public class Developers extends AppCompatActivity {
    Toolbar toolbar;
    Drawer result=null;
    AccountHeader headerResult=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developers);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Developers");

        // Create the AccountHeader
        headerResult= new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.header)
                .addProfiles(
                        new ProfileDrawerItem().withName("Tony Stark").withEmail("ironman@jarvis.com").withIcon(getResources().getDrawable(R.drawable.tony_stark))

                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
                        return false;
                    }
                })
                .build();


        result = new DrawerBuilder()
                .withActivity(this)
                .withAccountHeader(headerResult)
                .withToolbar(toolbar)
                .withActionBarDrawerToggleAnimated(true)

                .addDrawerItems(
                        new PrimaryDrawerItem().withIdentifier(1).withName("Home").withIcon(getResources().getDrawable(R.drawable.home)),
                        new PrimaryDrawerItem().withIdentifier(2).withName("Explore").withIcon(getResources().getDrawable(R.drawable.explore)),

                        new ExpandableDrawerItem().withName("Your courses").withIcon(getResources().getDrawable(R.drawable.your_courses)).withIdentifier(3).withSelectable(false).withSubItems(
                                new SecondaryDrawerItem().withName("Course 1").withLevel(2).withIdentifier(31).withSelectable(false),
                                new SecondaryDrawerItem().withName("Course 2").withLevel(2).withIdentifier(32).withSelectable(false)
                        ),

                        new PrimaryDrawerItem().withIdentifier(4).withName("Help and Feedback").withIcon(getResources().getDrawable(R.drawable.help_and_feedback)),
                        new PrimaryDrawerItem().withIdentifier(5).withName("Developers").withIcon(getResources().getDrawable(R.drawable.developers))


                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        if (drawerItem != null) {
                            Intent intent = null;
                            if (drawerItem.getIdentifier() == 1) {
                                intent = new Intent(Developers.this, Home.class);
                            } else if (drawerItem.getIdentifier() == 2) {
                                intent = new Intent(Developers.this, Explore.class);
                            } else if (drawerItem.getIdentifier() ==4) {
                                intent = new Intent(Developers.this, HelpAndFeedback.class);
                           // } else if (drawerItem.getIdentifier() ==5) {
                            //    intent = new Intent(Developers.this, Developers.class);
                            }
                            if (intent != null) {
                                Developers.this.startActivity(intent);
                            }
                        }

                        return false;
                    }

                })
                // .withSavedInstance(savedInstanceState)
                // .withShowDrawerOnFirstLaunch(true)
                .build();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState = result.saveInstanceState(outState);
        outState = headerResult.saveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }


    @Override
    public void onBackPressed() {
        if (result != null && result.isDrawerOpen()) {
            result.closeDrawer();
        } else {
            super.onBackPressed();
        }

    }
}
