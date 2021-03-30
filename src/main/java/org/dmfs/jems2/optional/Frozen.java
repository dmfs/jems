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

import static org.dmfs.jems2.optional.Absent.absent;


/**
 * An {@link Optional} decorator which avoids frequent (potentially expensive) evaluation of another {@link Optional} by retaining the result of the first
 * evaluation.
 */
public final class Frozen<T> extends LazyDelegatingOptional<T>
{
    public Frozen(Optional<T> delegate)
    {
        super(() -> delegate.isPresent() ? new Present<>(delegate.value()) : absent());
    }
}
