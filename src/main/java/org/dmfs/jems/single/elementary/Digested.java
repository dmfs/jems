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

package org.dmfs.jems.single.elementary;

import org.dmfs.iterables.decorators.Mapped;
import org.dmfs.iterables.elementary.Seq;
import org.dmfs.iterators.Function;
import org.dmfs.jems.function.elementary.SingleFunction;
import org.dmfs.jems.single.Single;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.Locale;


/**
 * A {@link Single} of a byte array which represents the digested value of the given input data.
 *
 * @author Marten Gajda
 */
public final class Digested implements Single<byte[]>
{
    private final Single<MessageDigest> mDigest;
    private final Iterable<Single<byte[]>> mParts;


    public Digested(Single<MessageDigest> digest, byte[]... parts)
    {
        this(digest, new Mapped<>(new Seq<>(parts), new SingleFunction<byte[]>()));
    }


    public Digested(Single<MessageDigest> digest, CharSequence... parts)
    {
        this(digest, "UTF-8", parts);
    }


    public Digested(Single<MessageDigest> digest, final String encoding, CharSequence... parts)
    {
        this(digest, new Mapped<>(new Seq<>(parts), new Function<CharSequence, Single<byte[]>>()
        {
            @Override
            public Single<byte[]> apply(CharSequence bytes)
            {
                try
                {
                    return new ValueSingle<>(bytes.toString().getBytes(encoding));
                }
                catch (UnsupportedEncodingException e)
                {
                    throw new RuntimeException(String.format(Locale.ENGLISH, "%s encoding not supported by runtime", encoding));
                }
            }
        }));
    }


    @SafeVarargs
    public Digested(Single<MessageDigest> digest, Single<byte[]>... parts)
    {
        this(digest, new Seq<>(parts));
    }


    public Digested(Single<MessageDigest> digest, Iterable<Single<byte[]>> parts)
    {
        mDigest = digest;
        mParts = parts;
    }


    @Override
    public byte[] value()
    {
        MessageDigest digest = mDigest.value();
        for (Single<byte[]> part : mParts)
        {
            digest.update(part.value());
        }
        return digest.digest();
    }

}
