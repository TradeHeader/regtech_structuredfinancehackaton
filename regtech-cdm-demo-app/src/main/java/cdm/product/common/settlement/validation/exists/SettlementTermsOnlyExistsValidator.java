package cdm.product.common.settlement.validation.exists;

import cdm.product.common.settlement.CashSettlementTerms;
import cdm.product.common.settlement.PhysicalSettlementTerms;
import cdm.product.common.settlement.SettlementCentreEnum;
import cdm.product.common.settlement.SettlementDate;
import cdm.product.common.settlement.SettlementProvision;
import cdm.product.common.settlement.SettlementTerms;
import cdm.product.common.settlement.SettlementTypeEnum;
import cdm.product.common.settlement.StandardSettlementStyleEnum;
import cdm.product.common.settlement.TransferSettlementEnum;
import com.google.common.collect.ImmutableMap;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ExistenceChecker;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.ValidatorWithArg;
import com.rosetta.model.metafields.FieldWithMetaString;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;

public class SettlementTermsOnlyExistsValidator implements ValidatorWithArg<SettlementTerms, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends SettlementTerms> ValidationResult<SettlementTerms> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("settlementType", ExistenceChecker.isSet((SettlementTypeEnum) o.getSettlementType()))
				.put("transferSettlementType", ExistenceChecker.isSet((TransferSettlementEnum) o.getTransferSettlementType()))
				.put("settlementCurrency", ExistenceChecker.isSet((FieldWithMetaString) o.getSettlementCurrency()))
				.put("settlementDate", ExistenceChecker.isSet((SettlementDate) o.getSettlementDate()))
				.put("settlementCentre", ExistenceChecker.isSet((SettlementCentreEnum) o.getSettlementCentre()))
				.put("settlementProvision", ExistenceChecker.isSet((SettlementProvision) o.getSettlementProvision()))
				.put("standardSettlementStyle", ExistenceChecker.isSet((StandardSettlementStyleEnum) o.getStandardSettlementStyle()))
				.put("cashSettlementTerms", ExistenceChecker.isSet((List<? extends CashSettlementTerms>) o.getCashSettlementTerms()))
				.put("physicalSettlementTerms", ExistenceChecker.isSet((PhysicalSettlementTerms) o.getPhysicalSettlementTerms()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("SettlementTerms", ValidationType.ONLY_EXISTS, "SettlementTerms", path, "");
		}
		return failure("SettlementTerms", ValidationType.ONLY_EXISTS, "SettlementTerms", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
