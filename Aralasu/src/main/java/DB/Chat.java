package DB;

import java.sql.Timestamp;

public class Chat {
    private Long id;
    private User user;
    private User opponent_user;
    private Timestamp created_date;
    private String latest_message_text;
    private Timestamp latest_message_time;
    private int countOfNewMessage;

    public Chat() {
    }

    public Chat(Long id, User user, User opponent_user, Timestamp created_date, String latest_message_text, Timestamp latest_message_time, int countOfNewMessage) {
        this.id = id;
        this.user = user;
        this.opponent_user = opponent_user;
        this.created_date = created_date;
        this.latest_message_text = latest_message_text;
        this.latest_message_time = latest_message_time;
        this.countOfNewMessage = countOfNewMessage;
    }

    public int getCountOfNewMessage() {
        return countOfNewMessage;
    }

    public void setCountOfNewMessage(int countOfNewMessage) {
        this.countOfNewMessage = countOfNewMessage;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getOpponent_user() {
        return opponent_user;
    }

    public void setOpponent_user(User opponent_user) {
        this.opponent_user = opponent_user;
    }

    public Timestamp getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Timestamp created_date) {
        this.created_date = created_date;
    }

    public String getLatest_message_text() {
        return latest_message_text;
    }

    public void setLatest_message_text(String latest_message_text) {
        this.latest_message_text = latest_message_text;
    }

    public Timestamp getLatest_message_time() {
        return latest_message_time;
    }

    public void setLatest_message_time(Timestamp latest_message_time) {
        this.latest_message_time = latest_message_time;
    }
}
