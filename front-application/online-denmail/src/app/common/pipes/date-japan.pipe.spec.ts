import { DateJapanPipe } from './date-japan.pipe';

describe('DateJapanPipe', () => {
  // it('create an instance', () => {
  //   const pipe = new DateJapanPipe();
  //   expect(pipe).toBeTruthy();
  // });

  // This pipe is a pure, stateless function so no need for BeforeEach
  const pipe = new DateJapanPipe();

  it('transforms "（Sunday）" to "(日)"', () => {
    expect(pipe.transform('（Sunday）')).toBe('(日)');
  });

  it('transforms "（Monday）" to "(月)"', () => {
    expect(pipe.transform('（Monday）')).toBe('(月)');
  });

  it('transforms "（Tuesday）" to "(火)"', () => {
    expect(pipe.transform('（Tuesday）')).toBe('(火)');
  });

  it('transforms "（Wednesday）" to "(水)"', () => {
    expect(pipe.transform('（Wednesday）')).toBe('(水)');
  });

  it('transforms "（Thursday）" to "(木)"', () => {
    expect(pipe.transform('（Thursday）')).toBe('(木)');
  });

  it('transforms "（Friday）" to "(金)"', () => {
    expect(pipe.transform('（Friday）')).toBe('(金)');
  });

  it('transforms "（Saturday） to "(土)"', () => {
    expect(pipe.transform('（Saturday）')).toBe('(土)');
  });


});
