package com.ss7.controller;

import com.ss7.model.dto.DataResponse;
import com.ss7.model.entity.Worker;
import com.ss7.service.worker.WorkerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workers")
@RequiredArgsConstructor
public class WorkerController{

    private final WorkerService workerService;

    @GetMapping
    public ResponseEntity<DataResponse<List<Worker>>> findAll(){
        return ResponseEntity.ok(new DataResponse<>(workerService.findAll(), HttpStatus.OK));
    }

    @GetMapping("{id}")
    public ResponseEntity<DataResponse<Worker>> findById(@PathVariable Long id){
        return ResponseEntity.ok(new DataResponse<>(workerService.findById(id), HttpStatus.OK));
    }

    @PostMapping
    public ResponseEntity<DataResponse<Worker>> save(@RequestBody Worker worker){
        return ResponseEntity.ok(new DataResponse<>(workerService.save(worker), HttpStatus.CREATED));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<DataResponse<Worker>> deleteById(@PathVariable Long id){
        workerService.deleteById(id);
        return ResponseEntity.ok(new DataResponse<>(null, HttpStatus.NO_CONTENT));
    }

    @PutMapping("{id}")
    public ResponseEntity<DataResponse<Worker>> update(@RequestBody Worker worker){
        return ResponseEntity.ok(new DataResponse<>(workerService.update(worker), HttpStatus.OK));
    }
}
