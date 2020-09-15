package ru.rxnnct.jobsmonitor.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.rxnnct.jobsmonitor.domain.SourceGetMethod;
import ru.rxnnct.jobsmonitor.repo.SourceGetMethodRepo;

import java.util.List;

@RestController
@RequestMapping("api/source-get-methods")
public class SourceGetMethodController {
    private final SourceGetMethodRepo sourceGetMethodRepo;

    @Autowired
    public SourceGetMethodController(SourceGetMethodRepo sourceGetMethodRepo) {
        this.sourceGetMethodRepo = sourceGetMethodRepo;
    }

    @GetMapping
    public List<SourceGetMethod> list() {
        return sourceGetMethodRepo.findAll();
    }

    @GetMapping("{id}")
    public SourceGetMethod getOne(@PathVariable("id") SourceGetMethod sourceGetMethod) {
        return sourceGetMethod;
    }

    @PostMapping
    public SourceGetMethod create(@RequestBody SourceGetMethod sourceGetMethod) {
        return sourceGetMethodRepo.save(sourceGetMethod);
    }

    @PutMapping("{id}")
    public SourceGetMethod update(
            @PathVariable("id") SourceGetMethod sourceGetMethodFromDb,
            @RequestBody SourceGetMethod sourceGetMethod
    ) {
        BeanUtils.copyProperties(sourceGetMethod, sourceGetMethodFromDb, "id");

        return sourceGetMethodRepo.save(sourceGetMethodFromDb);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") SourceGetMethod sourceGetMethod) {
        sourceGetMethodRepo.delete(sourceGetMethod);
    }
}
