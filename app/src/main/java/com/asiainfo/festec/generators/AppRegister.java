package com.asiainfo.festec.generators;

import com.asiainfo.latte_annotations.annotations.AppRegisterGenerator;
import com.asiainfo.latte_core.wechat.templates.AppRegisterTemplate;

@AppRegisterGenerator(
        packageName = "com.asiainfo.festec",
        registerTemplate = AppRegisterTemplate.class
)
// TODO: WeChatEntry.java
public interface AppRegister {
}
