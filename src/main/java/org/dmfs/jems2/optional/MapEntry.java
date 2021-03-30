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

import java.util.Map;


/**
 * An {@link Optional} element of a {@link Map}. This is present if the given {@link Map} contains a non-{@code null} value for the given key.
 */
public final class MapEntry<V> extends LazyDelegatingOptional<V>
{
    public <K> MapEntry(Map<? super K, ? extends V> map, K key)
    {
        super(() -> new NullSafe<>(map.get(key)));
    }
}
