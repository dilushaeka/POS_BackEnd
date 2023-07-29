package lk.ijse.gdse.pos_backend.dto;

/*This Application Is Copyright Protected
    Author : Dilusha Ekanayaka
    Email  : dilushaeka99@gmail.com
    Date   : 7/26/2023
    Time   : 1:42 PM 
*/

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class CustomerDTO {
    int cusId;
    String cusName;
    String email;
}
