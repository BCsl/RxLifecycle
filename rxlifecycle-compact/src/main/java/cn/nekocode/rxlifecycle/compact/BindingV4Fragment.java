/*
 * Copyright 2016 nekocode
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.nekocode.rxlifecycle.compact;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.nekocode.rxlifecycle.LifecyclePublisher;

/**
 * @author nekocode (nekocode.cn@gmail.com)
 */
public class BindingV4Fragment extends Fragment {
    private final LifecyclePublisher mLifecyclePublisher = new LifecyclePublisher();

    public BindingV4Fragment() {
    }

    public LifecyclePublisher getLifecyclePublisher() {
        return mLifecyclePublisher;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mLifecyclePublisher.onAttach();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mLifecyclePublisher.onAttach();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLifecyclePublisher.onCreate();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mLifecyclePublisher.onCreateView();
        return null;
    }

    @Override
    public void onStart() {
        super.onStart();
        mLifecyclePublisher.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        mLifecyclePublisher.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mLifecyclePublisher.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        mLifecyclePublisher.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mLifecyclePublisher.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mLifecyclePublisher.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mLifecyclePublisher.onDetach();
    }
}
