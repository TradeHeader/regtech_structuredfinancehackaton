package cdm.product.asset.validation;

import cdm.product.asset.CreditIndexReferenceInformation;
import com.google.common.collect.Lists;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;
import java.util.List;

import static com.google.common.base.Strings.isNullOrEmpty;
import static com.rosetta.model.lib.expression.ExpressionOperators.checkNumber;
import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;
import static java.util.Optional.empty;
import static java.util.Optional.of;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class CreditIndexReferenceInformationTypeFormatValidator implements Validator<CreditIndexReferenceInformation> {

	private List<ComparisonResult> getComparisonResults(CreditIndexReferenceInformation o) {
		return Lists.<ComparisonResult>newArrayList(
				checkNumber("indexSeries", o.getIndexSeries(), empty(), of(0), empty(), empty()), 
				checkNumber("indexAnnexVersion", o.getIndexAnnexVersion(), empty(), of(0), empty(), empty())
			);
	}

	@Override
	public ValidationResult<CreditIndexReferenceInformation> validate(RosettaPath path, CreditIndexReferenceInformation o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("CreditIndexReferenceInformation", ValidationType.TYPE_FORMAT, "CreditIndexReferenceInformation", path, "", error);
		}
		return success("CreditIndexReferenceInformation", ValidationType.TYPE_FORMAT, "CreditIndexReferenceInformation", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, CreditIndexReferenceInformation o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("CreditIndexReferenceInformation", ValidationType.TYPE_FORMAT, "CreditIndexReferenceInformation", path, "", res.getError());
				}
				return success("CreditIndexReferenceInformation", ValidationType.TYPE_FORMAT, "CreditIndexReferenceInformation", path, "");
			})
			.collect(toList());
	}

}
