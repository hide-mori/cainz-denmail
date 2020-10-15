import { HttpHeaders } from '@angular/common/http';
/**
 * @classdesc HttpConstants HTTP通信定数クラス
 */
export class HttpConstants {

    /**
     * サーバ通信時HTTPヘッダ
     * @static
     * @memberof HttpConstants
     */
    public static readonly HTTP_OPTIONS = {
        headers: new HttpHeaders(        
          {
            "Content-Type": "application/json",
            "Access-Control-Allow-Origin": "*",
          }
        ),
    };

    public static readonly HTTP_OPTIONS_FILE_UPLOAD = {
        headers: new HttpHeaders({ 'Content-Type': 'application/octet-stream' }),
    };

    /**
     * HTTP通信結果:成功
     * @static
     * @type {string}
     * @memberof HttpConstants
     */
    public static readonly HTTP_SUCCESS: string = 'success';

    /**
     * HTTP通信結果:失敗
     * @static
     * @type {string}
     * @memberof HttpConstants
     */
    public static readonly HTTP_FAILURE: string = 'failure';
}
