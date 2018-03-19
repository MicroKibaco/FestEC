package com.asiainfo.festec.generators;


import com.asiainfo.latte_annotations.annotations.EntryGenerator;
import com.asiainfo.latte_core.wechat.templates.WXEntryTemplate;

@EntryGenerator(
        packageName = "com.asiainfo.festec",
        entryTemplate = WXEntryTemplate.class
)
public interface WeChatEntry {
}
