package com.ferdielik.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "announce", uniqueConstraints = @UniqueConstraint(columnNames = {"date", "title", "author"}))
public class Announce implements Serializable
{
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "date")
    private String date;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private AnnounceType type;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getDate()
    {
        return date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getAuthor()
    {
        return author;
    }

    public void setAuthor(String author)
    {
        this.author = author;
    }

    public AnnounceType getType()
    {
        return type;
    }

    public void setType(AnnounceType type)
    {
        this.type = type;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public Announce clone()
    {
        Announce announce = new Announce();
        announce.setAuthor(author);
        announce.setTitle(title);
        announce.setType(type);
        announce.setDate(date);
        announce.setContent(content);

        return announce;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Announce announce = (Announce) o;

        if (!title.equals(announce.title)) return false;
        if (!author.equals(announce.author)) return false;
        return content.equals(announce.content);
    }

    @Override
    public int hashCode()
    {
        int result = title.hashCode();
        result = 31 * result + author.hashCode();
        result = 31 * result + content.hashCode();
        return result;
    }
}