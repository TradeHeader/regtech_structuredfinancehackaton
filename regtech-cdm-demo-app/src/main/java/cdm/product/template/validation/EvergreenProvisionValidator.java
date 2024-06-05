package cdm.product.template.validation;

import cdm.base.datetime.AdjustableRelativeOrPeriodicDates;
import cdm.base.datetime.RelativeDateOffset;
import cdm.base.staticdata.party.PartyRole;
import cdm.observable.asset.Price;
import cdm.product.template.EvergreenProvision;
import com.google.common.collect.Lists;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;
import java.time.ZonedDateTime;
import java.util.List;

import static com.google.common.base.Strings.isNullOrEmpty;
import static com.rosetta.model.lib.expression.ExpressionOperators.checkCardinality;
import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class EvergreenProvisionValidator implements Validator<EvergreenProvision> {

	private List<ComparisonResult> getComparisonResults(EvergreenProvision o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("singlePartyOption", (PartyRole) o.getSinglePartyOption() != null ? 1 : 0, 0, 1), 
				checkCardinality("noticePeriod", (RelativeDateOffset) o.getNoticePeriod() != null ? 1 : 0, 1, 1), 
				checkCardinality("noticeDeadlinePeriod", (RelativeDateOffset) o.getNoticeDeadlinePeriod() != null ? 1 : 0, 0, 1), 
				checkCardinality("noticeDeadlineDateTime", (ZonedDateTime) o.getNoticeDeadlineDateTime() != null ? 1 : 0, 0, 1), 
				checkCardinality("extensionFrequency", (AdjustableRelativeOrPeriodicDates) o.getExtensionFrequency() != null ? 1 : 0, 1, 1), 
				checkCardinality("finalPeriodFeeAdjustment", (Price) o.getFinalPeriodFeeAdjustment() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<EvergreenProvision> validate(RosettaPath path, EvergreenProvision o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("EvergreenProvision", ValidationType.CARDINALITY, "EvergreenProvision", path, "", error);
		}
		return success("EvergreenProvision", ValidationType.CARDINALITY, "EvergreenProvision", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, EvergreenProvision o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("EvergreenProvision", ValidationType.CARDINALITY, "EvergreenProvision", path, "", res.getError());
				}
				return success("EvergreenProvision", ValidationType.CARDINALITY, "EvergreenProvision", path, "");
			})
			.collect(toList());
	}

}
