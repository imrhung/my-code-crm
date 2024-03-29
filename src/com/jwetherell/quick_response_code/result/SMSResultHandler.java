/*
 * Copyright (C) 2008 ZXing authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jwetherell.quick_response_code.result;

import com.iseed.crm.android.R;

import com.google.zxing.client.result.ParsedResult;
import com.google.zxing.client.result.SMSParsedResult;

import android.app.Activity;
import android.telephony.PhoneNumberUtils;

/**
 * Handles SMS addresses, offering a choice of composing a new SMS or MMS
 * message.
 * 
 * @author dswitkin@google.com (Daniel Switkin)
 */
public final class SMSResultHandler extends ResultHandler {

    public SMSResultHandler(Activity activity, ParsedResult result) {
        super(activity, result);
    }

    @Override
    public CharSequence getDisplayContents() {
        SMSParsedResult smsResult = (SMSParsedResult) getResult();
        StringBuilder contents = new StringBuilder(50);
        String[] rawNumbers = smsResult.getNumbers();
        String[] formattedNumbers = new String[rawNumbers.length];
        for (int i = 0; i < rawNumbers.length; i++) {
            formattedNumbers[i] = PhoneNumberUtils.formatNumber(rawNumbers[i]);
        }
        ParsedResult.maybeAppend(formattedNumbers, contents);
        ParsedResult.maybeAppend(smsResult.getSubject(), contents);
        ParsedResult.maybeAppend(smsResult.getBody(), contents);
        return contents.toString();
    }

    @Override
    public int getDisplayTitle() {
        return R.string.result_sms;
    }
}
