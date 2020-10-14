package com.tcs.denmail.online.domain.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.tcs.denmail.common.service.TcsBaseService;
import com.tcs.denmail.online.app.model.Work;
import com.tcs.denmail.online.app.model.WorkDetail;
import com.tcs.denmail.online.common.ConvertUtil;
import com.tcs.denmail.online.domain.entity.WorkDetailEntity;
import com.tcs.denmail.online.domain.entity.WorkEntity;
import com.tcs.denmail.online.domain.repository.WorkRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkServiceImpl extends TcsBaseService implements WorkService {

    @Autowired
    private WorkRepository workRepository;

    @Override
    public WorkServiceOutDto getWorkDetail(WorkServiceInDto inDto) {

        List<WorkEntity> workEntities = workRepository.findAll();
        WorkServiceOutDto workServiceOutDto = new WorkServiceOutDto();

        List<Work> works = workEntities.stream().map(this::mapToWork).collect(Collectors.toList());

        workServiceOutDto.setWorkList(works);
        return workServiceOutDto;
    }

    @Override
    public WorkServiceOutDto registWorkDetail(WorkServiceInDto inDto) {
        WorkEntity work = new WorkEntity();
        work.setWorkId(4);
        work.setWorkName("やっつけ仕事4");
        WorkDetailEntity detail = new WorkDetailEntity();
        detail.setWorkId(4);
        detail.setWorkDetailId(1);
        detail.setWorkDetailName("やっつけ仕事明細1");

        List<WorkDetailEntity> workDetailEntities = new ArrayList<WorkDetailEntity>();
        workDetailEntities.add(detail);
        work.setWorkDetailEntities(workDetailEntities);
        workRepository.save(work);
        return null;
    }

    public Work mapToWork(WorkEntity entity) {
        Work work = new Work();
        work.setWorkId(ConvertUtil.toString(entity.getWorkId()));
        work.setWorkName(entity.getWorkName());
        work.setAssigningDate(ConvertUtil.toString(entity.getAssigningDate()));
        work.setDeadline(ConvertUtil.toString(entity.getDeadline()));
        work.setWorkStatus(entity.getWorkStatus());
        work.setTenpoId(entity.getTenpoId());
        work.setTenpoName(entity.getTenpoName());
        List<WorkDetail> workDetails = entity.getWorkDetailEntities().stream().map(this::mapToWorkDetail)
                .collect(Collectors.toList());
        work.setWorkDetails(workDetails);
        return work;
    }

    public WorkDetail mapToWorkDetail(WorkDetailEntity detailEntity) {
        WorkDetail workDetail = new WorkDetail();
        workDetail.setWorkDetailId(Integer.toString(detailEntity.getWorkDetailId()));
        workDetail.setWorkDetailName(detailEntity.getWorkDetailName());
        workDetail.setWorkDetailStatus(detailEntity.getWorkDetailStatus());
        workDetail.setWorkDetailStatusString(ConvertUtil.toString(detailEntity.getWorkDetailStatus()));
        workDetail.setEmployeeId(detailEntity.getEmployeeId());
        workDetail.setEmployeeName(detailEntity.getEmployeeName());
        workDetail.setWorkDetailContent(detailEntity.getWorkDetailContent());
        workDetail.setWorkDetailProcess(detailEntity.getWorkDetailProcess());
        workDetail.setFilePath(detailEntity.getFilePath());
        workDetail.setReport(detailEntity.getReport());
        workDetail.setCompleted(false);
        return workDetail;
    }

}
