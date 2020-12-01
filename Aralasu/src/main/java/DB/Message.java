package DB;

import java.sql.Timestamp;

public class Message {
    private Long id;
    private Chat chat;
    private User user;
    private User sender;
    private String message_text;
    private Boolean read_by_receiver;
    private Timestamp sent_date;

    public Message() {
    }

    public Message(Long id, Chat chat, User user, User sender, String message_text, Boolean read_by_receiver, Timestamp sent_date) {
        this.id = id;
        this.chat = chat;
        this.user = user;
        this.sender = sender;
        this.message_text = message_text;
        this.read_by_receiver = read_by_receiver;
        this.sent_date = sent_date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public String getMessage_text() {
        return message_text;
    }

    public void setMessage_text(String message_text) {
        this.message_text = message_text;
    }

    public Boolean getRead_by_receiver() {
        return read_by_receiver;
    }

    public void setRead_by_receiver(Boolean read_by_receiver) {
        this.read_by_receiver = read_by_receiver;
    }

    public Timestamp getSent_date() {
        return sent_date;
    }

    public void setSent_date(Timestamp sent_date) {
        this.sent_date = sent_date;
    }
}
