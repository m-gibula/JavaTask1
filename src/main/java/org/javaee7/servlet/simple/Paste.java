package org.javaee7.servlet.simple;

/**
 * Created by student on 10.07.17.
 */
public class Paste {
    private String title;
    private String content;

    public Paste(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
