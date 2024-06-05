package cdm.product.common.settlement.validation.exists;

import cdm.product.common.settlement.SettlementBase;
import cdm.product.common.settlement.SettlementCentreEnum;
import cdm.product.common.settlement.SettlementDate;
import cdm.product.common.settlement.SettlementProvision;
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
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;

public class SettlementBaseOnlyExistsValidator implements ValidatorWithArg<SettlementBase, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends SettlementBase> ValidationResult<SettlementBase> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("settlementType", ExistenceChecker.isSet((SettlementTypeEnum) o.getSettlementType()))
				.put("transferSettlementType", ExistenceChecker.isSet((TransferSettlementEnum) o.getTransferSettlementType()))
				.put("settlementCurrency", ExistenceChecker.isSet((FieldWithMetaString) o.getSettlementCurrency()))
				.put("settlementDate", ExistenceChecker.isSet((SettlementDate) o.getSettlementDate()))
				.put("settlementCentre", ExistenceChecker.isSet((SettlementCentreEnum) o.getSettlementCentre()))
				.put("settlementProvision", ExistenceChecker.isSet((SettlementProvision) o.getSettlementProvision()))
				.put("standardSettlementStyle", ExistenceChecker.isSet((StandardSettlementStyleEnum) o.getStandardSettlementStyle()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("SettlementBase", ValidationType.ONLY_EXISTS, "SettlementBase", path, "");
		}
		return failure("SettlementBase", ValidationType.ONLY_EXISTS, "SettlementBase", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
