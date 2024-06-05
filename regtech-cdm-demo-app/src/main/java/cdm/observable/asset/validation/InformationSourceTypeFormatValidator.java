package cdm.observable.asset.validation;

import cdm.observable.asset.InformationSource;
import com.google.common.collect.Lists;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;
import java.util.List;

import static com.google.common.base.Strings.isNullOrEmpty;
import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class InformationSourceTypeFormatValidator implements Validator<InformationSource> {

	private List<ComparisonResult> getComparisonResults(InformationSource o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<InformationSource> validate(RosettaPath path, InformationSource o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("InformationSource", ValidationType.TYPE_FORMAT, "InformationSource", path, "", error);
		}
		return success("InformationSource", ValidationType.TYPE_FORMAT, "InformationSource", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, InformationSource o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("InformationSource", ValidationType.TYPE_FORMAT, "InformationSource", path, "", res.getError());
				}
				return success("InformationSource", ValidationType.TYPE_FORMAT, "InformationSource", path, "");
			})
			.collect(toList());
	}

}
