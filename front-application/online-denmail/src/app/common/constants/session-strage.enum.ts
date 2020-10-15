/**
 * @classdesc セッションストレージ列挙型クラス
 */
export enum SessionStorageEnum {
    LAST_LOGIN_TIME = 'last.login.time',
    LOGIN_INFO_USER_ID = 'loginInfo.userID',
    LOGIN_INFO_USER_NAME = 'loginInfo.userName',
    LOGIN_INFO_USER_TYPE = 'loginInfo.userType',
    LOGIN_INFO_USER_CODE = 'loginInfo.userCode',
    LOGIN_INFO_TENPO_CODE = 'loginInfo.tenpoCode',
    LOGIN_INFO_TENPO_NAME = 'loginInfo.tenpoName',

    LOGIN_INFO_FILE_NOTICE_ADDRESS = 'loginInfo.fileNoticeAddress',
    LOGIN_INFO_ADMIN_FLG = 'loginInfo.adminFlg',
    LOGIN_INFO_REFERENCE_MODE = 'referenceMode',
    ROLE = 'userInfo.role',
    ROOM_ROOM_ID = 'room.roomID',
    TEXT_MESSAGE_CACHE = 'textMessageCache',
    STATUS_MESSAGE_CACHE = 'statusMessageCache',
    INS_SELECT_INFO = 'inssuranceSelectInfo'
}
