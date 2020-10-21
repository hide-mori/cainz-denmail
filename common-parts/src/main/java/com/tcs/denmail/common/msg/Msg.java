/*
 * Project Name  : 新でんメール
 * 
 * 更新内容
 */
package com.tcs.denmail.common.msg;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.Map;

import com.tcs.denmail.common.msg.builder.MsgInfoBuilder;
import com.tcs.denmail.common.msg.builder.MsgInfoBuilderFactory;
import com.tcs.denmail.common.msg.exception.MsgIdNotFoundException;
import com.tcs.denmail.common.msg.exception.MsgInitializeException;

/**
 * メッセージ定義情報の取得を行うファーサードクラスです。
 * 
 * <pre>
 *      // メッセージ定義情報の初期化
 *      Msg.initialize();
 *      
 *      // メッセージ定義情報の取得
 *      Msg.getMessage(&lt;メッセージID&gt;,…)
 * </pre>
 * 
 * @author Hayato Nishimura
 * @version 3.2.0
 * @since 1.0.0
 */
public class Msg {

  /**
   * メッセージ情報生成オブジェクト
   */
  private static MsgInfoBuilder msgInfoBuilder;

  /**
   * 引数で渡されたメッセージIDで定義され、かつJVMのデフォルトロケールと一致する言語のメッセージ文言を返す。
   * 
   * @param msgId メッセージID
   * @return メッセージ文言
   */
  public static String getMessage(String msgId) {
    return getMessage(msgId, new String[] { null });
  }

  /**
   * 引数で渡されたメッセージIDで定義され、かつ指定されたロケールに最もマッチする言語のメッセージ文言を返す。
   * 
   * @param msgId  メッセージID
   * @param locale 取得したいメッセージのロケール
   * @return メッセージ文言
   */
  public static String getMessage(String msgId, Locale locale) {
    return getMessage(msgId, new String[] { null }, locale);
  }

  /**
   * 引数で渡されたメッセージIDで定義され、かつJVMのデフォルトロケールに最もマッチする言語のメッセージ文言を返す。
   * 
   * @param msgId  メッセージID
   * @param param1 パラメータ1
   * @return メッセージ文言
   */
  public static String getMessage(String msgId, String param1) {
    return getMessage(msgId, new String[] { param1 });
  }

  /**
   * 引数で渡されたメッセージIDで定義され、かつ指定されたロケールに最もマッチする言語のメッセージ文言を返す。
   * 
   * @param msgId  メッセージID
   * @param param1 パラメータ1
   * @param locale 取得したいメッセージのロケール
   * @return メッセージ文言
   */
  public static String getMessage(String msgId, String param1, Locale locale) {
    return getMessage(msgId, new String[] { param1 }, locale);
  }

  /**
   * 引数で渡されたメッセージIDで定義され、かつJVMのデフォルトロケールに最もマッチする言語のメッセージ文言を返す。
   * 
   * @param msgId  メッセージID
   * @param param1 パラメータ1
   * @param param2 パラメータ2
   * @return メッセージ文言
   */
  public static String getMessage(String msgId, String param1, String param2) {

    return getMessage(msgId, new String[] { param1, param2 });
  }

  /**
   * 引数で渡されたメッセージIDで定義され、かつ指定されたロケールに最もマッチする言語のメッセージ文言を返す。
   * 
   * @param msgId  メッセージID
   * @param param1 パラメータ1
   * @param param2 パラメータ2
   * @param locale 取得したいメッセージのロケール
   * @return メッセージ文言
   */
  public static String getMessage(String msgId, String param1, String param2, Locale locale) {

    return getMessage(msgId, new String[] { param1, param2 }, locale);
  }

  /**
   * 引数で渡されたメッセージIDで定義され、かつJVMのデフォルトロケールに最もマッチする言語のメッセージ文言を返す。
   * 
   * @param msgId  メッセージID
   * @param param1 パラメータ1
   * @param param2 パラメータ2
   * @param param3 パラメータ3
   * @return メッセージ文言
   */
  public static String getMessage(String msgId, String param1, String param2, String param3) {

    return getMessage(msgId, new String[] { param1, param2, param3 });
  }

  /**
   * 引数で渡されたメッセージIDで定義され、かつ指定されたロケールに最もマッチする言語のメッセージ文言を返す。
   * 
   * @param msgId  メッセージID
   * @param param1 パラメータ1
   * @param param2 パラメータ2
   * @param param3 パラメータ3
   * @param locale 取得したいメッセージのロケール
   * @return メッセージ文言
   */
  public static String getMessage(String msgId, String param1, String param2, String param3, Locale locale) {

    return getMessage(msgId, new String[] { param1, param2, param3 }, locale);
  }

  /**
   * 引数で渡されたメッセージIDで定義され、かつJVMのデフォルトロケールに最もマッチする言語のメッセージ文言を返す。
   * 
   * @param msgId     メッセージID
   * @param paramList パラメータの配列
   * @return メッセージ文言
   */
  public static String getMessage(String msgId, String[] paramList) {
    return getMessage(msgId, paramList, Locale.getDefault());
  }

