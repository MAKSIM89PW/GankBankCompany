package service;

import model.Beneficiary;

public interface BeneficiaryService {
    Beneficiary createBeneficiary(String name);
    Beneficiary getBeneficiary(Long id);
}
