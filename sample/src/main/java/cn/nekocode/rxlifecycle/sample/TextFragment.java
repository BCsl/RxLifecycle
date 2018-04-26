package cn.nekocode.rxlifecycle.sample;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import cn.nekocode.rxlifecycle.LifecyclePublisher;
import cn.nekocode.rxlifecycle.compact.RxLifecycleCompact;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static cn.nekocode.rxlifecycle.LifecyclePublisher.ON_CREATE_VIEW;

/**
 * Created by chensuilun on 2018/4/26.
 */
public class TextFragment extends BaseFragment {

    public static final String KEY = "KEY";

    public static TextFragment newInstance(String title) {
        Bundle args = new Bundle();
        args.putString(KEY, title);
        TextFragment fragment = new TextFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initView(View root, Bundle savedInstanceState) {
        ((TextView) root.findViewById(R.id.tv_text)).setText(getArguments().getString(KEY));
    }

    CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    @Override
    protected void initData(Bundle savedInstanceState) {
        final String key = getArguments().getString(KEY);
        /**
         * FIXME: 2018/4/26
         *
         * 如果当前 Fragment 因为 ViewPager 缓存机制被 destroy 掉，生命周期会走到 onDestroyView，下次再次 onCrate 的时候，因为内部的 Headless Fragment
         * 实例依然是存在的，内部使用的是 BehaviorProcessor 来发射生命周期，因为实例没有变，当前最后发射的事件是 onDestroyView，因此再次创建改 Fragment view 并添加
         * 到界面的时候，Headless Fragment 的显示的生命周期会相对落后，因此在主 Fragment 显示的生命周期中进行注册绑定的话 ，可能接受到上次最后发射的的 onDestroyView 事件而导致流的终止
         */
        mCompositeDisposable.add(RxLifecycleCompact.bind(this)
                .asObservable()
                .distinctUntilChanged()
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) {
                        if (BuildConfig.DEBUG) {
                            Log.v(TextFragment.this.toString(), String.format("accept:" + integer));
                        }
                        if (integer == ON_CREATE_VIEW) {
                            Observable.interval(0, 2, TimeUnit.SECONDS)
                                    .compose(RxLifecycleCompact.bindUntil(TextFragment.this, LifecyclePublisher.ON_DESTROY_VIEW).<Long>withObservable())
                                    .subscribeOn(Schedulers.computation())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(new Consumer<Long>() {
                                        @Override
                                        public void accept(Long n) {
                                            Log.v(key, "bindUntil destroy view accept: " + n.toString());
                                        }
                                    }, new Consumer<Throwable>() {
                                        @Override
                                        public void accept(Throwable throwable) throws Exception {

                                        }
                                    }, new Action() {
                                        @Override
                                        public void run() throws Exception {
                                            if (BuildConfig.DEBUG) {
                                                Log.v(TextFragment.this.toString(), String.format("onComplete:"));
                                            }
                                        }
                                    });
                        }
                    }
                }));


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mCompositeDisposable.clear();
    }

    @Override
    protected int getContentView() {
        return R.layout.fragment_text;
    }
}
