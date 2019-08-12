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
import org.dmfs.jems.iterable.elementary.Seq;
import org.dmfs.iterators.Function;
import org.dmfs.jems.function.BiFunction;
import org.dmfs.jems.function.elementary.SingleFunction;
import org.dmfs.jems.messagedigest.MessageDigestFactory;
import org.dmfs.jems.single.Single;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.Locale;


/**
 * A {@link Single} of a byte array which represents the digested value of the given input data.
 *
 * @author Marten Gajda
 */
public final class Digest implements Single<byte[]>
{
    private final MessageDigestFactory mMessageDigestFactory;
    private final Iterable<Single<byte[]>> mParts;


    public Digest(MessageDigestFactory messageDigestFactory, byte[]... parts)
    {
        this(messageDigestFactory, new Mapped<>(new Seq<>(parts), new SingleFunction<byte[]>()));
    }


    public Digest(MessageDigestFactory messageDigestFactory, CharSequence... parts)
    {
        this(messageDigestFactory, "UTF-8", parts);
    }


    public Digest(MessageDigestFactory messageDigestFactory, final String encoding, CharSequence... parts)
    {
        this(messageDigestFactory, new Mapped<>(new Seq<>(parts), new Function<CharSequence, Single<byte[]>>()
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
                    throw new RuntimeException(String.format(Locale.ENGLISH, "%s encoding not supported by runtime", encoding), e);
                }
            }
        }));
    }


    @SafeVarargs
    public Digest(MessageDigestFactory messageDigestFactory, Single<byte[]>... parts)
    {
        this(messageDigestFactory, new Seq<>(parts));
    }


    public Digest(MessageDigestFactory messageDigestFactory, Iterable<Single<byte[]>> parts)
    {
        mMessageDigestFactory = messageDigestFactory;
        mParts = parts;
    }


    @Override
    public byte[] value()
    {
        return new Reduced<Single<byte[]>, MessageDigest>(mMessageDigestFactory::newInstance, new DigestFunction(), mParts).value().digest();
    }


    private final static class DigestFunction implements BiFunction<MessageDigest, Single<byte[]>, MessageDigest>
    {
        @Override
        public MessageDigest value(MessageDigest messageDigest, Single<byte[]> bytes)
        {
            messageDigest.update(bytes.value());
            return messageDigest;
        }
    }
}
