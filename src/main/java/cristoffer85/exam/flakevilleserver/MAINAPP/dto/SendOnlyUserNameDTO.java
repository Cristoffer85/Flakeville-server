package cristoffer85.exam.flakevilleserver.MAINAPP.dto;

public class SendOnlyUserNameDTO {
    private String username;

    public SendOnlyUserNameDTO(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
