package cdm.event.workflow.validation.exists;

import cdm.event.workflow.CreditLimitUtilisation;
import cdm.event.workflow.LimitApplicableExtended;
import cdm.event.workflow.Velocity;
import cdm.event.workflow.metafields.FieldWithMetaCreditLimitTypeEnum;
import cdm.event.workflow.metafields.FieldWithMetaLimitLevelEnum;
import com.google.common.collect.ImmutableMap;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ExistenceChecker;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.ValidatorWithArg;
import com.rosetta.model.metafields.FieldWithMetaString;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;

public class LimitApplicableExtendedOnlyExistsValidator implements ValidatorWithArg<LimitApplicableExtended, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends LimitApplicableExtended> ValidationResult<LimitApplicableExtended> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("limitType", ExistenceChecker.isSet((FieldWithMetaCreditLimitTypeEnum) o.getLimitType()))
				.put("clipSize", ExistenceChecker.isSet((Integer) o.getClipSize()))
				.put("amountUtilized", ExistenceChecker.isSet((BigDecimal) o.getAmountUtilized()))
				.put("utilization", ExistenceChecker.isSet((CreditLimitUtilisation) o.getUtilization()))
				.put("amountRemaining", ExistenceChecker.isSet((BigDecimal) o.getAmountRemaining()))
				.put("currency", ExistenceChecker.isSet((FieldWithMetaString) o.getCurrency()))
				.put("velocity", ExistenceChecker.isSet((Velocity) o.getVelocity()))
				.put("limitLevel", ExistenceChecker.isSet((FieldWithMetaLimitLevelEnum) o.getLimitLevel()))
				.put("limitAmount", ExistenceChecker.isSet((BigDecimal) o.getLimitAmount()))
				.put("limitImpactDueToTrade", ExistenceChecker.isSet((BigDecimal) o.getLimitImpactDueToTrade()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("LimitApplicableExtended", ValidationType.ONLY_EXISTS, "LimitApplicableExtended", path, "");
		}
		return failure("LimitApplicableExtended", ValidationType.ONLY_EXISTS, "LimitApplicableExtended", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
