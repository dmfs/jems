/*
 * Copyright 2017 dmfs GmbH
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

package org.dmfs.testutils.tools;

/**
 * Base class for test value object classes.
 *
 * @author Gabor Keszthelyi
 */
public abstract class AbstractValueObject
{
    private final int mValue;


    protected AbstractValueObject(int value)
    {
        mValue = value;
    }


    @Override
    public final boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }

        AbstractValueObject that = (AbstractValueObject) o;

        return mValue == that.mValue;
    }


    @Override
    public final int hashCode()
    {
        return mValue;
    }


    @Override
    public final String toString()
    {
        return getClass().getSimpleName() + "{" + mValue + '}';
    }
}