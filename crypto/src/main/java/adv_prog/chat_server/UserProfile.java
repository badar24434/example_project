package adv_prog.chat_server;

import java.io.Serializable;

public class UserProfile implements Serializable {
    private String username;
    private String status;
    private String avatarUrl;
    private String bio;

    public UserProfile(String username) {
        this.username = username;
        this.status = "Online";
        this.avatarUrl = "default_avatar.png";
        this.bio = "No bio sett";
    }

    // Getters and setters
    public String getUsername() { return username; }
    public String getStatus() { return status; }
    public String getAvatarUrl() { return avatarUrl; }
    public String getBio() { return bio; }
    
    public void setStatus(String status) { this.status = status; }
    public void setAvatarUrl(String avatarUrl) { this.avatarUrl = avatarUrl; }
    public void setBio(String bio) { this.bio = bio; }
}
