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
package cn.nekocode.rxlifecycle.transformer;

import android.support.annotation.NonNull;

import cn.nekocode.rxlifecycle.LifecyclePublisher;
import io.reactivex.Maybe;
import io.reactivex.MaybeSource;
import io.reactivex.MaybeTransformer;
import io.reactivex.functions.Predicate;
import io.reactivex.processors.BehaviorProcessor;

/**
 * @author nekocode (nekocode.cn@gmail.com)
 */
public class BindLifecycleMaybeTransformer<T> implements MaybeTransformer<T, T> {
    private final BehaviorProcessor<Integer> mLifecycleBehavior;
    private @LifecyclePublisher.Events
    int mDisposeEvent;

    private BindLifecycleMaybeTransformer() throws IllegalAccessException {
        throw new IllegalAccessException();
    }

    public BindLifecycleMaybeTransformer(@NonNull BehaviorProcessor<Integer> lifecycleBehavior, @LifecyclePublisher.Events int disposeLifecycle) {
        this.mLifecycleBehavior = lifecycleBehavior;
        this.mDisposeEvent = disposeLifecycle;
    }

    @Override
    public MaybeSource<T> apply(Maybe<T> upstream) {
        return upstream.takeUntil(
                mLifecycleBehavior.skipWhile(new Predicate<Integer>() {
                    @Override
                    public boolean test(@LifecyclePublisher.Events Integer event) throws Exception {
                        return (event & mDisposeEvent) == 0;
                    }
                })
        );
    }
}