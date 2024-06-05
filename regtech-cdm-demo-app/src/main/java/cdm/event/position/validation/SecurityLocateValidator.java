package cdm.event.position.validation;

import cdm.event.position.SecurityLocate;
import cdm.event.workflow.MessageInformation;
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

public class SecurityLocateValidator implements Validator<SecurityLocate> {

	private List<ComparisonResult> getComparisonResults(SecurityLocate o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("messageInformation", (MessageInformation) o.getMessageInformation() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<SecurityLocate> validate(RosettaPath path, SecurityLocate o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("SecurityLocate", ValidationType.CARDINALITY, "SecurityLocate", path, "", error);
		}
		return success("SecurityLocate", ValidationType.CARDINALITY, "SecurityLocate", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, SecurityLocate o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("SecurityLocate", ValidationType.CARDINALITY, "SecurityLocate", path, "", res.getError());
				}
				return success("SecurityLocate", ValidationType.CARDINALITY, "SecurityLocate", path, "");
			})
			.collect(toList());
	}

}
