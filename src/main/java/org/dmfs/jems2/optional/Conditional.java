/*
 * Copyright 2021 dmfs GmbH
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.dmfs.jems2.optional;

import org.dmfs.jems2.Optional;
import org.dmfs.jems2.Predicate;
import org.dmfs.jems2.Single;

import static org.dmfs.jems2.optional.Absent.absent;


/**
 * {@link Optional} that is present with the value of the provided target if it satisfies the given {@link Predicate}, otherwise it is absent.
 */
public final class Conditional<T> extends LazyDelegatingOptional<T>
{
    public Conditional(Predicate<? super T> predicate, Single<T> targetSingle)
    {
        super(() -> predicate.satisfiedBy(targetSingle.value()) ? new Just<>(targetSingle) : absent());
    }


    public Conditional(Predicate<T> predicate, T targetValue)
    {
        super(() -> predicate.satisfiedBy(targetValue) ? new Present<>(targetValue) : absent());
    }
}
