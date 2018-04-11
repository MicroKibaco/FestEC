package com.asiainfo.festec.generators;

import com.asiainfo.latte_annotations.annotations.PayEntryGenerator;
import com.asiainfo.latte_core.wechat.templates.WXPayEntryTemplate;

@PayEntryGenerator(
        packageName = "com.asiainfo.festec",
        payEntryTemplate = WXPayEntryTemplate.class
)
public interface WeChatPayEntry {
}
