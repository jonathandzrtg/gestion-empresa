package com.cjcode.projectMinTic.Services;

import com.cjcode.projectMinTic.Entities.Enterprise;
import com.cjcode.projectMinTic.Repositories.EnterpriseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EnterpriseServiceImpl implements EnterpriseService{

    @Autowired
    private EnterpriseRepository repository;

    @Autowired
    private UtilsService utilsService;

    @Override
    public List<Enterprise> getAllEnterpriseMVC() {

        return repository.findAll();
    }

    @Override
    public Enterprise getEnterpriseByIdMVC(Long id) {
        return repository.findById(id).get();
    }

    @Override
    public Enterprise createEnterpriseMVC(Enterprise enterprise) {
        Enterprise enterpriseDb = repository.findByName(enterprise.getName());
        if(enterpriseDb == null){
            var enterpriseDb2 = repository.findByDocument(enterprise.getDocument());
            if(enterpriseDb2 == null){
                enterprise.setCreateAt(new Date());
                return repository.save(enterprise);
            }
            return null;
        }
        return null;
    }

    @Override
    public Enterprise updateEnterpriseMVC(Enterprise enterprise, Long id) {
        Optional<Enterprise> enterpriseDb = repository.findById(id);
        Enterprise enterpriseName = repository.findByName(enterprise.getName());
        if(enterpriseName == null || enterpriseName.getId() == id)
        {
            Enterprise enterpriseDocument = repository.findByDocument(enterprise.getDocument());
            if(enterpriseDocument == null || enterpriseDocument.getId() == id){
                Enterprise enterpriseSave = (Enterprise) utilsService.validateData(enterpriseDb.get(),enterprise);
                enterpriseSave.setUpdateAt(new Date());
                return repository.save(enterpriseSave);
            }
            return null;
        }
        return null;
    }

    @Override
    public Boolean deleteEnterpriseMVC(Long id) {
        try{
            repository.deleteById(id);
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public ResponseEntity<?> getAllEnterprises() {
        List<Enterprise> enterpriseList = repository.findAll();
        if(enterpriseList.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existen empresas registradas");
        }
        return ResponseEntity.ok(enterpriseList);
    }

    @Override
    public ResponseEntity<?> createEnterprise(Enterprise enterprise) {
        Enterprise enterpriseDb = repository.findByName(enterprise.getName());
        if(enterpriseDb == null){
            var enterpriseDb2 = repository.findByDocument(enterprise.getDocument());
            if(enterpriseDb2 == null){
                enterprise.setCreateAt(new Date());
                return ResponseEntity.ok(repository.save(enterprise));
            }
            return ResponseEntity.badRequest().body("Empresa con Documento: " + enterprise.getDocument()
                    + " ya registrado");
        }
        return ResponseEntity.badRequest().body("Empresa con el nombre: " + enterprise.getName() + " ya registrada");
    }

    @Override
    public ResponseEntity<?> getEnterpriseById(Long id) {
        Optional<Enterprise> enterpriseDb = repository.findById(id);
        if(enterpriseDb.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Empresa no registrada");
        }
        return ResponseEntity.ok(enterpriseDb.get());
    }

    @Override
    public ResponseEntity<?> deleteEnterprise(Long id) {
        Optional<Enterprise> enterpriseDb = repository.findById(id);
        if(enterpriseDb.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Empresa no registrada");
        }
        repository.deleteById(id);
        return ResponseEntity.ok("Empresa Eliminada Correctamente");
    }

    @Override
    public ResponseEntity<?> updateEnterprise(Long id, Enterprise enterprise) {
        Optional<Enterprise> enterpriseDb = repository.findById(id);
        if(enterpriseDb.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Empresa no registrada");
        }
        Enterprise enterpriseName = repository.findByName(enterprise.getName());
        if(enterpriseName == null){
            Enterprise enterpriseDocument = repository.findByDocument(enterprise.getDocument());
            if(enterpriseDocument == null){
                Enterprise enterpriseSave = (Enterprise) utilsService.validateData(enterpriseDb.get(),enterprise);
                enterpriseSave.setUpdateAt(new Date());
                return ResponseEntity.ok(repository.save(enterpriseSave));
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Número de Documento Ya Existe");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nombre de Empresa Ya registrado");
    }
}
