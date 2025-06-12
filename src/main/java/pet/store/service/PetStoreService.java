package pet.store.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import pet.store.dao.PetStoreDao;
import pet.store.controller.model.PetStoreData;
import pet.store.entity.PetStore;

import java.util.NoSuchElementException;

@Service
public class PetStoreService {
    @Autowired
    private PetStoreDao petStoreDao;

    public PetStoreData savePetStore(PetStoreData data) {
        PetStore petStore = findOrCreatePetStore(data.id);

        petStore.storeName = data.storeName;
        petStore.storeLocation = data.storeLocation;

        PetStore saved = petStoreDao.save(petStore);
        return new PetStoreData(saved);
    }

    private PetStore findOrCreatePetStore(Long id) {
        if (id == null) {
            return new PetStore();
        } 
        return petStoreDao.findById(id)
            .orElseThrow(() -> new NoSuchElementException("Pet store not found with ID: " + id));
    }
}
