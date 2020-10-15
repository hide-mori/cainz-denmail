import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'dateJapan'
})
export class DateJapanPipe implements PipeTransform {

  transform(date: string): string {
    if (date.includes('（Sunday）')) {
      return date.replace('（Sunday）', '(日)');
    }
    if (date.includes('（Monday）')) {
      return date.replace('（Monday）', '(月)');
    }
    if (date.includes('（Tuesday）')) {
      return date.replace('（Tuesday）', '(火)');
    }
    if (date.includes('（Wednesday）')) {
      return date.replace('（Wednesday）', '(水)');
    }
    if (date.includes('（Thursday）')) {
      return date.replace('（Thursday）', '(木)');
    }
    if (date.includes('（Friday）')) {
      return date.replace('（Friday）', '(金)');
    }
    if (date.includes('（Saturday）')) {
      return date.replace('（Saturday）', '(土)');
    }

    return date;
  }

}
