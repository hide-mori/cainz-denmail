import { DateFPipe } from './date-formate.pipe';

describe('DateFormatePipe', () => {
  it('create an instance', () => {
    const pipe = new DateFPipe();
    expect(pipe).toBeTruthy();
  });
});