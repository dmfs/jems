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

package org.dmfs.jems.text.composite;

import org.dmfs.jems.function.BiFunction;
import org.dmfs.jems.single.elementary.Reduced;
import org.dmfs.jems.text.Text;

import java.util.LinkedList;
import java.util.List;


/**
 * @author Marten Gajda
 */
public final class Joined implements Text
{
    private final Text mSeparator;
    private final Iterable<Text> mSegments;


    public Joined(Text separator, Iterable<Text> segments)
    {
        mSeparator = separator;
        mSegments = segments;
    }


    @Override
    public char[] value()
    {
        char[] separatorChars = mSeparator.value();
        List<char[]> segments = new Reduced<>(new LinkedList<char[]>(), new BiFunction<LinkedList<char[]>, Text, LinkedList<char[]>>()
        {

            @Override
            public LinkedList<char[]> value(LinkedList<char[]> chars, Text text)
            {
                chars.add(text.value());
                return chars;
            }
        }, mSegments).value();

        int size = new Reduced<>(0, new BiFunction<Integer, char[], Integer>()
        {
            @Override
            public Integer value(Integer integer, char[] chars)
            {
                return integer + chars.length;
            }
        }, segments).value();

        char[] result = new char[size + separatorChars.length * Math.max(0, segments.size() - 1)];
        int pos = 0;
        boolean first = true;
        for (char[] segment : segments)
        {
            if (first)
            {
                first = false;
            }
            else
            {
                System.arraycopy(separatorChars, 0, result, pos, separatorChars.length);
                pos += separatorChars.length;
            }
            System.arraycopy(segment, 0, result, pos, segment.length);
            pos += segment.length;
        }
        return result;
    }
}
