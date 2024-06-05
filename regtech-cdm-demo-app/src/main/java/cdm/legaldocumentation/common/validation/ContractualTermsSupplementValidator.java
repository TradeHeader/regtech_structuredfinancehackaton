package cdm.legaldocumentation.common.validation;

import cdm.legaldocumentation.common.ContractualTermsSupplement;
import cdm.legaldocumentation.common.metafields.FieldWithMetaContractualSupplementTypeEnum;
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

public class ContractualTermsSupplementValidator implements Validator<ContractualTermsSupplement> {

	private List<ComparisonResult> getComparisonResults(ContractualTermsSupplement o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("contractualTermsSupplementType", (FieldWithMetaContractualSupplementTypeEnum) o.getContractualTermsSupplementType() != null ? 1 : 0, 1, 1), 
				checkCardinality("publicationDate", (Date) o.getPublicationDate() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<ContractualTermsSupplement> validate(RosettaPath path, ContractualTermsSupplement o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("ContractualTermsSupplement", ValidationType.CARDINALITY, "ContractualTermsSupplement", path, "", error);
		}
		return success("ContractualTermsSupplement", ValidationType.CARDINALITY, "ContractualTermsSupplement", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, ContractualTermsSupplement o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("ContractualTermsSupplement", ValidationType.CARDINALITY, "ContractualTermsSupplement", path, "", res.getError());
				}
				return success("ContractualTermsSupplement", ValidationType.CARDINALITY, "ContractualTermsSupplement", path, "");
			})
			.collect(toList());
	}

}
