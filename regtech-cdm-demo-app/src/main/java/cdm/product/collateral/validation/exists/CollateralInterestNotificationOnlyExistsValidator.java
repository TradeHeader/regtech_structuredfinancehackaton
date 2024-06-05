package cdm.product.collateral.validation.exists;

import cdm.base.datetime.DayTypeEnum;
import cdm.product.collateral.CollateralInterestNotification;
import com.google.common.collect.ImmutableMap;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ExistenceChecker;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.ValidatorWithArg;
import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;

public class CollateralInterestNotificationOnlyExistsValidator implements ValidatorWithArg<CollateralInterestNotification, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends CollateralInterestNotification> ValidationResult<CollateralInterestNotification> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("trigger", ExistenceChecker.isSet((String) o.getTrigger()))
				.put("offset", ExistenceChecker.isSet((BigDecimal) o.getOffset()))
				.put("notificationTime", ExistenceChecker.isSet((LocalTime) o.getNotificationTime()))
				.put("notificationDayType", ExistenceChecker.isSet((DayTypeEnum) o.getNotificationDayType()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("CollateralInterestNotification", ValidationType.ONLY_EXISTS, "CollateralInterestNotification", path, "");
		}
		return failure("CollateralInterestNotification", ValidationType.ONLY_EXISTS, "CollateralInterestNotification", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
