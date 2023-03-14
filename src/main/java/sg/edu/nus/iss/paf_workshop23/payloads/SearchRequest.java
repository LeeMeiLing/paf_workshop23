package sg.edu.nus.iss.paf_workshop23.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchRequest {
    
    private Integer orderId;
    
}
