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

package org.dmfs.iterators.utils;

import org.dmfs.iterators.ArrayIterator;
import org.dmfs.iterators.EmptyIterator;
import org.dmfs.iterators.SingletonIterator;
import org.dmfs.iterators.decorators.Filtered;
import org.dmfs.iterators.decorators.Serialized;
import org.dmfs.iterators.filters.NonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


/**
 * An open addressing {@link Set} implementation. While insert performance is mostly comparable to the default {@link
 * HashSet} implementation, this set aims to be much more memory efficient.
 * <p>
 * This class is not thread safe.
 *
 * @param <E>
 *         The type of the elements in the set.
 *
 * @author Marten Gajda
 */
public final class SlimSet<E> implements Set<E>, Cloneable
{
    /**
     * Indicates that this set contains a null value. Since null values are not hashable, we handle them separately.
     */
    private boolean mContainsNull;

    /**
     * The number of elements in this set.
     */
    private int mSize;

    /**
     * The backing array that contains all the values.
     */
    private Object[] mArray;

    /**
     * The calculated hash value. The value is updated on-the-fly whenever an element is added or removed.
     */
    private int mHashCode;

    /**
     * The configured load factor. The backing array is resized whenever <code>mSize/mArray.length > mLoadFactor</code>
     */
    private final float mLoadFactor;


    /**
     * Default constructor.
     */
    public SlimSet()
    {
        this(16);
    }


    /**
     * Constructor to create a set that can take at least the given number of elements before it needs to be rehashed.
     *
     * @param size
     *         The number of elements that the set can hold before it needs to be resized and rehashed.
     */
    public SlimSet(final int size)
    {
        this(size, 0.75f);
    }


    /**
     * Constructor to create a set that can take at least the given number of elements before it needs to be rehashed.
     * This constructor also allows to specify the load factor of the set.
     *
     * @param size
     *         The number of elements that the set can hold before it needs to be resized and rehashed.
     * @param foadFactor
     *         The load factor if this set.
     */
    public SlimSet(final int size, final float foadFactor)
    {
        this(new Object[(int) Math.max(size / foadFactor, 16)], 0, false, 0, foadFactor);
    }


    /**
     * Creates a set from the elements of another {@link Collection}.
     *
     * @param other
     *         The other collection that contains the initial values of this set.
     */
    public SlimSet(final Collection<E> other)
    {
        this((other.size() * 3) / 2 /* leave some more room to allow adding elements without having to rehash immediately */);
        addAll(other);
    }


    /**
     * Creates a set from the values in an array.
     *
     * @param values
     *         An array holding the initial values of the set.
     */
    public SlimSet(final E[] values)
    {
        this((values.length * 3) / 2/* leave some more room to allow adding elements without having to rehash immediately */);
        for (E value : values)
        {
            add(value);
        }
    }


    private SlimSet(final Object[] objects, final int size, final boolean containsNull, final int hash, final float lf)
    {
        if (lf <= 0 || lf >= 1)
        {
            throw new IllegalArgumentException(String.format("The load factor %f is not a number between 0 and 1", lf));
        }

        mArray = objects;
        mSize = size;
        mContainsNull = containsNull;
        mHashCode = hash;
        mLoadFactor = lf;
    }


    @Override
    public boolean contains(final Object object)
    {
        if (object == null)
        {
            return mContainsNull;
        }

        // the element is contained if the element at the predicted position is not null
        return mArray[predictedPosition(mArray, object, object.hashCode())] != null;
    }


    @Override
    public boolean add(final E object)
    {
        if (object == null)
        {
            if (mContainsNull)
            {
                // null is already in the set
                return false;
            }

            // "add" null value
            mSize++;
            mContainsNull = true;
            return true;
        }

        if (mArray.length * mLoadFactor <= mSize)
        {
            // need a resize
            mArray = rehash(mArray, mArray.length * 2 /* always double the array */);
        }

        int hash = object.hashCode();
        int i = predictedPosition(mArray, object, hash);

        if (mArray[i] != null)
        {
            // the predicted position is already taken, which means the element is already in the set
            return false;
        }

        // update the set
        mArray[i] = object;
        mSize++;
        mHashCode ^= hash;
        return true;
    }


    @Override
    public boolean isEmpty()
    {
        return mSize == 0;
    }


    @Override
    public int size()
    {
        return mSize;
    }


