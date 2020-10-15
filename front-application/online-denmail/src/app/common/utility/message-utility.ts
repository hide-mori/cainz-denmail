export class MessageUtility {
  public static getMessageWithParam(msg, param?: string[]): string {
    const args = [];
    const paramArray = msg.content.match(/\{(\d+)\}/g);
    for (let i = 0; i < param.length; i++) {
      if (paramArray[i] === undefined) {
        console.warn(
          'メッセージID ' +
            msg.msgID +
            ' の置換先パラメータ{' +
            i +
            '}がありません。'
        );
      }
      args[i] = param[i];
    }
    return msg.content.replace(/\{(\d+)\}/g, function (m, k) {
      return args[k];
    });
  }
}
