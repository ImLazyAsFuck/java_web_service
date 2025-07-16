package com.ss7.service.worker;

import com.ss7.model.entity.Worker;
import com.ss7.repo.WorkerRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkerServiceImpl implements WorkerService{

    private final WorkerRepo workerRepo;

    @Override
    public void deleteById(Long id){
        if(findById(id) == null){
            throw new EntityNotFoundException("Worker not found with id = " + id);
        }
        workerRepo.deleteById(id);
    }

    @Override
    public List<Worker> findAll(){
        return workerRepo.findAll();
    }

    @Override
    public Worker findById(Long id){
        return workerRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Worker not found with id = " + id));
    }

    @Override
    public Worker save(Worker worker){
        return workerRepo.save(worker);
    }

    @Override
    public Worker update(Worker worker){
        Worker exist = findById(worker.getId());
        exist.setFullName(worker.getFullName());
        exist.setAddress(worker.getAddress());
        exist.setPhone(worker.getPhone());
        exist.setSalary(worker.getSalary());
        return workerRepo.save(exist);
    }
}
