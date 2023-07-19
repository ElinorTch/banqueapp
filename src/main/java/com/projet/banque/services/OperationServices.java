package com.projet.banque.services;

import com.projet.banque.entities.*;
import com.projet.banque.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class OperationServices {
    @Autowired
    private RetraitRepository retraitRepository;

    @Autowired
    private DepotRepository depotRepository;

    @Autowired
    private TransfertRepository transfertRepository;

    @Autowired
    private OperationRepository operationRepository;

    public void saveDepot(Depot operation) {
        depotRepository.save(operation);
    }
    public void saveRetrait(Retrait operation) {
        retraitRepository.save(operation);
    }
    public void saveTransfert(Transfert operation) {
        transfertRepository.save(operation);
    }

    public List<Operation> findAllOperation() {
        List<Operation> operations = operationRepository.findAll();
        List<Operation> operation = new ArrayList<>();
        for (Operation operation1 : operations) {
            if (Objects.equals(operation1.getType_operation(), "depot") || Objects.equals(operation1.getType_operation(), "retrait")) {
                operation.add(operation1);
            }
        }
        return operation;
    }
    public List<Depot> findAllDepot() {
        return depotRepository.findAll();
    }

    public List<Transfert> findAllTransfert() {
        return transfertRepository.findAll();
    }
}
