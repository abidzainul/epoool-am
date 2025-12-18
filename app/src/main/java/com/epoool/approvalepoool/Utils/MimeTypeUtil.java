package com.epoool.approvalepoool.Utils;

import java.util.HashMap;

/* loaded from: classes.dex */
public class MimeTypeUtil {
    static HashMap<String, String> types = new HashMap<>();

    private static void init() {
        types.put("jpeg", "image/jpeg");
        types.put("jpg", "image/jpeg");
        types.put("png", "image/png");
        types.put("gif", "image/gif");
        types.put("pdf", "application/pdf");
    }

    public static String getType(String str) {
        if (types.size() == 0) {
            init();
        }
        return types.get(str);
    }
}
