import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'dateFormatePipe'
})
export class DateFPipe implements PipeTransform {

  transform(value: string) {

    let toValue = value;
    
    if( value != null && value.length == 8){
      toValue = value.substring(0,4) + "/" +  value.substring(4,6) +"/" + value.substring(6,8);
    } else {
      toValue = value;
    }
    
    return toValue;
  }

}
