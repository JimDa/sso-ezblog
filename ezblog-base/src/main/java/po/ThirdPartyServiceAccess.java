package po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ThirdPartyServiceAccess {
    private Integer id;
    private String serviceName;
    private String accessKey;
    private String accessSecret;
}
