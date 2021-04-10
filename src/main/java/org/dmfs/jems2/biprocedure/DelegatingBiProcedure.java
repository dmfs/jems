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

package org.dmfs.jems2.biprocedure;

import org.dmfs.jems2.BiProcedure;


/**
 * An abstract {@link BiProcedure} which delegates to another {@link BiProcedure}.
 */
public abstract class DelegatingBiProcedure<Argument1, Argument2> implements BiProcedure<Argument1, Argument2>
{
    private final BiProcedure<Argument1, Argument2> mDelegate;


    public DelegatingBiProcedure(BiProcedure<Argument1, Argument2> delegate)
    {
        mDelegate = delegate;
    }


    @Override
    public final void process(Argument1 arg1, Argument2 arg2)
    {
        mDelegate.process(arg1, arg2);
    }
}
