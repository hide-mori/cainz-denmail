package com.tcs.denmail.online.domain.service.list;

import java.util.List;
import java.util.ArrayList;

import com.tcs.denmail.common.service.TcsBaseService;
import com.tcs.denmail.online.app.model.RenrakuItiranModel;
import com.tcs.denmail.online.app.model.RenrakuItiranItemModel;
import com.tcs.denmail.online.domain.entity.TMShainDptAscEntity;
import com.tcs.denmail.online.domain.entity.VShainMstEntity;
import com.tcs.denmail.online.domain.entity.QRenrakuListObj;
import com.tcs.denmail.online.domain.repository.VShainMstRepository;
import com.tcs.denmail.online.domain.repository.QRenrakuListObjRepository;
import com.tcs.denmail.online.domain.repository.TMShainDptAscRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RenrakuListServiceImpl extends TcsBaseService implements RenrakuListService{

    @Autowired
    private VShainMstRepository vShainMstRepository;
    @Autowired
    private QRenrakuListObjRepository renrakuListObjRepository;
    @Autowired
    private TMShainDptAscRepository tMShainDptAscRepository;

    // 店長職位：0;
    private final String DML_SHOKUI_CD_TENTYOU = "0";

    @Override
    public RenrakuItiranModel getRenrakuItiranModel(String shainCd) {
        
        RenrakuItiranModel returnModel = new RenrakuItiranModel();
        VShainMstEntity vShainMstEntityTmp = vShainMstRepository.findByShainCd(shainCd);

        
        List<QRenrakuListObj> renrakuListObjList = null;
        /*
        Date dt = new Date();
        LocalDateTime.from(dt.toInstant()).plusDays(1);
        */
        String tomorrow = "20201212";

        // 店長の
        if( DML_SHOKUI_CD_TENTYOU.equals( vShainMstEntityTmp.getDmlShokuiCd()) ) {
            renrakuListObjList = renrakuListObjRepository.findByTenpoCd(vShainMstEntityTmp.getTenCd(), tomorrow); 
        }else {
            List<TMShainDptAscEntity>  tMShainDptAscEntityList =  tMShainDptAscRepository.findByShainCd(shainCd);
            
            if(tMShainDptAscEntityList == null || tMShainDptAscEntityList.size() == 0) {
                returnModel.setErrorCode("errorCode");
                returnModel.setErrorMsg("このメッセージをFront側に保存するように、現在あなたの担当が、社員管理マスタに登録されていません。メニューから[○社員管理マスタ]を選び登録してください。");
            } else {
              renrakuListObjList = renrakuListObjRepository.findByTenpoCd(vShainMstEntityTmp.getTenCd(), tomorrow); 
              renrakuListObjList = filterList(tMShainDptAscEntityList,renrakuListObjList);
            }
        }
        
        setReturnModel(returnModel, renrakuListObjList);
        
        return returnModel;
    }

    private List<QRenrakuListObj> filterList(List<TMShainDptAscEntity> list1, List<QRenrakuListObj> renrakuListObjList) {

        List<QRenrakuListObj> retList = new ArrayList<QRenrakuListObj>();
        if(list1 == null || list1.size() == 0) {
            return retList;
        }

        List<String> dptCdList = new ArrayList<String>();
        for(TMShainDptAscEntity tntity :list1){
            dptCdList.add(tntity.getDptCd());
        }
        
        for(QRenrakuListObj tmpObj : renrakuListObjList){
            String hiban = tmpObj.getHinban();
            if(hiban != null ) {
                if(hiban.startsWith("#") ){
                    hiban= hiban.substring(1);
                }
                String[] arrHiban = hiban.split(",");

                boolean isTarget = false;
                for(String tmpHiban : arrHiban) {
                    if( dptCdList.contains(tmpHiban) ){
                        isTarget = true;
                        break;
                    }
                }

                if(isTarget) {
                    retList.add(tmpObj);
                }
            }
        }

        return retList;
    }

    private void setReturnModel(RenrakuItiranModel returnModel, List<QRenrakuListObj> renrakuListObjList) {
        List<RenrakuItiranItemModel> list =  new ArrayList<RenrakuItiranItemModel>();

        returnModel.setItemList(list);

        if(renrakuListObjList == null) {
            return;
        }
        for(QRenrakuListObj item : renrakuListObjList) {
            RenrakuItiranItemModel newItem = new RenrakuItiranItemModel();
            newItem.setKanriNo(item.getKanriNo());
            newItem.setSagyoFromYmd(item.getSagyoFromYmd());
            newItem.setSagyoToYmd(item.getSagyoToYmd());
            newItem.setShudaiCd(item.getShudaiCd());
            newItem.setShudaiNm(item.getShudaiNm());
            newItem.setHinban(item.getHinban());
            newItem.setKenmei(item.getKenmei());
            setStatusId(newItem, item);
            setDspKenmei(newItem, item);

            list.add(newItem);

            switch(newItem.getStatusId()) {
                    case "1" : returnModel.setUnread( returnModel.getUnread() + 1);break;
                    case "2" : returnModel.setWorkingCnt( returnModel.getWorkingCnt() + 1);break;
                    case "3" : returnModel.setWorkerCompleted( returnModel.getWorkerCompleted() + 1);break;
                    case "4" : returnModel.setWorkerExcluded( returnModel.getWorkerExcluded() + 1);break;
                    case "5" : returnModel.setCompleted( returnModel.getCompleted() + 1);break;
                    case "6" : returnModel.setExcluded( returnModel.getExcluded() + 1);break;
            }
        }
        
    }
    
    private void setStatusId(RenrakuItiranItemModel newItem, QRenrakuListObj item) {
        /*
        if("0".equals(item.getShinchokuKbn())) {
            newItem.setStatusId("1");
        } else if("1".equals(item.getShinchokuKbn())) {
            newItem.setStatusId("2");
        } else if(!"9".equals(item.getShinchokuKbn())
            && "9".equals(item.getSagyoShinchokuKbn())
            && !"1".equals(item.getSagyoTaishogaiFlg())) {
                
                newItem.setStatusId("3");              
        } else if( !"9".equals(item.getShinchokuKbn())
                 && "9".equals(item.getSagyoShinchokuKbn())
                 && "1".equals(item.getSagyoTaishogaiFlg())) {
            newItem.setStatusId("4");  
        } else if("9".equals(item.getShinchokuKbn())  &&  !"1".equals(item.getTaishogaiFlg())) {
            newItem.setStatusId("5");
        } else if("9".equals(item.getShinchokuKbn())  &&  "1".equals(item.getTaishogaiFlg())) {
            newItem.setStatusId("6");
        }
        */
        newItem.setStatusId(item.getDbStatusId());
    }

    private void setDspKenmei(RenrakuItiranItemModel newItem, QRenrakuListObj item) {
        
        String dspKenmei = "";
        String hinban = item.getHinban();
        if(hinban.startsWith("#") ){
            hinban= hinban.substring(1);
        }
        String[] arrHinban = hinban.split(",");

        String kenmei = item.getKenmei() != null? item.getKenmei()  :  "";
        if(arrHinban.length > 3) {
            dspKenmei = "#" +  kenmei;
        }else {
            dspKenmei = "# " + (item.getShudaiNm()==null?item.getShudaiNm() :"")+ kenmei;
        }

        newItem.setDspKenmei(dspKenmei);
    }

}