package org.snow.model.business;


import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "NOTICE")
public class Notice {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "TITLE", length =100, unique = false)
    @Size(min = 1, max = 100)
    private String title;

    @Column(name = "NOTICE_TEXT", length =100, unique = false)
    @Size(min = 1, max = 100)
    private String noticeText;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNoticeText() {
        return noticeText;
    }

    public void setNoticeText(String noticeText) {
        this.noticeText = noticeText;
    }
}
