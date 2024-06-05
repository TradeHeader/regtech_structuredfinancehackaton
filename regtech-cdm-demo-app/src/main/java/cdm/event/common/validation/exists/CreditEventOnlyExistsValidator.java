package cdm.event.common.validation.exists;

import cdm.event.common.CreditEvent;
import cdm.event.common.CreditEventTypeEnum;
import cdm.legaldocumentation.common.Resource;
import cdm.observable.asset.Price;
import cdm.product.asset.ReferenceInformation;
import com.google.common.collect.ImmutableMap;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.records.Date;
import com.rosetta.model.lib.validation.ExistenceChecker;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.ValidatorWithArg;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;

public class CreditEventOnlyExistsValidator implements ValidatorWithArg<CreditEvent, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends CreditEvent> ValidationResult<CreditEvent> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("creditEventType", ExistenceChecker.isSet((CreditEventTypeEnum) o.getCreditEventType()))
				.put("eventDeterminationDate", ExistenceChecker.isSet((Date) o.getEventDeterminationDate()))
				.put("auctionDate", ExistenceChecker.isSet((Date) o.getAuctionDate()))
				.put("finalPrice", ExistenceChecker.isSet((Price) o.getFinalPrice()))
				.put("recoveryPercent", ExistenceChecker.isSet((BigDecimal) o.getRecoveryPercent()))
				.put("publiclyAvailableInformation", ExistenceChecker.isSet((List<? extends Resource>) o.getPubliclyAvailableInformation()))
				.put("referenceInformation", ExistenceChecker.isSet((ReferenceInformation) o.getReferenceInformation()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("CreditEvent", ValidationType.ONLY_EXISTS, "CreditEvent", path, "");
		}
		return failure("CreditEvent", ValidationType.ONLY_EXISTS, "CreditEvent", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
