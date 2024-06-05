package cdm.observable.event.validation;

import cdm.base.datetime.BusinessCenterEnum;
import cdm.base.staticdata.party.CounterpartyRoleEnum;
import cdm.observable.event.CreditEventNotice;
import cdm.observable.event.PubliclyAvailableInformation;
import com.google.common.collect.Lists;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.path.RosettaPath;
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

public class CreditEventNoticeValidator implements Validator<CreditEventNotice> {

	private List<ComparisonResult> getComparisonResults(CreditEventNotice o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("notifyingParty", (List<CounterpartyRoleEnum>) o.getNotifyingParty() == null ? 0 : ((List<CounterpartyRoleEnum>) o.getNotifyingParty()).size(), 1, 2), 
				checkCardinality("businessCenter", (BusinessCenterEnum) o.getBusinessCenter() != null ? 1 : 0, 0, 1), 
				checkCardinality("publiclyAvailableInformation", (PubliclyAvailableInformation) o.getPubliclyAvailableInformation() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<CreditEventNotice> validate(RosettaPath path, CreditEventNotice o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("CreditEventNotice", ValidationType.CARDINALITY, "CreditEventNotice", path, "", error);
		}
		return success("CreditEventNotice", ValidationType.CARDINALITY, "CreditEventNotice", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, CreditEventNotice o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("CreditEventNotice", ValidationType.CARDINALITY, "CreditEventNotice", path, "", res.getError());
				}
				return success("CreditEventNotice", ValidationType.CARDINALITY, "CreditEventNotice", path, "");
			})
			.collect(toList());
	}

}
