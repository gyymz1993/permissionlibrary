/*
 * Copyright © Yan Zhenjie
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
package com.utils.permission.runtime.setting;

import com.utils.permission.PermissionActivity;
import com.utils.permission.Setting;
import com.utils.permission.source.Source;

/**
 * <p>Setting executor.</p>
 * Created by Yan Zhenjie on 2016/12/28.
 */
public class RuntimeSetting implements Setting, PermissionActivity.RequestListener {

    private Source mSource;
    private Setting.Action mComeback;

    public RuntimeSetting(Source source) {
        this.mSource = source;
    }

    @Override
    public void execute() {
        new SettingPage(mSource).start(-1);
    }

    @Override
    public void execute(int requestCode) {
        new SettingPage(mSource).start(requestCode);
    }

    @Override
    public void cancel() {
    }

    @Override
    public Setting onComeback(Setting.Action comeback) {
        this.mComeback = comeback;
        return this;
    }

    @Override
    public void start() {
        PermissionActivity.permissionSetting(mSource.getContext(), this);
    }

    @Override
    public void onRequestCallback() {
        if (mComeback != null) {
            mComeback.onAction();
        }
    }
}