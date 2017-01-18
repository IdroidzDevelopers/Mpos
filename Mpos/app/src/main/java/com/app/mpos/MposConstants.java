package com.app.mpos;

/**
 * Created by aarokiax on 12/28/2016.
 */

public class MposConstants {

    //table uris
    private static final String AUTHORITY = "com.app.mpos.MposProvider";
    private static final String CONTENT_URI = "content://" + AUTHORITY;
    public static final String CONTENT_URI_TABLE_MASTER = CONTENT_URI + "/" + "master";
    public static final String CONTENT_URI_COMMUNICATION = CONTENT_URI + "/" + "Table_";

    public static final String TABLE_MASTER_PACKAGE = "package";
    public static final String MPOS_PACKAGE = "com.app.mpos";

}
