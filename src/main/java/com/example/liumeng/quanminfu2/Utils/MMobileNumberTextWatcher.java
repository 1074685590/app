package com.example.liumeng.quanminfu2.Utils;

import android.text.Editable;
import android.text.TextWatcher;

/**
 * 手机号吗控制
 */
class MMobileNumberTextWatcher implements TextWatcher {

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			if (s == null || s.length() == 0)
				return;
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < s.length(); i++) {
				if (i != 3 && i != 8 && s.charAt(i) == ' ') {
					continue;
				} else {
					sb.append(s.charAt(i));
					if ((sb.length() == 4 || sb.length() == 9)
							&& sb.charAt(sb.length() - 1) != ' ') {
						sb.insert(sb.length() - 1, ' ');
					}
				}
			}
			if (!sb.toString().equals(s.toString())) {
				int index = start + 1;
				if (sb.charAt(start) == ' ') {
					if (before == 0) {
						index++;
					} else {
						index--;
					}
				} else {
					if (before == 1) {
						index--;
					}
				}
//				edt_mobileNumber.setText(sb.toString());
//				edt_mobileNumber.setSelection(index);
			}
//			setNextStepEnable();
		}

		@Override
		public void afterTextChanged(Editable s) {
		}

	}