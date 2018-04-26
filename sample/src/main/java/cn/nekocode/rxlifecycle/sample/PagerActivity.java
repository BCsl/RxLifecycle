package cn.nekocode.rxlifecycle.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chensuilun on 2018/4/26.
 */
public class PagerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        /**
         * FIXME: 2018/4/26
         *
         * 如果当前 Fragment 因为 ViewPager 缓存机制被 destroy 掉，生命周期会走到 onDestroyView，下次再次 onCrate 的时候，因为内部的 Headless Fragment
         * 实例依然是存在的，内部使用的是 BehaviorProcessor 来发射生命周期，因为实例没有变，当前最后发射的事件是 onDestroyView，因此再次创建改 Fragment view 并添加
         * 到界面的时候，Headless Fragment 的显示的生命周期会相对落后，因此在主 Fragment 显示的生命周期中进行注册绑定的话 ，可能接受到上次最后发射的的 onDestroyView 事件而导致流的终止
         */
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(TextFragment.newInstance("index:0"));
        fragments.add(TextFragment.newInstance("index:1"));
        fragments.add(TextFragment.newInstance("index:2"));
        ViewPager viewPager = findViewById(R.id.vp_pager);
        viewPager.setAdapter(new BasePagerAdapter(getSupportFragmentManager(), fragments));

    }


}
