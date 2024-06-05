package cdm.base.staticdata.party.validation;

import cdm.base.staticdata.party.PartyReferencePayerReceiver;
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

public class PartyReferencePayerReceiverTypeFormatValidator implements Validator<PartyReferencePayerReceiver> {

	private List<ComparisonResult> getComparisonResults(PartyReferencePayerReceiver o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<PartyReferencePayerReceiver> validate(RosettaPath path, PartyReferencePayerReceiver o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("PartyReferencePayerReceiver", ValidationType.TYPE_FORMAT, "PartyReferencePayerReceiver", path, "", error);
		}
		return success("PartyReferencePayerReceiver", ValidationType.TYPE_FORMAT, "PartyReferencePayerReceiver", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, PartyReferencePayerReceiver o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("PartyReferencePayerReceiver", ValidationType.TYPE_FORMAT, "PartyReferencePayerReceiver", path, "", res.getError());
				}
				return success("PartyReferencePayerReceiver", ValidationType.TYPE_FORMAT, "PartyReferencePayerReceiver", path, "");
			})
			.collect(toList());
	}

}
