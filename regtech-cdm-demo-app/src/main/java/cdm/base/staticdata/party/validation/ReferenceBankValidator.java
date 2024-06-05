package cdm.base.staticdata.party.validation;

import cdm.base.staticdata.party.ReferenceBank;
import com.google.common.collect.Lists;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;
import com.rosetta.model.metafields.FieldWithMetaString;
import java.util.List;

import static com.google.common.base.Strings.isNullOrEmpty;
import static com.rosetta.model.lib.expression.ExpressionOperators.checkCardinality;
import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class ReferenceBankValidator implements Validator<ReferenceBank> {

	private List<ComparisonResult> getComparisonResults(ReferenceBank o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("referenceBankId", (FieldWithMetaString) o.getReferenceBankId() != null ? 1 : 0, 1, 1), 
				checkCardinality("referenceBankName", (String) o.getReferenceBankName() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<ReferenceBank> validate(RosettaPath path, ReferenceBank o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("ReferenceBank", ValidationType.CARDINALITY, "ReferenceBank", path, "", error);
		}
		return success("ReferenceBank", ValidationType.CARDINALITY, "ReferenceBank", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, ReferenceBank o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("ReferenceBank", ValidationType.CARDINALITY, "ReferenceBank", path, "", res.getError());
				}
				return success("ReferenceBank", ValidationType.CARDINALITY, "ReferenceBank", path, "");
			})
			.collect(toList());
	}

}
