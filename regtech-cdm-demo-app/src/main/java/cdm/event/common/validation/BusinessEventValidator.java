package cdm.event.common.validation;

import cdm.base.staticdata.identifier.IdentifiedList;
import cdm.event.common.BusinessEvent;
import cdm.event.common.CorporateActionTypeEnum;
import cdm.event.common.EventIntentEnum;
import com.google.common.collect.Lists;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.records.Date;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;
import java.util.List;

import static com.google.common.base.Strings.isNullOrEmpty;
import static com.rosetta.model.lib.expression.ExpressionOperators.checkCardinality;
import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class BusinessEventValidator implements Validator<BusinessEvent> {

	private List<ComparisonResult> getComparisonResults(BusinessEvent o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("intent", (EventIntentEnum) o.getIntent() != null ? 1 : 0, 0, 1), 
				checkCardinality("corporateActionIntent", (CorporateActionTypeEnum) o.getCorporateActionIntent() != null ? 1 : 0, 0, 1), 
				checkCardinality("eventDate", (Date) o.getEventDate() != null ? 1 : 0, 0, 1), 
				checkCardinality("effectiveDate", (Date) o.getEffectiveDate() != null ? 1 : 0, 0, 1), 
				checkCardinality("packageInformation", (IdentifiedList) o.getPackageInformation() != null ? 1 : 0, 0, 1), 
				checkCardinality("eventQualifier", (String) o.getEventQualifier() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<BusinessEvent> validate(RosettaPath path, BusinessEvent o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("BusinessEvent", ValidationType.CARDINALITY, "BusinessEvent", path, "", error);
		}
		return success("BusinessEvent", ValidationType.CARDINALITY, "BusinessEvent", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, BusinessEvent o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("BusinessEvent", ValidationType.CARDINALITY, "BusinessEvent", path, "", res.getError());
				}
				return success("BusinessEvent", ValidationType.CARDINALITY, "BusinessEvent", path, "");
			})
			.collect(toList());
	}

}
