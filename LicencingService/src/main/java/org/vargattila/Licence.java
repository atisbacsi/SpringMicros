package org.vargattila;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Licence {
    private String id;
    private String productName;
    private String licenceType;
    private String organizationId;
    private String fuerElvis;
}
