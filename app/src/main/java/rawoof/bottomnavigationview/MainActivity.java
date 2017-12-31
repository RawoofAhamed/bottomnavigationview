package rawoof.bottomnavigationview;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import rawoof.bottomnavigationview.adapter.ViewPagerAdapter;
import rawoof.bottomnavigationview.fragment.DashboardFragment;
import rawoof.bottomnavigationview.fragment.HomeFragment;
import rawoof.bottomnavigationview.fragment.NotificationFragment;


public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;

    private BottomNavigationView bottomNavigationView;

    private HomeFragment homeFragment;
    private DashboardFragment dashboardFragment;
    private NotificationFragment notificationFragment;

    MenuItem previousMenuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initializing viewPager
        viewPager = findViewById(R.id.viewpager);

        bottomNavigationView = findViewById(R.id.navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.navigation_home:
                                viewPager.setCurrentItem(0);
                                break;
                            case R.id.navigation_dashboard:
                                viewPager.setCurrentItem(1);
                                break;
                            case R.id.navigation_notifications:
                                viewPager.setCurrentItem(2);
                        }
                        return false;
                    }
                });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (previousMenuItem != null) {
                    previousMenuItem.setChecked(false);
                }
                else
                {
                    bottomNavigationView.getMenu().getItem(0).setChecked(false);
                }
                bottomNavigationView.getMenu().getItem(position).setChecked(true);
                previousMenuItem = bottomNavigationView.getMenu().getItem(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        setupViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        homeFragment=new HomeFragment();
        dashboardFragment =new DashboardFragment();
        notificationFragment = new NotificationFragment();
        adapter.addFragment(homeFragment);
        adapter.addFragment(dashboardFragment);
        adapter.addFragment(notificationFragment);
        viewPager.setAdapter(adapter);
    }



}