  /**
   * 引数で渡されたメッセージIDで定義され、かつ指定されたロケールと一致する言語のメッセージ文言を返す。
   * 
   * @param msgId     メッセージID
   * @param paramList パラメータの配列
   * @param locale    取得したいメッセージのロケール
   * @return メッセージ文言
   */
  public static String getMessage(String msgId, String[] paramList, Locale locale) {
    try {
      Map msgInfo = getMsgInfo(msgId);
      Map<String, String> contents = (Map<String, String>) msgInfo.get(MsgConstants.KEY_CONTENT);
      String content = getContent(contents, locale);
      return formatContent(content, paramList);
    } catch (MsgIdNotFoundException e) {
      MsgStd.err(e);
      return "";
    }
  }

  /**
   * コンテンツマップから、指定されたロケールに最もマッチするメッセージ文言を取得します。<br>
   * <br>
   * ■ ロケールマッチングアルゴリズム<br>
   * 下記の処理を順に行う。<br>
   * <ol>
   * <li>言語コード、国コード、バリアントが全て一致するメッセージが定義されていれば、そのメッセージを返す。</li>
   * <li>言語コード、国コードが一致するメッセージが定義されていれば、そのメッセージを返す。</li>
   * <li>言語コードが一致するメッセージが定義されていれば、そのメッセージを返す。</li>
   * <li>フォールバックメッセージが定義されていれば、そのメッセージを返す。</li>
   * <li>空文字列を返す</li>
   * </ol>
   * 
   * @param contents ロケールをキー、メッセージを値としたマップ
   * @param locale   取得したいメッセージのロケール
   * @return 指定されたロケールに最もマッチするメッセージ文言
   */
  private static String getContent(Map<String, String> contents, Locale locale) {
    if (contents == null || locale == null) {
      return "";
    }

    String[] keys = { locale.toString(), locale.getLanguage() + "_" + locale.getCountry(), locale.getLanguage(),
        MsgConstants.KEY_FALLBACK_LOCALE };

    for (String key : keys) {
      if (contents.containsKey(key)) {
        return contents.get(key);
      }
    }

    return "";
  }

  /**
   * 引数で渡されたメッセージIDで定義されたメッセージ定義情報を返す。<br>
   * <br>
   * メッセージ定義ファイル中の<msg-info>で囲まれた情報を1件分のメッセージ定義として扱う。<br>
   * 属性はString、要素はListとして格納されており、必要に応じて以下のように取り出す。<br>
   * <br>
   * String str = (String) msgInfo.get("属性名");<br>
   * List list = (List) msgInfo.get("要素名");<br>
   * <br>
   * 該当するメッセージ定義が存在しない場合はMsgIdNotFoundExceptionをスローします。
   * 
   * @param msgId メッセージID
   * @return メッセージ定義情報
   * @throws MsgIdNotFoundException メッセージIDが存在しなかった場合
   * @throws MsgInitializeException 初期化に失敗した場合
   */
  public static Map getMsgInfo(String msgId) throws MsgIdNotFoundException, MsgInitializeException {
    if (msgInfoBuilder == null) {
      initialize();
    }
    return msgInfoBuilder.getMsgInfo(msgId);
  }

  /**
   * メッセージ文言とパラメータをマージする。
   * 
   * @param content   メッセージ文言
   * @param paramList パラメータの配列
   * @return マージした文言
   */
  public static String formatContent(String content, String[] paramList) {
    // パラメータ配列がnullの場合、マージを行わずにパラメータ記号を削除して返す
    if (paramList == null) {
      return deleteParamSymbol(content);
    } else {
      for (int i = 0; i < paramList.length; i++) {
        // パラメータ配列の要素がnullの場合、空文字列と置き換える
        if (paramList[i] == null) {
          paramList[i] = "";
        }
      }
      // メッセージとパラメータをマージする
      // チケット#575 【開発部品】【MsgAPI】メッセージ文言に「'（シングルクォーテーション）」が入っている場合の不具合対応
      if (content == null) {
        content = "";
      } else {
        content = MessageFormat.format(content.replaceAll("'", "''"), (Object[]) paramList);
      }
      return deleteParamSymbol(content);
    }
  }

  /**
   * メッセージ文言からパラメータ記号を削除します
   * 
   * @param content メッセージ文言
   * @return パラメータ記号を削除した文言
   */
  private static String deleteParamSymbol(String content) {
    // パラメータ記号を削除
    content = content.replaceAll(MsgProperty.getProperty(MsgConstants.REGEX_PARAM_MARK), "");
    return content;
  }

  /**
   * メッセージ設定の初期化処理を行います。
   * 
   * @throws MsgInitializeException メッセージ設定の初期化に失敗した場合
   */
  public static void initialize() throws MsgInitializeException {
    // メッセージ情報生成クラスの取得
    msgInfoBuilder = MsgInfoBuilderFactory.getMsgInfoBuilder();

    // メッセージ情報の初期化
    msgInfoBuilder.initialize();
  }

}
