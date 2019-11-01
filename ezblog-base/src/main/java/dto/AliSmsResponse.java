package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AliSmsResponse {
    private String Message;
    private String RequestId;
    private String BizId;
    private String Code;
}
