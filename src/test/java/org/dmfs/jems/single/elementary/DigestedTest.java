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

import org.dmfs.iterables.elementary.Seq;
import org.dmfs.jems.single.Single;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;


public class DigestedTest
{
    @Test
    public void value() throws Exception
    {
        assertThat(new Digested(new Md5(), new byte[0]).value(), is(new Md5().value().digest()));
        assertThat(new Digested(new Md5(), new byte[0], new byte[0]).value(), is(new Md5().value().digest()));
        assertThat(new Digested(new Md5(), new byte[0], new byte[0], new byte[0]).value(), is(new Md5().value().digest()));
        assertThat(new Digested(new Md5(), new byte[] { 1, 2, 3 }).value(), is(new Md5().value().digest(new byte[] { 1, 2, 3 })));
        assertThat(new Digested(new Md5(), new byte[] { 1, 2, 3 }, new byte[] { 4, 5, 6 }).value(),
                is(new Md5().value().digest(new byte[] { 1, 2, 3, 4, 5, 6 })));
        assertThat(new Digested(new Md5(), new byte[] { 1, 2, 3 }, new byte[] { 4, 5, 6 }, new byte[] { 7, 8, 9 }).value(),
                is(new Md5().value().digest(new byte[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 })));

        assertThat(new Digested(new Md5(), (CharSequence) "").value(), is(new Md5().value().digest()));
        assertThat(new Digested(new Md5(), (CharSequence) "", "").value(), is(new Md5().value().digest()));
        assertThat(new Digested(new Md5(), (CharSequence) "", "", "").value(), is(new Md5().value().digest()));
        assertThat(new Digested(new Md5(), (CharSequence) "1äöü").value(), is(new Md5().value().digest("1äöü".getBytes("UTF-8"))));
        assertThat(new Digested(new Md5(), (CharSequence) "1äöü", "456").value(), is(new Md5().value().digest("1äöü456".getBytes("UTF-8"))));
        assertThat(new Digested(new Md5(), (CharSequence) "1äöü", "456", "789").value(), is(new Md5().value().digest("1äöü456789".getBytes("UTF-8"))));

        assertThat(new Digested(new Md5(), "latin1", (CharSequence) "").value(), is(new Md5().value().digest()));
        assertThat(new Digested(new Md5(), "latin1", (CharSequence) "1äöü").value(), is(new Md5().value().digest("1äöü".getBytes("latin1"))));
        assertThat(new Digested(new Md5(), "latin1", (CharSequence) "1äöü", "456").value(), is(new Md5().value().digest("1äöü456".getBytes("latin1"))));
        assertThat(new Digested(new Md5(), "latin1", (CharSequence) "1äöü", "456", "789").value(),
                is(new Md5().value().digest("1äöü456789".getBytes("latin1"))));

        assertThat(new Digested(new Md5(), new ValueSingle<>(new byte[0])).value(), is(new Md5().value().digest()));
        assertThat(new Digested(new Md5(), new ValueSingle<>(new byte[0]), new ValueSingle<>(new byte[0])).value(), is(new Md5().value().digest()));
        assertThat(new Digested(new Md5(), new ValueSingle<>(new byte[0]), new ValueSingle<>(new byte[0]), new ValueSingle<>(new byte[0])).value(),
                is(new Md5().value().digest()));

        assertThat(new Digested(new Md5(), new ValueSingle<>(new byte[] { 1, 2, 3 })).value(), is(new Md5().value().digest(new byte[] { 1, 2, 3 })));
        assertThat(new Digested(new Md5(), new ValueSingle<>(new byte[] { 1, 2, 3 }), new ValueSingle<>(new byte[] { 4, 5, 6 })).value(),
                is(new Md5().value().digest(new byte[] { 1, 2, 3, 4, 5, 6 })));
        assertThat(new Digested(new Md5(), new ValueSingle<>(new byte[] { 1, 2, 3 }), new ValueSingle<>(new byte[] { 4, 5, 6 }),
                new ValueSingle<>(new byte[] { 7, 8, 9 })).value(), is(new Md5().value().digest(new byte[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 })));

        assertThat(new Digested(new Md5(), new Seq<Single<byte[]>>(new ValueSingle<>(new byte[0]))).value(), is(new Md5().value().digest()));
        assertThat(new Digested(new Md5(), new Seq<Single<byte[]>>(new ValueSingle<>(new byte[0]), new ValueSingle<>(new byte[0]))).value(),
                is(new Md5().value().digest()));
        assertThat(
                new Digested(new Md5(), new Seq<Single<byte[]>>(new ValueSingle<>(new byte[0]), new ValueSingle<>(new byte[0]), new ValueSingle<>(new byte[0])))
                        .value(), is(new Md5().value().digest()));

        assertThat(new Digested(new Md5(), new Seq<Single<byte[]>>(new ValueSingle<>(new byte[] { 1, 2, 3 }))).value(),
                is(new Md5().value().digest(new byte[] { 1, 2, 3 })));
        assertThat(
                new Digested(new Md5(), new Seq<Single<byte[]>>(new ValueSingle<>(new byte[] { 1, 2, 3 }), new ValueSingle<>(new byte[] { 4, 5, 6 }))).value(),
                is(new Md5().value().digest(new byte[] { 1, 2, 3, 4, 5, 6 })));
        assertThat(new Digested(new Md5(), new Seq<Single<byte[]>>(new ValueSingle<>(new byte[] { 1, 2, 3 }), new ValueSingle<>(new byte[] { 4, 5, 6 }),
                new ValueSingle<>(new byte[] { 7, 8, 9 }))).value(), is(new Md5().value().digest(new byte[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 })));
    }


    @Test
    public void testIllegalEncoding()
    {
        Single<byte[]> digested = new Digested(new Md5(), "bogus-fake-invalid", (CharSequence) "");

        try
        {
            digested.value();
            fail("Did not throw");
        }
        catch (RuntimeException e)
        {
            // pass
        }
    }
}