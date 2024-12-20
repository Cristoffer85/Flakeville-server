package cristoffer85.exam.flakevilleserver.RABBITMQ.dto;

import lombok.Data;

@Data
public class MsgDto {
    private String sender;
    private String receiver;
    private String message;
}