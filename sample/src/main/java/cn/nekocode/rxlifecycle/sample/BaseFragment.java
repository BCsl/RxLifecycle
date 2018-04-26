package cn.nekocode.rxlifecycle.sample;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by chensuilun on 2018/4/26.
 */
public abstract class BaseFragment extends Fragment {
    protected View mRootView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (BuildConfig.DEBUG) {
            Log.v(this.toString(), String.format("onAttach:"));
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (BuildConfig.DEBUG) {
            Log.v(this.toString(), String.format("onCreate:"));
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (BuildConfig.DEBUG) {
            Log.v(this.toString(), String.format("onCreateView:"));
        }
        mRootView = onCreateContentView(inflater, container);
        return mRootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (BuildConfig.DEBUG) {
            Log.v(this.toString(), String.format("onViewCreated:"));
        }
        initView(view, savedInstanceState);
        initData(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (BuildConfig.DEBUG) {
            Log.v(this.toString(), String.format("onActivityCreated:"));
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (BuildConfig.DEBUG) {
            Log.v(this.toString(), String.format("onStart:"));
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (BuildConfig.DEBUG) {
            Log.v(this.toString(), String.format("onResume:"));
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (BuildConfig.DEBUG) {
            Log.v(this.toString(), String.format("onPause:"));
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (BuildConfig.DEBUG) {
            Log.v(this.getClass().getSimpleName(), String.format("onStop:"));
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (BuildConfig.DEBUG) {
            Log.v(this.toString(), String.format("onDestroyView:"));
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (BuildConfig.DEBUG) {
            Log.v(this.toString(), String.format("onDestroy:"));
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (BuildConfig.DEBUG) {
            Log.v(this.toString(), String.format("onDetach:"));
        }
    }


    protected View onCreateContentView(LayoutInflater inflater, ViewGroup container) {
        View view = getView();
        if (view == null) {
            view = inflater.inflate(getContentView(), container, false);
        }
        return view;
    }

    protected abstract void initView(View root, Bundle savedInstanceState);

    protected abstract void initData(Bundle savedInstanceState);


    protected abstract int getContentView();
}
