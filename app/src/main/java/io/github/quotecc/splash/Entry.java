package io.github.quotecc.splash;

/**
 * Created by cCorliss on 3/2/17.
 */

public class Entry {
    private String title;
    private String content;

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getContent(){
        return content;
    }

    public void setContent(String content){
        this.content = content;
    }
    private String[] getAll(){
        String[] s = {getTitle(),getContent()};
        return s;
    }
}
