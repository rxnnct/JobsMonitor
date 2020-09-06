package ru.rxnnct.jobsmonitor.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.rxnnct.jobsmonitor.domain.ProxyProperties;
import ru.rxnnct.jobsmonitor.repo.ProxyPropertiesRepo;

import java.util.List;

@RestController
@RequestMapping("api/proxy-properties")
public class ProxyPropertiesController {
    private final ProxyPropertiesRepo proxyPropertiesRepo;

    @Autowired
    public ProxyPropertiesController(ProxyPropertiesRepo proxyPropertiesRepo) {
        this.proxyPropertiesRepo = proxyPropertiesRepo;
    }

    @GetMapping
    public List<ProxyProperties> list() {
        return proxyPropertiesRepo.findAll();
    }

    @GetMapping("{id}")
    public ProxyProperties getOne(@PathVariable("id") ProxyProperties proxyPropertiesRepo) {
        return proxyPropertiesRepo;
    }

    @PostMapping
    public ProxyProperties create(@RequestBody ProxyProperties proxyProperties) {
        return proxyPropertiesRepo.save(proxyProperties);
    }

    @PutMapping("{id}")
    public ProxyProperties update(
            @PathVariable("id") ProxyProperties proxyPropertiesFromDb,
            @RequestBody ProxyProperties proxyProperties
    ) {
        BeanUtils.copyProperties(proxyProperties, proxyPropertiesFromDb, "id");

        return proxyPropertiesRepo.save(proxyPropertiesFromDb);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") ProxyProperties proxyProperties) {
        proxyPropertiesRepo.delete(proxyProperties);
    }
}
