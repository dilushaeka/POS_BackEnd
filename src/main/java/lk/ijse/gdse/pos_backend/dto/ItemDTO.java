package lk.ijse.gdse.pos_backend.dto;

/*This Application Is Copyright Protected
    Author : Dilusha Ekanayaka
    Email  : dilushaeka99@gmail.com
    Date   : 7/29/2023
    Time   : 9:40 AM 
*/

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ItemDTO {
    String itemId;
    String description;
    double unitPrice;
    double qty;


}