    /**
     * Rebuild the backing array with a different size.
     *
     * @param values
     *         The values to put into the new array.
     * @param newSize
     *         The size of the new array.
     *
     * @return The new backing array.
     */
    private static Object[] rehash(final Object[] values, final int newSize)
    {
        Object[] newArray = new Object[newSize];

        for (Object value : values)
        {
            if (value == null)
            {
                continue;
            }

            newArray[predictedPosition(newArray, value, value.hashCode())] = value;
        }

        return newArray;
    }


    /**
     * Calculates the position of the given element in the given array. The value at the calculated position will be
     * <code>null</code> if the element doesn't exist in the array, otherwise the position will point to the element.
     *
     * @param array
     * @param object
     *
     * @return
     */
    private static int predictedPosition(final Object[] array, final Object object, final int hash)
    {
        int arraySize = array.length;
        int i = Math.abs(hash) % arraySize;

        Object element = array[i];
        while (element != null) // the load factor guarantees that there is always at least one free slot
        {
            if (element.equals(object))
            {
                // already in the array
                return i;
            }
            // the position is taken, try the next slot
            i = (i + 1) % arraySize;
            element = array[i];
        }
        return i;
    }


    @Override
    public boolean addAll(final Collection<? extends E> c)
    {
        if (c instanceof Set && c.size() * 4 > size() * 3)
        {
            // prepare to take at least c.size() additional unique elements
            mArray = rehash(mArray, (int) (c.size() / mLoadFactor) + size());
        }

        boolean result = false;
        for (E element : c)
        {
            result |= add(element);
        }
        return result;
    }


    @Override
    public void clear()
    {
        mArray = new Object[mArray.length];
        mSize = 0;
        mContainsNull = false;
        mHashCode = 0;
    }


    @Override
    public boolean containsAll(final Collection<?> c)
    {
        for (Object element : c)
        {
            if (!contains(element))
            {
                return false;
            }
        }
        return true;
    }


    @SuppressWarnings("unchecked")
    @Override
    public Iterator<E> iterator()
    {
        if (mSize == 0)
        {
            return EmptyIterator.instance();
        }

        if (mSize == 1 && mContainsNull)
        {
            return new SingletonIterator<E>(null);
        }

        Iterator<E> result = new Filtered<E>(new ArrayIterator<E>((E[]) mArray.clone()),
                NonNull.<E>instance());
        if (mContainsNull)
        {
            return new Serialized<>(result, new SingletonIterator<E>(null));
        }
        return result;
    }


    @Override
    public boolean remove(Object object)
    {
        if (object == null)
        {
            if (!mContainsNull)
            {
                return false;
            }

            mSize--;
            mContainsNull = true;
            return true;
        }

        int hash = object.hashCode();

        int i = predictedPosition(mArray, object, hash);

        if (mArray[i] == null)
        {
            return false;
        }

        mHashCode ^= hash;
        mArray[i] = null;
        mSize--;
        return true;
    }


    @Override
    public boolean removeAll(Collection<?> c)
    {
        boolean result = false;
        for (Object element : c)
        {
            result |= remove(element);
        }
        return result;
    }


    @Override
    public boolean retainAll(Collection<?> c)
    {
        int originalCount = mSize;
        if (mContainsNull && !c.contains(null))
        {
            mContainsNull = false;
            --mSize;
        }

        for (int i = 0, count = mArray.length; i < count; ++i)
        {
            Object element = mArray[i];
            if (element == null)
            {
                continue;
            }
            if (!c.contains(element))
            {
                // element is not in c, remove it from the set
                mHashCode ^= element.hashCode();
                mArray[i] = null;
                --mSize;
            }
        }
        return mSize != originalCount;
    }


    @Override
    public Object[] toArray()
    {
        return mArray.clone();
    }


    @Override
    public <T> T[] toArray(T[] a)
    {
        System.arraycopy(mArray, 0, a, 0, Math.min(a.length, mArray.length));
        return a;
    }


    @Override
    protected SlimSet<E> clone()
    {
        return new SlimSet<E>(mArray.clone(), mSize, mContainsNull, mHashCode, mLoadFactor);
    }


    @Override
    public int hashCode()
    {
        return mContainsNull ? mHashCode ^ 7654321 : mHashCode;
    }


    @Override
    public boolean equals(Object obj)
    {
        if (obj == this)
        {
            return true;
        }

        if (!(obj instanceof Set))
        {
            return false;
        }

        if (obj instanceof SlimSet && obj.hashCode() != hashCode())
        {
            // we can rule out SlimSets with different hash codes
            return false;
        }

        // otherwise we assume that two sets are equal if they contain the same entries.

        Set<?> other = (Set<?>) obj;

        return other.size() == mSize && other.containsAll(this);
    }
}
