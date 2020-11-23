package com.xylink.wechat.util;


import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLEncoder;
import java.security.SignatureException;
import java.util.*;

/**
 * @author JinWei
 * @ClassName SignatureSampleLocal
 * @description
 * @date 2019-12-26
 **/
public class SignatureSampleLocal {

    private String requestUriPrefix = "/api/rest/external/v1/";
    private String wssPrefix = "wss://sdk.xylink.com/external/";

    public SignatureSampleLocal() {
    }


    private String computeStringToSign(String requestPath, Map<String, String> reqParams, String reqJsonEntity,
                                       String reqMethod, String host) throws Exception {
        String prefix = this.getPrefix(requestPath, host);
        StringBuffer strToSign = new StringBuffer(reqMethod);
        strToSign.append("\n");
        strToSign.append(requestPath.substring(prefix.length()));
        strToSign.append("\n");
        ArrayList params = new ArrayList(reqParams.keySet());
        Collections.sort(params);
        Iterator var8 = params.iterator();

        String ret;
        while(var8.hasNext()) {
            ret = (String)var8.next();
            strToSign.append(ret);
            strToSign.append("=");
            strToSign.append(reqParams.get(ret));
            strToSign.append("&");
        }

        strToSign.deleteCharAt(strToSign.length() - 1);
        strToSign.append("\n");
        byte[] reqEntity = new byte[0];
        if (StringUtils.isNotBlank(reqJsonEntity)) {
            reqEntity = reqJsonEntity.getBytes("utf-8");
        }

        byte[] data;
        if (reqEntity.length == 0) {
            data = DigestUtils.sha256("");
            strToSign.append(Base64.encodeBase64String(data));
        } else {
            if (reqEntity.length <= 100) {
                data = reqEntity;
            } else {
                data = Arrays.copyOf(reqEntity, 100);
            }

            byte[] entity = DigestUtils.sha256(data);
            strToSign.append(Base64.encodeBase64String(entity));
        }

        ret = strToSign.toString();
        return ret;
    }

    private String getPrefix(String reqPath, String host) {
        String prefix = host + this.requestUriPrefix;
        if (reqPath.startsWith("wss://")) {
            prefix = this.wssPrefix;
        }

        return prefix;
    }

    private void printArray(byte[] data) {
        StringBuffer sb = new StringBuffer();
        byte[] var3 = data;
        int var4 = data.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            byte d = var3[var5];
            sb.append(d);
            sb.append(",");
        }

        System.out.println(sb.toString());
    }

    private String calculateHMAC(String data, String key) throws SignatureException {
        try {
            SecretKeySpec e = new SecretKeySpec(key.getBytes("UTF8"), "HmacSHA256");
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(e);
            byte[] rawHmac = mac.doFinal(data.getBytes("UTF8"));
            String result = Base64.encodeBase64String(rawHmac);
            return result;
        } catch (Exception var7) {
            throw new SignatureException("Failed to generate HMAC : " + var7.getMessage());
        }
    }

    public String computeSignature(String jsonEntity, String method, String token, String reqPath, String host) {
        try {
            Map<String, String> reqParams = new HashMap();
            int idx = reqPath.indexOf("?");
            String[] params = reqPath.substring(idx + 1).split("&");
            String[] var8 = params;
            int var9 = params.length;

            for(int var10 = 0; var10 < var9; ++var10) {
                String param = var8[var10];
                String[] pair = param.split("=");
                if (pair.length == 1) {
                    reqParams.put(pair[0], "");
                } else {
                    reqParams.put(pair[0], pair[1]);
                }
            }

            reqPath = reqPath.substring(0, idx);
            String strToSign = this.computeStringToSign(reqPath, reqParams, jsonEntity, method, host);
            String mySignature = this.calculateHMAC(strToSign, token);
            mySignature = mySignature.replace(" ", "+");
            return URLEncoder.encode(mySignature, "utf-8");
        } catch (Exception var13) {
            return null;
        }
    }

}
