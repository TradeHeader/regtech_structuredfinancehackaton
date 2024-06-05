package cdm.product.template.validation.exists;

import cdm.observable.asset.SecurityValuation;
import cdm.product.template.InitialMargin;
import cdm.product.template.RepoDurationEnum;
import cdm.product.template.SecurityLeg;
import cdm.product.template.SecurityPayout;
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

public class SecurityPayoutOnlyExistsValidator implements ValidatorWithArg<SecurityPayout, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends SecurityPayout> ValidationResult<SecurityPayout> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("securityLeg", ExistenceChecker.isSet((List<? extends SecurityLeg>) o.getSecurityLeg()))
				.put("initialMargin", ExistenceChecker.isSet((InitialMargin) o.getInitialMargin()))
				.put("repoDuration", ExistenceChecker.isSet((RepoDurationEnum) o.getRepoDuration()))
				.put("securityValuation", ExistenceChecker.isSet((List<? extends SecurityValuation>) o.getSecurityValuation()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("SecurityPayout", ValidationType.ONLY_EXISTS, "SecurityPayout", path, "");
		}
		return failure("SecurityPayout", ValidationType.ONLY_EXISTS, "SecurityPayout", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
