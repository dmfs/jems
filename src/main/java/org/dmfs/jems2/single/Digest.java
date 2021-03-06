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

package org.dmfs.jems2.single;

import org.dmfs.jems2.BiFunction;
import org.dmfs.jems2.Fragile;
import org.dmfs.jems2.Generator;
import org.dmfs.jems2.Single;
import org.dmfs.jems2.iterable.Mapped;
import org.dmfs.jems2.iterable.Seq;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.Locale;

import static org.dmfs.jems2.function.SingleFunction.toSingle;


/**
 * A {@link Single} of a byte array which represents the digested value of the given input data.
 */
public final class Digest implements Single<byte[]>
{
    private final Generator<? extends MessageDigest> mMessageDigestGenerator;
    private final Iterable<? extends Fragile<byte[], ? extends RuntimeException>> mParts;


    public Digest(
        Generator<? extends MessageDigest> messageDigestGenerator,
        byte[]... parts)
    {
        this(messageDigestGenerator, new Mapped<>(toSingle(), new Seq<>(parts)));
    }


    public Digest(
        Generator<? extends MessageDigest> messageDigestGenerator,
        CharSequence... parts)
    {
        this(messageDigestGenerator, "UTF-8", parts);
    }


    public Digest(
        Generator<? extends MessageDigest> messageDigestGenerator,
        final String encoding,
        CharSequence... parts)
    {
        this(
            messageDigestGenerator,
            new Mapped<>(
                bytes -> {
                    try
                    {
                        return new Just<>(bytes.toString().getBytes(encoding));
                    }
                    catch (UnsupportedEncodingException e)
                    {
                        throw new RuntimeException(String.format(Locale.ENGLISH, "%s encoding not supported by runtime", encoding), e);
                    }
                },
                new Seq<>(parts)));
    }


    @SafeVarargs
    public Digest(
        Generator<? extends MessageDigest> messageDigestGenerator,
        Fragile<byte[], ? extends RuntimeException>... parts)
    {
        this(messageDigestGenerator, new Seq<>(parts));
    }


    public Digest(
        Generator<? extends MessageDigest> messageDigestGenerator,
        Iterable<? extends Fragile<byte[], ? extends RuntimeException>> parts)
    {
        mMessageDigestGenerator = messageDigestGenerator;
        mParts = parts;
    }


    @Override
    public byte[] value()
    {
        return new Reduced<>(mMessageDigestGenerator, DIGEST_ACCUMULATOR_FUNCTION, mParts).value().digest();
    }


    private static final BiFunction<MessageDigest, Fragile<byte[], ? extends RuntimeException>, MessageDigest> DIGEST_ACCUMULATOR_FUNCTION =
        (messageDigest, bytes) -> {
            messageDigest.update(bytes.value());
            return messageDigest;
        };
}
