package ru.rxnnct.jobsmonitor.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.rxnnct.jobsmonitor.domain.ProxyProperty;
import ru.rxnnct.jobsmonitor.repo.ProxyPropertyRepo;

import java.util.List;

@RestController
@RequestMapping("api/admin/proxy-properties")
public class ProxyPropertyController {
    private final ProxyPropertyRepo proxyPropertyRepo;

    @Autowired
    public ProxyPropertyController(ProxyPropertyRepo proxyPropertyRepo) {
        this.proxyPropertyRepo = proxyPropertyRepo;
    }

    @GetMapping
    public List<ProxyProperty> get() {
        return proxyPropertyRepo.findAll();
    }

    @GetMapping("{id}")
    public ProxyProperty getOne(@PathVariable("id") ProxyProperty proxyProperty) {
        return proxyProperty;
    }

    @PostMapping
    public ProxyProperty create(@RequestBody ProxyProperty proxyProperty) {
        return proxyPropertyRepo.save(proxyProperty);
    }

    @PutMapping("{id}")
    public ProxyProperty update(
            @PathVariable("id") ProxyProperty proxyPropertyFromDb,
            @RequestBody ProxyProperty proxyProperty
    ) {
        BeanUtils.copyProperties(proxyProperty, proxyPropertyFromDb, "id");

        return proxyPropertyRepo.save(proxyPropertyFromDb);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") ProxyProperty proxyProperty) {
        proxyPropertyRepo.delete(proxyProperty);
    }
}
