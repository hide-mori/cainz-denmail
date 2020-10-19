
package com.tcs.denmail.online.domain.repository;

import java.util.List;

import com.tcs.denmail.common.repository.TcsBaseRepository;
import com.tcs.denmail.online.domain.entity.QRenrakuListObj;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface QRenrakuListObjRepository extends TcsBaseRepository<QRenrakuListObj, String>  {
    
    public static final String _querybase
     = "select renraku.kanri_no, renraku.sagyo_from_ymd, renraku.sagyo_to_ymd, "
     + " renraku.shudai_cd, shudai.shudai_nm, " 
     + " renraku.hinban, renraku.kenmei, " 
     + " jokyo.shinchoku_kbn, jokyo.taishogai_flg, jokyo.sagyo_shinchoku_kbn, jokyo.sagyo_taishogai_flg, "
     + " '0' as status_id, "
     + " case  " 
     + " when jokyo.SHINCHOKU_KBN = '0'AND ( jokyo.SAGYO_SHINCHOKU_KBN <> '9' OR jokyo.SAGYO_SHINCHOKU_KBN IS NULL)  THEN '1' " 
     + " when jokyo.SHINCHOKU_KBN = '1' AND (jokyo.SAGYO_SHINCHOKU_KBN <> '9' OR jokyo.SAGYO_SHINCHOKU_KBN IS NULL)  THEN '2' " 
     + " when jokyo.SHINCHOKU_KBN <> '9' AND jokyo.SAGYO_SHINCHOKU_KBN = '9' AND (jokyo.SAGYO_TAISHOGAI_FLG <> '1' OR jokyo.SAGYO_TAISHOGAI_FLG IS NULL)  THEN '3' " 
     + " when jokyo.SHINCHOKU_KBN <> '9' AND jokyo.SAGYO_SHINCHOKU_KBN = '9' AND jokyo.SAGYO_TAISHOGAI_FLG = '1' THEN '4' " 
     + " when jokyo.SHINCHOKU_KBN = '9' AND ( jokyo.TAISHOGAI_FLG <> '1' OR  jokyo.TAISHOGAI_FLG IS NULL) THEN '5' " 
     + " when jokyo.SHINCHOKU_KBN = '9' AND jokyo.TAISHOGAI_FLG = '1' THEN '6'  " 
     + " ELSE '0'  " 
     + " end as db_status_id  " 
   + " from T_RENRAKU  renraku "
   + "  inner join T_RENRAKU_jokyo jokyo "
   + "  on renraku.KANRI_NO = jokyo.KANRI_NO "
   + "  left outer join m_shudai shudai "
   +  "   on shudai.shudai_cd = renraku.shudai_cd and (shudai.DISP_FLG is null ||  shudai.DISP_FLG <> '1'  )";

    public static final String _query_where = 
        "  where jokyo.ATESAKI_TENPO_CD = :tenpoCd "
     + "  and renraku.SHONIN_FLG = '1' AND  renraku.DEL_FLG = '0' AND renraku.TENCHAKU_YMD < :tomorrow"
     + "  ORDER BY renraku.sagyo_to_ymd asc ";
    
     public static final String _select_column = " kanri_no, sagyo_from_ymd, sagyo_to_ymd, shudai_cd, shudai_nm, "
        + " hinban, kenmei, shinchoku_kbn, taishogai_flg, sagyo_shinchoku_kbn, sagyo_taishogai_flg, " 
        +" status_id, db_status_id ";

     public static final String _query = " select " + _select_column 
         + " from (" + _querybase + _query_where + ") m "
         + "  ORDER BY m.sagyo_to_ymd asc ";

    @Query(value = _query, nativeQuery = true)
    List<QRenrakuListObj> findByTenpoCd(@Param("tenpoCd") String tenpoCd, @Param("tomorrow") String tomorrow);
}