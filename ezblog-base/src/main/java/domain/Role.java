package domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import schema.BaseSchema;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role extends BaseSchema {
    private Integer id;
    private String roleName;
    private String roleCode;
    private String description;
}
