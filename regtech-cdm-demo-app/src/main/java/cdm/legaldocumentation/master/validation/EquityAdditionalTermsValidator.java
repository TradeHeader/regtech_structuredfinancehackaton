package cdm.legaldocumentation.master.validation;

import cdm.legaldocumentation.master.DeterminationRolesAndTerms;
import cdm.legaldocumentation.master.EquityAdditionalTerms;
import cdm.legaldocumentation.master.ExtraordinaryEvents;
import cdm.legaldocumentation.master.UnderlierSubstitutionProvision;
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

public class EquityAdditionalTermsValidator implements Validator<EquityAdditionalTerms> {

	private List<ComparisonResult> getComparisonResults(EquityAdditionalTerms o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("extraordinaryEvents", (ExtraordinaryEvents) o.getExtraordinaryEvents() != null ? 1 : 0, 0, 1), 
				checkCardinality("determinationTerms", (List<? extends DeterminationRolesAndTerms>) o.getDeterminationTerms() == null ? 0 : ((List<? extends DeterminationRolesAndTerms>) o.getDeterminationTerms()).size(), 1, 0), 
				checkCardinality("substitutionProvision", (UnderlierSubstitutionProvision) o.getSubstitutionProvision() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<EquityAdditionalTerms> validate(RosettaPath path, EquityAdditionalTerms o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("EquityAdditionalTerms", ValidationType.CARDINALITY, "EquityAdditionalTerms", path, "", error);
		}
		return success("EquityAdditionalTerms", ValidationType.CARDINALITY, "EquityAdditionalTerms", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, EquityAdditionalTerms o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("EquityAdditionalTerms", ValidationType.CARDINALITY, "EquityAdditionalTerms", path, "", res.getError());
				}
				return success("EquityAdditionalTerms", ValidationType.CARDINALITY, "EquityAdditionalTerms", path, "");
			})
			.collect(toList());
	}

}
