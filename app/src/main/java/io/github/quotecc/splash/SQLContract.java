package io.github.quotecc.splash;

/**
 * Created by cCorliss on 3/2/17.
 */

public final class SQLContract {
    private SQLContract(){}

    public static class FeedEntry{
        public static final String TABLE_NAME = "notes";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_CONTENT = "content";
    }


}
