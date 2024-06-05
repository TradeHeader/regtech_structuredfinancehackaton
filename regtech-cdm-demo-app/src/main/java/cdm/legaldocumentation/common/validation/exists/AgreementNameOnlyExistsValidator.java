package cdm.legaldocumentation.common.validation.exists;

import cdm.legaldocumentation.common.AgreementName;
import cdm.legaldocumentation.common.ContractualMatrix;
import cdm.legaldocumentation.common.ContractualTermsSupplement;
import cdm.legaldocumentation.common.LegalAgreementTypeEnum;
import cdm.legaldocumentation.common.metafields.FieldWithMetaContractualDefinitionsEnum;
import cdm.legaldocumentation.master.metafields.FieldWithMetaMasterAgreementTypeEnum;
import cdm.legaldocumentation.master.metafields.FieldWithMetaMasterConfirmationAnnexTypeEnum;
import cdm.legaldocumentation.master.metafields.FieldWithMetaMasterConfirmationTypeEnum;
import cdm.product.collateral.CollateralMarginTypeEnum;
import cdm.product.collateral.metafields.FieldWithMetaCreditSupportAgreementTypeEnum;
import com.google.common.collect.ImmutableMap;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ExistenceChecker;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.ValidatorWithArg;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;

public class AgreementNameOnlyExistsValidator implements ValidatorWithArg<AgreementName, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends AgreementName> ValidationResult<AgreementName> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("agreementType", ExistenceChecker.isSet((LegalAgreementTypeEnum) o.getAgreementType()))
				.put("creditSupportAgreementType", ExistenceChecker.isSet((FieldWithMetaCreditSupportAgreementTypeEnum) o.getCreditSupportAgreementType()))
				.put("creditSupportAgreementMarginType", ExistenceChecker.isSet((CollateralMarginTypeEnum) o.getCreditSupportAgreementMarginType()))
				.put("contractualDefinitionsType", ExistenceChecker.isSet((List<? extends FieldWithMetaContractualDefinitionsEnum>) o.getContractualDefinitionsType()))
				.put("contractualTermsSupplement", ExistenceChecker.isSet((List<? extends ContractualTermsSupplement>) o.getContractualTermsSupplement()))
				.put("contractualMatrix", ExistenceChecker.isSet((List<? extends ContractualMatrix>) o.getContractualMatrix()))
				.put("masterAgreementType", ExistenceChecker.isSet((FieldWithMetaMasterAgreementTypeEnum) o.getMasterAgreementType()))
				.put("masterConfirmationType", ExistenceChecker.isSet((FieldWithMetaMasterConfirmationTypeEnum) o.getMasterConfirmationType()))
				.put("masterConfirmationAnnexType", ExistenceChecker.isSet((FieldWithMetaMasterConfirmationAnnexTypeEnum) o.getMasterConfirmationAnnexType()))
				.put("otherAgreement", ExistenceChecker.isSet((String) o.getOtherAgreement()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("AgreementName", ValidationType.ONLY_EXISTS, "AgreementName", path, "");
		}
		return failure("AgreementName", ValidationType.ONLY_EXISTS, "AgreementName", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
