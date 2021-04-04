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

package org.dmfs.jems2.generator;

import org.junit.Test;

import java.security.MessageDigest;

import static org.dmfs.jems2.hamcrest.matchers.LambdaMatcher.having;
import static org.dmfs.jems2.hamcrest.matchers.generatable.GeneratableMatcher.startsWith;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


/**
 * Unit test for {@link Sha256}.
 */
public class Sha256Test
{
    @Test
    public void test()
    {
        assertThat(
            Sha256::new,
            startsWith(
                having(MessageDigest::getAlgorithm, is("SHA-256")),
                having(MessageDigest::getAlgorithm, is("SHA-256")),
                having(MessageDigest::getAlgorithm, is("SHA-256")),
                having(MessageDigest::getAlgorithm, is("SHA-256")),
                having(MessageDigest::getAlgorithm, is("SHA-256"))));
    }

}