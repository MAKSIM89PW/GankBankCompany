package Bank.service;

import Bank.model.Beneficiary;

public interface BeneficiaryService {
    Beneficiary createBeneficiary(String name);
    Beneficiary getBeneficiary(Long id);
}
