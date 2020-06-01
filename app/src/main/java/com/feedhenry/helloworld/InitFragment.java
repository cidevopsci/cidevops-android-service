/**
 * Copyright 2015 Red Hat, Inc., and individual contributors
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
package com.feedhenry.helloworld;

import android.os.*;
import android.support.v4.app.*;
import android.util.*;
import android.view.*;
import android.widget.*;

import com.feedhenry.sdk.*;

public class InitFragment extends Fragment {

    private static final String TAG = InitFragment.class.getName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.init_fragment, null);

        FH.init(getActivity(), new FHActCallback() {
            @Override
            public void success(FHResponse fhResponse) {
                Log.d(TAG, "init - success");
                MainActivity activity = (MainActivity) getActivity();
                activity.showHelloScreen();
            }

            @Override
            public void fail(FHResponse fhResponse) {
                Log.d(TAG, "init - fail");
                Log.e(TAG, fhResponse.getErrorMessage(), fhResponse.getError());

                MainActivity activity = (MainActivity) getActivity();

                Toast.makeText(activity, fhResponse.getErrorMessage(), Toast.LENGTH_LONG).show();
                activity.finish();
            }
        });

        return view;
    }

}
