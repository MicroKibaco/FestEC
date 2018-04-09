package com.asiainfo.latte_ec.pay;


import android.text.TextUtils;

public class PayResult {
    private String resultStatus;
    private String result;
    private String memo;

    public PayResult(String rawResult) {
        if (TextUtils.isEmpty(rawResult)) {
            return;
        }
        String[] resultParams = rawResult.split(";");
        for (String resultParam : resultParams) {

            if (resultParam.startsWith("resultStatus")) {
                resultStatus = gatValue(resultParam, "resultStatus");
            }
            if (resultParam.startsWith("result")) {
                result = gatValue(resultParam, "result");
            }
            if (resultParam.startsWith("memo")) {
                memo = gatValue(resultParam, "memo");
            }
        }
    }

    private String gatValue(String key, String content) {
        String prefix = key + "={";
        return content.substring(content.indexOf(prefix) + prefix.length(),
                content.lastIndexOf("}"));
    }

    public String getResultStatus() {
        return resultStatus;
    }

    public void setResultStatus(String resultStatus) {
        this.resultStatus = resultStatus;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    @Override
    public String toString() {
        return "PayResult{" +
                "resultStatus='" + resultStatus + '\'' +
                ", result='" + result + '\'' +
                ", memo='" + memo + '\'' +
                '}';
    }
}
