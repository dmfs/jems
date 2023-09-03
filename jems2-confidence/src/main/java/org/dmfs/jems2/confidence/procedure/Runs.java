/*
 * Copyright 2022 dmfs GmbH
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package org.dmfs.jems2.confidence.procedure;

import org.dmfs.jems2.Function;
import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.confidence.Assessment;
import org.saynotobugs.confidence.Description;
import org.saynotobugs.confidence.Quality;
import org.saynotobugs.confidence.assessment.Fail;
import org.saynotobugs.confidence.assessment.Pass;
import org.saynotobugs.confidence.description.LiteralDescription;
import org.saynotobugs.confidence.description.Spaced;
import org.saynotobugs.confidence.description.Text;
import org.saynotobugs.confidence.description.Value;
import org.saynotobugs.confidence.quality.object.Throwing;


@StaticFactories(value = "Jems2", packageName = "org.dmfs.jems2.confidence")
public final class Runs implements Quality<Throwing.Breakable>
{
    private final Description mPassDescription;
    private final Function<? super Throwable, ? extends Description> mFailDescription;

    public Runs()
    {
        this(LiteralDescription.EMPTY);
    }

    public Runs(Description passDescription)
    {
        this(passDescription, throwable -> new Spaced(new Text("threw"), new Value(throwable)));
    }


    public Runs(Description description, Function<? super Throwable, ? extends Description> failDescription)
    {
        mPassDescription = description;
        mFailDescription = failDescription;
    }


    @Override
    public Assessment assessmentOf(Throwing.Breakable candidate)
    {
        try
        {
            candidate.run();
            return new Pass();
        }
        catch (Throwable e)
        {
            return new Fail(mFailDescription.value(e));
        }
    }


    @Override
    public Description description()
    {
        return mPassDescription;
    }
}
