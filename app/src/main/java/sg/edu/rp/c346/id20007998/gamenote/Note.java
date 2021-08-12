package sg.edu.rp.c346.id20007998.gamenote;

import java.io.Serializable;

public class Note implements Serializable {
    private int id;
    private String title;
    private String author;
    private String name;
    private String type;
    private String description;

    public Note(int id,String title,String author,String name,String type,String description){
        this.id=id;
        this.title=title;
        this.author=author;
        this.name=name;
        this.type=type;
        this.description=description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
