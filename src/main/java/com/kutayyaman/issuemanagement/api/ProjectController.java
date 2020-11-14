package com.kutayyaman.issuemanagement.api;

import com.kutayyaman.issuemanagement.dto.ProjectDto;
import com.kutayyaman.issuemanagement.service.impl.ProjectServiceImpl;
import com.kutayyaman.issuemanagement.util.ApiPaths;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(ApiPaths.ProjectCtrl.CTRL)
@Api(value = ApiPaths.ProjectCtrl.CTRL, description = "Project APIs")
public class ProjectController {

    private final ProjectServiceImpl projectService;

    public ProjectController(ProjectServiceImpl projectService){
        this.projectService = projectService;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get By Id Operation", response = ProjectDto.class)
    public ResponseEntity<ProjectDto> getById(@PathVariable(value = "id", required = true) Long id){
        ProjectDto projectDto = projectService.getById(id);
        return ResponseEntity.ok(projectDto);
    }

    @PostMapping
    @ApiOperation(value = "Create Operation", response = ProjectDto.class)
    public ResponseEntity<ProjectDto> createProject(@Valid @RequestBody ProjectDto projectDto){ //@Valid anatasyonu ProjectDto nesnesi icerisindeki ornegin @Notnull gibi validasyon islemlerinden basarili gecmediyse geriye error dondurmek icin
        return ResponseEntity.ok(projectService.save(projectDto));
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT) //veya @PutMapping("/update/{id}") seklinde yapilabilir.Bu reqeustmapping anatasyonu diger methodlarda da kullanilabilir.
    @ApiOperation(value = "Update Operation", response = ProjectDto.class)
    public ResponseEntity<ProjectDto> updateProject(@PathVariable(value = "id", required = true) Long id, @Valid @RequestBody ProjectDto projectDto){
        return ResponseEntity.ok(projectService.update(id,projectDto));
    }

    @DeleteMapping(path = "/{id}")
    @ApiOperation(value = "Delete Operation", response = Boolean.class)
    public ResponseEntity<Boolean> delete(@PathVariable(value = "id",required = true) Long id){
        return ResponseEntity.ok(projectService.delete(id));
    }

}
